package util.change;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.collections4.CollectionUtils;
import util.DbSource;

import java.util.List;

public class QueryReportDetileInf {

    //每隔10s,请求一次接口

    public static void main(String[] args) {
        DbSource.init();
        run2();
    }

    public static void run2() {
        String sql = "select id from oa_finance_report where id in(2392,2393,2394)";
        List<Record> list = Db.find(sql);
        for (Record record : list) {
            Long id = record.getLong("id");
            if (id != null) {
                sql = "update oa_finance_report_formula_value set value=10 where report_id = ? and unit=100104";
                Db.update(sql,id);
            }
        }
    }


    public static void run() {
        String sql = "select fr.id,fr.name,oc.name cname from oa_finance_report fr left join oa_company oc ON fr.company_id = oc.id";
        List<Record> list = Db.find(sql);
        for (Record record : list) {
            Long id = record.getLong("id");
            sql = "select id from oa_finance_report_relation where report_id = ?";
            List<Record> rds = Db.find(sql , id);
            if (CollectionUtils.isEmpty(rds)) {
                System.err.printf("name:%s,cname:%s\n" , record.getStr("name") , record.getStr("cname"));
            }
        }
    }


}
