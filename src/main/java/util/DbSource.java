package util;

import com.alibaba.druid.pool.DruidDataSource;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;

import java.sql.SQLException;
import java.util.Properties;

/**
 * 适应多个数据源
 */
public class DbSource {
    private static DruidDataSource dataSource = null;
    public static String[] mySqlInfos = null;

    static {
        mySqlInfos = getDFInfor();
        init(mySqlInfos);
    }

    public DbSource() {
        init();
    }
    public static String[] getDFInfor(){
        return new String[]{
            "druid.url=jdbc:mysql://stg2.v5time.net:3366/apg_oa?useUnicode=true&characterEncoding=utf-8" ,
                    "druid.name=tfte" ,
                    "druid.password=tt0fsdaefst123yQb" ,
                    "druid.username=tfte"
        };
    }

    public static void init() {
        init(mySqlInfos);
    }

    public static void init(String[] dbInfos) {
        if (null == dataSource) {
            Properties properties = new Properties();
            if (dbInfos == null)
                dbInfos = mySqlInfos;
            for (String str : dbInfos) {
                String[] props = str.split("=");
                properties.setProperty(props[0] , props[1]);
            }
            dataSource = new DruidDataSource();
            dataSource.configFromPropety(properties);
            try {
                dataSource.init();
                DbKit.addConfig(new Config("tablesInfo" , dataSource));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
       // DbSource.init();
        System.err.printf("%s" , Db.queryStr("select version()"));
    }
}
