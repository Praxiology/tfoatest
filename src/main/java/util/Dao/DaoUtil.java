package util.Dao;

import org.junit.Test;

public class DaoUtil {

    public static DaoUtil dao = new DaoUtil();

    public static String[] daos = {"oa_finance_report_prostpone=WriteFinanceReport"};
    public static String importStr = null;

    static {
        StringBuilder sd = new StringBuilder();
        sd.append("import app.gv.Val;\n");
        sd.append("import com.jfinal.ext.plugin.tablebind.TableBind;\n");
        sd.append("import com.jfinal.plugin.activerecord.Model;\n\n");
        importStr = sd.toString();
    }

    public static String getModel(String tb , String model) {
        StringBuilder sd = new StringBuilder("@TableBind(tableName = \"");
        sd.append(tb);
        sd.append("\")\n");
        sd.append("public class ");
        sd.append(model);
        sd.append(" extends Model<");
        sd.append(model);
        sd.append("> {\n\n");
        sd.append("    public static ");
        sd.append(model);
        sd.append(" dao = new ");
        sd.append(model);
        sd.append("();\n\n");
        sd.append("    public long id() {\n        return getLong(Val.PK);\n    }\n}\n\n");
        return sd.toString();
    }

    @Test
    public void run() {
        StringBuilder sd = null;
        StringBuilder all = new StringBuilder();
        for (String dao : daos) {
            sd = new StringBuilder();
            String[] pd = dao.split("=");
            sd.append(importStr);
            sd.append(getModel(pd[0] , pd[1]));
            all.append(sd);
        }
        System.err.println(all);
    }

}
