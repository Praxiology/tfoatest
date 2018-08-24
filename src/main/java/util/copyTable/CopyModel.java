package util.copyTable;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import util.DbSource;

import java.util.List;

public class CopyModel {

    public static Long modelId = 52L;

    public static String tb = " oa_finance_model_formula ";
    private static String sql = "select * from  "+tb+"  where model_id = ?";

    public static void main(String[] args) {
        DbSource.init();
        List<Record> list = Db.find(sql , modelId);
        runTSUM(list);
    }

    public static void runTSUM(List<Record> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            boolean upIsTSUM = false;
            String upFormula = null;
            for (Record record : list) {
                //修改TSUM公式:上一个是TSUM公式,下一个是TQ公式,替换公式的口径
                String formula = record.getStr("formula");
                if (StringUtils.isNotBlank(formula)) {
                    formula = formula.trim();
                    if (formula.startsWith("=TQ") && upIsTSUM) {
                        formula = upFormula.replaceFirst("101102" , "100103");
                        //System.err.printf("%s\n" ,formula);
                    }
                    upIsTSUM = false;
                    upFormula = null;
                    if (formula.startsWith("=TSU")) {
                        upFormula = formula;
                        upIsTSUM = true;
                    }
                    Long id = record.getLong("id");
                    record.clear();
                    record.set("id" , id);
                    record.set("formula" , formula);
                    Db.update(tb , record);
                }
            }
        }

    }

    public static void runTQ(List<Record> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (Record record : list) {
                //修改合并抵销项
                String formula = record.getStr("formula");
                if (StringUtils.isNotBlank(formula)) {
                    if (formula.contains("=TQ")) {
                        //=TQ[[100101],[-208201],[year,month]]
                        Long project = record.getLong("project");
                        formula = formula.replaceFirst("208201" , String.valueOf(project));
                        Long id = record.getLong("id");
                        record.clear();
                        record.set("id" , id);
                        record.set("formula" , formula);
                        Db.update(tb , record);
                    }
                }
            }
        }
    }


}
