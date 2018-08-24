package util.change;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import util.DbSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ChangeUnit {


    //1:将 oa_finance_report_formula_value 的type 变更

    //2:将 oa_finance_report_metadata 非元数据删除


    public static void main(String[] args) {
        DbSource.init();
        run2();
    }
    public static void run2(){

        List<Record> list = Db.find("select * from oa_finance_model_formula where model_id = 17");
        for (Record record : list) {
            String formula = record.getStr("formula");
            Long id = record.getLong("id");
            formula = formula.replace("213211","213203");
           // System.err.printf("%s\n" , formula);
            Db.update("update oa_finance_model_formula set formula = ? where id =?",formula,id);
        }

    }


    public static void run(){
       // Db.update("delete from oa_finance_report_metadata where unit in(100102,101101,101102)");
        /*StringBuilder unitIds = new StringBuilder();
        Set<Long> uns = new TreeSet<>();
        List<Record> list = Db.find("select id,type,unit from oa_finance_report_formula_value where 1=1");
        for (Record record : list) {
            Long unit = record.getLong("unit");
            Record re = Db.findFirst("select base_data  from oa_finance_unit where identifier =?",unit);
            int base_data = re.getInt("base_data");
            if (9 == base_data) {
                uns.add(unit);
            }
            //Db.update(" update oa_finance_report_formula_value set type = ? where id = ? ",base_data,record.getInt("id"));
        }*/
        //uns.forEach(e -> System.err.printf("units:%s\n" ,e.toString()));
    }

}
