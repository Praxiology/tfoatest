package util.table;

import com.jfinal.plugin.activerecord.Db;
import util.DbSource;

public class ToDoList {

    public static void main(String[] args) {
        DbSource.init();
        Db.update("delete from oa_to_do_list where id in(4721) ");
    }

}
