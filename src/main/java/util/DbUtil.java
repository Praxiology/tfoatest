package util;

//import com.alibaba.druid.pool.DruidDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.DbKit;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    public static void init() {
        initDruidDataSource();
    }

    public static boolean openComonnProperty = false;

    public static String[] dbInfos = {
            "druid.url=jdbc:mysql://stg2.v5time.net:3366/apg_oa?useUnicode=true&characterEncoding=utf-8" ,
            "druid.name=tfte" ,
            "druid.password=tt0fsdaefst123yQb" ,
            "druid.username=tfte"
    };

    public static String[] columns = null;

    public static String[] columnsAnnotation = null;

    public static String[] tabs = null;

    private static DruidDataSource dataSource = null;

    public static void initDruidDataSource() {
        if (null == dataSource) {
            dataSource = new DruidDataSource();
            dataSource.configFromPropety(getDbProperties());
            DbKit.addConfig(new Config("tablesInfo" , dataSource));
            try {
                dataSource.init();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Properties getDbProperties() {
        Properties properties = new Properties();
        for (String str : dbInfos) {
            String[] props = str.split("=");
            properties.setProperty(props[0] , props[1]);
        }
        return properties;
    }

    public static String getSql() {
        StringBuilder sqlSd = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            sqlSd.append(",tb.");
            sqlSd.append(columns[i]);
        }
        sqlSd.replace(0 , 1 , "select ");
        sqlSd.append(" from information_schema.columns tb where table_schema = 'apg_oa' ");
        sqlSd.append(" and table_name =?;");
        return sqlSd.toString();
    }


    public static void setColumns(String[] columns) {
        if (!openComonnProperty || DbUtil.columns == null) {
            DbUtil.columns = columns;
        }
    }

    public static void setTabs(String[] tabs) {
        if (!openComonnProperty || DbUtil.tabs == null) {
            DbUtil.tabs = tabs;
        }
    }

    public static void setColumnsAnnotation(String[] columnsAnnotation) {
        if (!openComonnProperty || DbUtil.columnsAnnotation == null) {
            DbUtil.columnsAnnotation = columnsAnnotation;
        }
    }
}
