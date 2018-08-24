package util.deleteData;

import com.jfinal.plugin.activerecord.Db;
import util.DbSource;

public class DeleteData {


    //删除任务,报表,报表详细数据

    public static void main(String[] args) {
        DbSource.init();
        run();
    }
    public static void run(){
        Long[] ids = {2220L,2222L,2223L,2224L,2221L,2219L};
        for (Long id : ids) {
            run(id);
        }
        Db.update("delete from oa_finance_report_metadata where month in(3,2)");
    }

    public static void run(Long id){
        Db.update("delete from oa_finance_report where id =?",id);
        Db.update("delete from oa_finance_report_formula_value where report_id =?",id);
        Db.update("delete from oa_finance_report_relation where report_id =?",id);
    }





}
