package util.excuteSql;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import util.Dao.ModelUtil;

import java.util.List;

/**
 * DML（data manipulation language）数据操纵语言：
 * 　　　　就是我们最经常用到的 SELECT、UPDATE、INSERT、DELETE。 主要用来对数据库的数据进行一些操作。
 * <p>
 * SELECT 列名称 FROM 表名称
 * UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
 * INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
 * DELETE FROM 表名称 WHERE 列名称 = 值
 */
public class DML extends ModelUtil {
    public static final String[] sqls = {
            "un used",
            "UPDATE oa_finance_report SET child_company_num = (SELECT count(1) from oa_company oc where oc.parent_id=?) where company_id=? ",
            "SELECT count(1) from oa_company oc where oc.parent_id=?",
            "SELECT id,company_id from oa_finance_report"
    };

    @Test
    public void test_update_sql(){
        List<Record> reports = Db.find(sqls[3]);
        if (CollectionUtils.isNotEmpty(reports)) {
            for (Record report : reports) {
                Long cid = report.getLong("company_id");
                Db.update(sqls[1],cid,cid);
            }

        }
    }
}
