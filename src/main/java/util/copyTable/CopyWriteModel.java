package util.copyTable;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import util.DbUtil;

import java.util.List;

//1:查询报表模板记录
//2:去除ID主键,新增一条记录
//3:获得新增记录的主键
//4:查询原记录的模板配置信息
//5:去除主键ID,变更模板ID
public class CopyWriteModel {

    public static void main(String[] args) {
        DbUtil.init();
        foreachHandle();
    }

    public static void foreachHandle() {
        for (Long id : oldModelIds) {
            oldModelId = id;
            saveSql();
           // deleteSql();
        }
    }

    private static Long[] oldModelIds = {
            //38L,34L,33L,
           // 31L ,32L ,37L ,
            56L,
            -1L};//必填
    private static Long oldModelId = null;//
    private static Long newModelId = null;
    private static String newModelName = null;
    private static String write_model = " oa_finance_write_model ";
    private static String model_relation = " oa_finance_model_relation ";
    private static String model_formula = " oa_finance_model_formula ";

    private static String sqlModel = "select * from  "+write_model+"  where id = ?";
    private static String sqlRelation = "select * from  "+model_relation+"  where model_id = ?";
    private static String sqlFormula = "select * from  "+model_formula+"  where model_id = ?";

    private static String deleteSqlModel = "Delete from  "+write_model+"  where id = ?";
    private static String deleteSqlRelation = "Delete from  "+model_relation+"  where model_id = ?";
    private static String deleteSqlFormula = "Delete from  "+model_formula+"  where model_id = ?";

    public static void deleteSql() {
        Db.update(deleteSqlModel , oldModelId);
        Db.update(deleteSqlRelation , oldModelId);
        Db.update(deleteSqlFormula , oldModelId);
    }

    public static void saveSql() {
        handleModel();
        handleRelation();
        handleFormula();
    }

    public static void handleModel() {
        Record from = Db.findFirst(sqlModel , oldModelId);
        if (null == from) {
            return;
        }
        from.remove("id");
        newModelName = from.getStr("model_name")+"_2";
        from.set("model_name" , newModelName);
        Db.save(write_model , from);
        newModelId = from.getLong("id");
        System.err.printf("%s" , newModelId.toString());
    }

    public static void handleRelation() {
        List<Record> from = Db.find(sqlRelation , oldModelId);
        if (null == from) {
            return;
        }
        for (Record record : from) {
            record.set("model_id" , newModelId);
            record.remove("id");
            if (newModelName != null && "templateName".equals(record.getStr("mark"))) {
                record.set("value" , newModelName);
                newModelName = null;
            }
            Db.save(model_relation , record);
        }
    }

    public static void handleFormula() {
        List<Record> from = Db.find(sqlFormula , oldModelId);
        if (null == from) {
            return;
        }
        for (Record record : from) {
            record.set("model_id" , newModelId);
            record.remove("id");
            Db.save(model_formula , record);
        }
    }
}
