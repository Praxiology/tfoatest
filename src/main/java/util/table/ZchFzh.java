package util.table;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import util.DbSource;

import java.util.List;

public class ZchFzh {

    public static void main(String[] args) {
        DbSource.init();
        run();
    }

    public static void run() {
        String sql = "select * from oa_finance_model_formula where model_id = 51";
        List<Record> list = Db.find(sql);
        for (Record rd : list) {
            String formula = rd.getStr("formula");
            Long unit = rd.getLong("unit");//10103
            if (unit != null && unit == 101102L && formula != null && formula.startsWith("=TC")) {
                //System.err.printf("%s,%s\n" ,formula , formula.replace("101102","100101") );
                formula = formula.replace("101102","100101");
               Db.update("update oa_finance_model_formula set formula =? where id=? ",formula,rd.getLong("id"));
            }
        }
    }
}
