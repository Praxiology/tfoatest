package util.table;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.junit.Test;
import util.DbSource;

import java.util.*;

public class Member extends DbSource {

    @Test
    public void test_mobile(){
        String mobile1 = "13345678";
        Integer i = 100;
        String sql = "SELECT  om.company_id,om.id from oa_member om  where om.login_name like 'cw_%' order by om.company_id";
        Map<Long,String> map = new HashMap<>();
        List<Record> list = Db.find(sql);
        for (Record record : list) {
            Long cid = record.getLong("company_id");
            String mob = map.get(cid);
            if (mob == null) {
                mob = mobile1+i.toString();
                map.put(cid,mob) ;
                i++;
            }
            Db.update("update oa_member set mobile = 13515659329 where id = ? ","'13515659329'",record.getLong("id"));
        }
    }


    @Test
    public void test_update(){
        String sql = "SELECT om.* from oa_member om where om.login_name like 'cw_%'";
        List<Record> list = Db.find(sql);
        for (Record record : list) {
            String name = record.getStr("name");
            if (name.contains("集团")||name.contains("传媒")||name.contains("集团")||name.contains("集团")) {
                Db.update("update oa_member set mobile = 15345678910  where id = ? ",record.getLong("id"));
            }
        }
    }

    @Test
    public void test_x() {
        Set<String> cnames = new HashSet<>();
        //导出财务人员信息
        String sql = "SELECT oc.`name` cname, om.* from oa_member om LEFT JOIN oa_company oc ON om.company_id = oc.id where om.login_name like 'cw_%' order by om.company_id";
        List<Record> list = Db.find(sql);
        StringBuilder sd = new StringBuilder();
        for (Record record : list) {
            String cname = record.getStr("cname");
            if (cnames.add(cname)){
                sd.append("\n\n公司:"+cname+"\n"+record.getStr("login_name")+" 角色:"+record.getStr("name")+"\n");
            } else {
                sd.append(record.getStr("login_name")+" 角色:"+record.getStr("name")+"\n");
            }
        }
        System.err.printf("%s" , sd.toString());
    }
}
