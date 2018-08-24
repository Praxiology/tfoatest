package util.copyTable;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import util.DbUtil;

import java.util.List;

public class ModelDataChange {

    // =TSEL[[100101,208201],[year,month]] ==> =TSEL[[100104,208201],[year,month]]
    // =TSEL[[101102,208201],[(year-1),month]] ==> =TSEL[[101103,208201],[(year-1),month]]
    // =TQ[100101,208201] ==> =TQ[[100104],[-208201],[year,month]]

    public static Long formulaModelId = 2151L;

    public static String tb = " oa_finance_report_formula_value ";
    private static String sql = "select * from  "+tb+"  where report_id = ?";

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        DbUtil.init();
        List<Record> list = Db.find(sql , formulaModelId);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Record record : list) {
                String formula = record.getStr("formula");
                if (StringUtils.isNotBlank(formula)) {
                    formula = formula.trim();
                    Double v = 0d;
                    if (formula.startsWith("=TSEL[[")) {
                        if (formula.contains("],[(year-1")) {
                            // =TSEL[[101102,208201],[(year-1),month]] ==> =TSEL[[101103,208201],[(year-1),month]]
                        } else {
                            // =TSEL[[100101,208201],[year,month]] ==> =TSEL[[100104,208201],[year,month]]
                            v = 10d;
                        }
                    } else if (formula.startsWith("=TQ[")) {
                        //=TQ[100101,208201] ==> =TQ[[100104],[-208201],[year,month]]
                    }
                    if (v > 0d) {
                        Long id = record.getLong("id");
                        //System.err.printf("1:%s\n" , record.toJson());
                        record.clear();
                        record.set("id" , id);
                        record.set("value" , 10d);
                        //System.err.printf("2:%s\n" , record.toJson());
                        Db.update(tb , record);
                    }

                    /*if (formula.startsWith(first)) {
                        _formula.replace(0,_formula.length(),"");
                        //替换口径
                        //=TSUM[[100104],[208213,-208214],[year,month]]
                        Long unit = record.getLong("unit");
                        Long id = record.getLong("id");
                        String three = formula.substring(13);
                        _formula.append(first).append(unit).append(three);
                        record.clear();
                       // System.err.printf("1:%s\n" , record.toJson());
                        record.set("formula" , _formula.toString());
                        record.set("id" , id);
                        //System.err.printf("2:%s\n" , record.toJson());
                        Db.update(tb , record);
                        //System.err.printf("%d:%d:%s\n" , record.getLong("project") , record.getLong("unit") , _formula);
                    }*/
                }
            }
        }
    }

}
