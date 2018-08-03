package util.model;

import java.util.List;

/**
 * 通过继承这个类,获得简易操作数据库的方法
 * */
public abstract class Table {

    private String TbName;

    public void setTbName(String tbName) {
        TbName = tbName;
    }

    public List<Result> queryBySql(String selectSql){
        return null;
    }


}
