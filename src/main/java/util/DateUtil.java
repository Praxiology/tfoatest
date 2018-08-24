package util;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    @Test
    public void test_db_date() {
        DbSource.init();
        Record record = Db.findFirst(" select * from oa_finance_timed_task where execute_time = ?","2018-08-10" );
        System.err.printf("%s\n" , record != null ? record.toJson() : "时间为空");
    }


    @Test
    public void dateUtilTest() {
        //判断是否是每月的同一天
        boolean yrw = isConform(2 , Timestamp.valueOf("2000-03-30 17:55:55"));
        boolean jrw = isConform(3 , Timestamp.valueOf("2000-03-30 17:55:55"));
        boolean nrw = isConform(4 , Timestamp.valueOf("2000-03-29 17:55:55"));
        System.err.printf("y:%b\n " , yrw);
        System.err.printf("j:%b\n " , jrw);
        System.err.printf("n:%b" , nrw);
    }

    @Test
    public void yearMounth() {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        today.get(Calendar.MONTH);
        today.get(Calendar.YEAR);
        System.err.printf("%d,%d" , today.get(Calendar.MONTH) , today.get(Calendar.YEAR));
    }


    //判断当前时间与指定时间比较
    public static boolean isConform(int type , Timestamp timestamp) {

        if (null == timestamp) {
            return false;
        }
        // type=> 2: 月度;3:季度 4:年度
        // 当前
        Calendar today = new GregorianCalendar();
        today.setTime(Timestamp.valueOf("2000-02-28 17:55:55"));
        //任务执行的哪一天
        Calendar someday = new GregorianCalendar();
        someday.setTime(timestamp);

        //获得月中的当天数值
        int tdm = today.get(Calendar.DAY_OF_MONTH);
        int sdm = someday.get(Calendar.DAY_OF_MONTH);
        if (2 == type) {//月度任务判断,同天
            // 获得本月最后一天
            int lastDay = today.getActualMaximum(Calendar.DATE);
            if (tdm == lastDay && sdm > tdm) {
                // 指定日期(天)数值大于当前月最后一天,且当前天就是最后一天,
                // 则返回true,可以发短信提醒用户
                return true;
            }
            return tdm == sdm;
        } else {//季/年度任务判断,同月同天
            // 获得 月数 (由于底层原因,月份数值需要+1才和实际值相同)
            int sm = 1+someday.get(Calendar.MONTH);
            int tm = 1+today.get(Calendar.MONTH);
            if (tm == sm) {// 同月
                if (tdm == sdm) {//同天
                    return true;
                }
                // 以防万一:2月单独处理(闰年2月有29天),此情况出现概率极小
                // 任务是2月29号,本2月只有28天,顾返回true,可以发短信给用户
                if (2 == tm && sdm == 29 && tdm == 28) {
                    return true;
                }
            }
            return false;
        }
    }
}
