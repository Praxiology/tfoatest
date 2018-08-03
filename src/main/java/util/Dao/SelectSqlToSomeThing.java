package util.Dao;

import org.junit.Test;
import util.StrUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectSqlToSomeThing {

    public static String selectSql = "al.id,bs.name,fg.age";

    @Test
    public void run() {
        recordToDtoSet();
    }

    public void recordToDtoSet() {
        String[] clos = selectSql.split(",");
        StringBuilder sd = new StringBuilder();
        List<String> objs = new ArrayList<>();
        for (String clo : clos) {
            String[] arr = clo.split("\\.");
            String cl = null;
            if (arr.length == 0) {
                cl = clo;
            } else {
                cl = arr[1];
            }
            objs.add(StrUtil.humpDbToSet(cl));
            objs.add(cl);
            sd.append("\ndto.set?(record.get(\"?\"));");
        }
        String result = StrUtil.replace(sd.toString() , objs);
        System.err.println(result);
    }


}
