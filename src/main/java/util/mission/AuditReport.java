package util.mission;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import util.DbSource;

import java.util.List;

public class AuditReport {

    public static void main(String[] args) {
        DbSource.init();
        run2();
    }

    public static void run2() {
        //从报表查询出所有状态需要审核的报表
        String sql = "select id from oa_finance_report where status = 2";
        List<Record> list = Db.find(sql);
        for (Record record : list) {
            Long id = record.getLong("id");
            if (id != null) {
                //根据报表ID,查询最新的审核人
                String sql2 = "SELECT * from oa_finance_report_data_error_check where report_id = ? ORDER BY id";
                //审核请求
                Record check = Db.findFirst(sql2,id);
                Long dealer = check.getLong("");



                //查询报表最新状态
            }
        }
    }


}
