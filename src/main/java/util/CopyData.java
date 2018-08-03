package util;

import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CopyData {

    /**
     * SELECT * from oa_finance_report_formula_value fv where fv.report_id=5;
     * SELECT * from oa_finance_report_relation rr where rr.report_id = 5;
     *
     * INSERT INTO `apg_oa`.`oa_finance_model_relation`
     * (`id`, `model_id`, `project`, `unit`, `cell_code`, `value`, `model_attach_colspan`,
     * `model_attach_rowspan`, `model_attach_x`,
     * `model_attach_y`, `status`, `readOnly`, `mark`)
     * VALUES
     * ('11882', '9', NULL, NULL, NULL, 'Q', '2', '1', '5', '6', '1', NULL, NULL);
     *
     *
     *
     * **/

    @Test
    public void run(){
        DbUtil.init();
        String us = "update oa_finance_model_relation";

       /* String relationSal = "SELECT * from oa_finance_report_relation rr where rr.report_id = 5";
        List<Record> list = Db.find(relationSal);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Record r : list) {
                System.err.printf("re : %s\n" ,JsonKit.toJson(r));
                Record inRe = new Record();
                inRe.set("model_id",9);
                inRe.set("project",r.getLong("project"));
                inRe.set("unit",r.getLong("unit"));
                inRe.set("cell_code",r.getLong("cell_code"));
                inRe.set("value",r.getStr("value"));
                inRe.set("model_attach_rowspan",r.getInt("colspan"));
                inRe.set("model_attach_colspan",r.getInt("rowspan"));
                inRe.set("model_attach_y",r.getInt("col_y"));
                inRe.set("model_attach_x",r.getInt("row_x"));
                inRe.set("readOnly",r.getInt("readOnly"));
                inRe.set("mark",r.getStr("mark"));
                inRe.set("status",1);
               // Db.save("oa_finance_model_relation",inRe);
            }
        }*/
    }
}
