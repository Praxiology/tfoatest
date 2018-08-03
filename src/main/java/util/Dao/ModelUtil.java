package util.Dao;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import util.DbUtil;
import util.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 获得表字段对应POJO属性
 **/
public class ModelUtil {

    public static ModelUtil dao = new ModelUtil();

    public ModelUtil() {
        DbUtil.init();
        DbUtil.setColumns(new String[]{"column_name" , "data_type" , "column_comment"});
        DbUtil.setTabs(new String[]{"oa_finance_report"});
    }

    @Test
    public void run() {
        String sql = DbUtil.getSql();
        for (String tb : DbUtil.tabs) {
            List<Record> list = Db.find(sql , tb);
            //recordToDtoSet(list);
            recordToModelSet(list);
           // recordToDtoPojo(list);
            //recordToJsonFromTable(list);
            //recordToDtoPojo(list);
            //recordToGetPojo(list);
        }
    }

    public void recordToJsonFromTable(List<Record> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuilder sd = new StringBuilder();
            List<Object> objs = new ArrayList<>();
            String column = null;
            String cc = null;
            for (Record record : list) {
                cc = record.getStr("column_comment");
                column = record.getStr("column_name");
                objs.add(StrUtil.humpTrans(column));
                objs.add(cc);
                sd.append("{'?':'?'},");
            }
            System.err.println(StrUtil.replace(sd , objs));
        }
    }

    public void recordToModelSet(List<Record> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuilder sd = new StringBuilder();
            String dt = null;
            String column = null;

            for (Record record : list) {
                dt = record.getStr("data_type");
                column = record.getStr("column_name");
                sd.append("model.set(\"");
                sd.append(column);
                sd.append("\",record.get");
                addDataType(sd , dt);
                sd.append("(\""+column+"\"));\n");
            }
            System.err.println(sd);
        }
    }

    public void recordToDtoSet(List<Record> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuilder sd = new StringBuilder();
            String dt = null;
            String column = null;
            for (Record record : list) {
                dt = record.getStr("data_type");
                column = record.getStr("column_name");
                sd.append("dto.set");
                sd.append(StrUtil.humpDbToSet(column));
                sd.append("(record.get");
                addDataType(sd , dt);
                sd.append("(\"");
                sd.append(column);
                sd.append("\"));\n");
            }
            System.err.println(sd);
        }
    }

    public void recordToDtoPojo(List<Record> list) {

        if (CollectionUtils.isNotEmpty(list)) {
            StringBuilder sd = new StringBuilder();
            String dt = "";
            for (Record record : list) {
                sd.append("private ");
                dt = record.getStr("data_type");
                getDataType(sd,dt);
                sd.append(StrUtil.humpDbToPoJo(record.getStr("column_name")));
                sd.append(";//");
                sd.append(record.getStr("column_comment"));
                sd.append("\n");
            }
            System.err.println(sd);
        }
    }

    public void recordToGetPojo(List<Record> list) {

        if (CollectionUtils.isNotEmpty(list)) {
            StringBuilder sd = new StringBuilder();
            String dt = "";
            String column_name = "";
            for (Record record : list) {
                dt = record.getStr("data_type");
                column_name=record.getStr("column_name");
                String column_name2 = StrUtil.humpDbToPoJo(column_name);
                getDataType(sd,dt);
                sd.append(" ");
                sd.append(column_name2);
                sd.append(" = value.get");
                addDataType(sd,dt);
                //sd.append(StrUtil.bigFristWord(column_name));
                sd.append("(\"");
                sd.append(column_name);
                sd.append("\");//");
                sd.append(record.getStr("column_comment"));
                sd.append("\n");
            }
            System.err.println(sd);
        }
    }

    public void getDataType(StringBuilder sd , String dt) {
        if ("bigint".equals(dt)) {
            sd.append("Long ");
        } else if ("varchar".equals(dt)) {
            sd.append("String ");
        } else if ("tinyint".equals(dt)) {
            sd.append("Integer ");
        } else if ("datetime".equals(dt)) {
            sd.append("String ");
        } else if ("double".equals(dt)){
            sd.append("Double ");
        }
    }

    public void addDataType(StringBuilder sd , String dt) {
        if ("bigint".equals(dt)) {
            sd.append("Long");
        } else if ("varchar".equals(dt)) {
            sd.append("Str");
        } else if ("tinyint".equals(dt)) {
            sd.append("Int");
        } else if ("datetime".equals(dt)) {
            sd.append("Str");
        }else if ("double".equals(dt)){
            sd.append("Double");
        }
    }


}
