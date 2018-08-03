package util.Dao;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import util.DbUtil;

import java.util.List;

public class SqlUtil {

    public SqlUtil(){
        DbUtil.init();
        DbUtil.setColumns(new String[]{"column_name"});
        DbUtil.setTabs(new String[]{"oa_finance_report=fr"});
    }

    @Test
    public void run(){
        String sql = DbUtil.getSql();
        String[] tbInfo = null;
        for (String tb : DbUtil.tabs) {
            tbInfo = tb.split("=");
            List<Record> list = Db.find(sql , tbInfo[0]);
            getSelect(list,tbInfo[1],tbInfo[0]);
            //getUpdateSql(list,tbInfo[0]);
        }

    }

    public void getUpdateSql(List<Record> list,String tb){
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuilder sd = new StringBuilder(" update ");
            sd.append(tb);
            sd.append(" set ");
            String column = null;
            StringBuilder sd2 = new StringBuilder(" where ");
            for (Record record : list) {
                column = record.getStr("column_name");
                sd.append(column);
                sd.append(" = ");
                sd.append(column);
                sd.append(",");
                sd2.append(column);
                sd2.append(" = ? ");
                sd2.append(" and ");
            }
            sd.append(sd2);
            System.err.println(sd);
        }
    }

    public void getSelect(List<Record> list,String tbAlis,String tb){
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuilder sd = new StringBuilder(" select ");
            String column = null;
            for (Record record : list) {
                column = record.getStr("column_name");
                sd.append(tbAlis);
                sd.append(".");
                sd.append(column);
                sd.append(", ");
            }
            sd.append(" from ");
            sd.append(tb);
            sd.append(" ");
            sd.append(tbAlis);
            System.err.println(sd);
        }

    }

}
