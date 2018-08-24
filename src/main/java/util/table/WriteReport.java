package util.table;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import util.DbSource;

import java.util.List;

public class WriteReport {

    public static void main(String[] args) {
        DbSource.init();
        deleteByModelId(null);
    }

    public static void  queryReport(){
        StringBuilder _sql = new StringBuilder();
        _sql.append("SELECT * from oa_finance_report where 1=1 ");
        List<Record> list = Db.find(_sql.toString());
        for (Record record : list) {
            String name = record.getStr("name");
            System.err.printf("name:%s\n" ,name );
        }
    }

    public static void deleteByModelId(Long modelId){
        //查询关联的任务
        //再查询关联的报表
        //删除报表 relation formula todoList
        StringBuilder _sql = new StringBuilder();
        _sql.append("SELECT id from oa_finance_report where id in(2414,2415,2416) ");
        /*_sql.append(" ( ");
        _sql.append(" SELECT id from oa_finance_mission where write_model_id =54 ");
        _sql.append(" ) ");*/
        List<Record> list = Db.find(_sql.toString());
        for (Record record : list) {
            Long _id = record.getLong("id");
            Db.update("delete from oa_finance_report where id =?" , _id);
            Db.update("delete from oa_finance_report_formula_value where report_id =?" , _id);
            Db.update("delete from oa_finance_report_relation where report_id =?" , _id);
            Db.update("delete from oa_finance_report_data_error_check where report_id =?" , _id);
            Db.update("delete from oa_to_do_list where type =3 and data_id =?" , _id);
        }
    }
}
