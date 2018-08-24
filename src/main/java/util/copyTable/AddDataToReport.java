package util.copyTable;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.collections.CollectionUtils;
import util.DbSource;

import java.util.List;

public class AddDataToReport {
    public static Long reportIds[] ={2188L,-1L} ;

    public static String tb = " oa_finance_report_formula_value ";
    private static String sql = "select * from  "+tb+"  where report_id = ?";

    public static void main(String[] args) {
        DbSource.init();
        for (Long reportId : reportIds) {
            List<Record> list = Db.find(sql , reportId);
            runTSEL(list);
        }
    }

    public static void runTSEL(List<Record> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            //本月数 赋值 10D
            Double v = 10D;
            for (Record record : list) {
                if (100104L == record.getLong("unit") && record.getStr("formula").startsWith("=TSE")) {
                   // System.err.printf("1:%s\n" , record.toJson());
                    Long id = record.getLong("id");
                    record.clear();
                    record.set("id" , id);
                    record.set("value" , v);
                    //System.err.printf("2:%s\n" , record.toJson());
                    Db.update(tb , record);
                }
            }
        }
    }


}
