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
     * <p>
     * INSERT INTO `apg_oa`.`oa_finance_model_relation`
     * (`id`, `model_id`, `project`, `unit`, `cell_code`, `value`, `model_attach_colspan`,
     * `model_attach_rowspan`, `model_attach_x`,
     * `model_attach_y`, `status`, `readOnly`, `mark`)
     * VALUES
     * ('11882', '9', NULL, NULL, NULL, 'Q', '2', '1', '5', '6', '1', NULL, NULL);
     **/

    @Test
    public void run() {
        DbSource.init();
        copyRelation();
    }

    public static void copyRelation() {
        String relationSal = "SELECT * from oa_finance_report_relation where report_id=54";
        List<Record> list = Db.find(relationSal);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Record record : list) {
                Record model = new Record();
                model.set("model_id" , 54);
                model.set("project" , record.getLong("project"));
                model.set("unit" , record.getLong("unit"));
                model.set("cell_code" , record.getLong("cell_code"));
                model.set("value" , record.getStr("value"));
                model.set("model_attach_colspan" , record.get("rowspan"));
                model.set("model_attach_rowspan" , record.get("colspan"));
                model.set("model_attach_x" , record.get("row_x"));
                model.set("model_attach_y" , record.get("col_y"));
                model.set("status" , 1);
                model.set("readOnly" , record.getInt("readOnly"));
                model.set("mark" , record.getStr("mark"));
                Db.save("oa_finance_model_relation" , model);
            }
        }

    }
}
