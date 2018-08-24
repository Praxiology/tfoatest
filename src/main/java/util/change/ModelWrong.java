package util.change;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import util.DbSource;

import java.util.List;

public class ModelWrong {

    public static void main(String[] args) {
        DbSource.init();
        //run();
    }

    //因为模板错误,删除报表
    public static void run() {
        //String sql = "SELECT id from oa_finance_report where mission_id in (SELECT id from oa_finance_mission where write_model_id <=20 and write_model_id >=13 )";
        String sql = "SELECT id from oa_finance_report where 1=1";
        List<Record> list = Db.find(sql);
       /* for (Record record : list) {
            Long _id = record.getLong("id");
            Db.update("delete from oa_finance_report where id =?" , _id);
            Db.update("delete from oa_finance_report_formula_value where report_id =?" , _id);
            Db.update("delete from oa_finance_report_relation where report_id =?" , _id);
            Db.update("delete from oa_finance_report_data_error_check where apply_type =2 and report_id =?" , _id);
            Db.update("delete from oa_to_do_list where type =3 and document_type =5 and data_id =?" , _id);
        }*/
    }
}
