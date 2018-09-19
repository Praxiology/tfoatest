package util.table;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import util.DbSource;

import java.util.List;

public class Metadata extends DbSource {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        StringBuilder sd = new StringBuilder();
        sd.append("SELECT id,company_id,year,month from oa_finance_report where mission_id in (");
        sd.append("        SELECT id from oa_finance_mission where write_model_id in (");
        sd.append("                SELECT id from oa_finance_write_model wm where wm.business_type=1");
        sd.append("        )");
        sd.append(")");
        List<Record> list = Db.find(sd.toString());
        for (Record record : list) {
            // 获得 报表ID, 公司ID ,年 , 月
            Long id = record.getLong("id");
            Long company_id = record.getLong("company_id");
            Integer year = record.getInt("year");
            Integer month = record.getInt("month");
            // 获得公式表中项目 和 口径
            List<Record> list2 = Db.find("SELECT project , unit FROM oa_finance_report_formula_value where report_id = ?" , id);
            for (Record r2 : list2) {
                Long project = r2.getLong("project");
                Long unit = r2.getLong("unit");
                //删除数据
                //String sql = "delete from oa_finance_report_metadata where project = ? and unit = ? and year = ? and month = ? and company_id=?";
               // Db.update(sql,project , unit , year , month , company_id);
                String sql = "SELECT * from oa_finance_report_metadata where project = ? and unit = ? and year = ? and month = ? and company_id=?";
                List<Record> list3 = Db.find(sql , project , unit , year , month , company_id);
                if (null != list3 && list3.size() > 0) {
                    System.err.printf("%d|%s\n" , list3.size() , list3.get(0).toJson());
                }
            }
        }
    }

}
