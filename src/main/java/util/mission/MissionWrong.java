package util.mission;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.junit.Test;
import util.DbSource;

import java.util.List;

public class MissionWrong extends DbSource {


    @Test
    public void test_x(){
        String sql = "SELECT id from oa_finance_report where mission_id = 50";



    }

}
