package util;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StrUtil {

    //驼峰转换
    public static String humpDbToPoJo(String column) {
        if (StringUtils.isNotBlank(column)) {

            int _start = column.indexOf("_");
            if (0 < _start) {
                char ch = column.charAt(_start+1);
                if (ch >= 'a' && ch <= 'z') {
                    ch -= 32;
                }
                return column.replace("_"+column.charAt(_start+1) , String.valueOf(ch));
            }
        }
        return column;
    }

    //
    public static String humpDbToSet(String column) {
        column = humpDbToPoJo(column);
        if (StringUtils.isNotBlank(column)) {
            return column.substring(0 , 1).toUpperCase()+column.substring(1);
        }
        return column;
    }

    //首字母大写
    public static String bigFristWord(String column) {
        return humpDbToSet(column);
    }

    //驼峰装换
    public static String humpTrans(String column) {
        return humpDbToPoJo(column);
    }

    public static StringBuilder replace(StringBuilder str , List<Object> params) {
        if (str != null && params != null && params.size() > 0) {
            int len = params.size();
            int start = 0;
            String reStr = null;
            for (int i = 0; i < len; i++) {
                int j = str.indexOf("?" , start);
                if (j > -1) {
                    reStr = params.get(i).toString();
                    str.replace(j , j+1 , reStr);
                    start = j+reStr.length();
                }
            }
        }
        return str;
    }

    //装换
    public static String replace(String str , List<Object> params) {
        return replace(new StringBuilder(str) , params).toString();
    }

    public static String replace(String str , Object... params) {
        return replace(new StringBuilder(str) , params).toString();
    }

    public static StringBuilder replace(StringBuilder str , Object... params) {
        if (null != params) {
            List<Object> objs = new ArrayList<>(params.length);
            for (Object obj : params) {
                objs.add(obj);
            }
            return replace(str , objs);
        }
        return str;
    }

    public int strLastIndexOf() {
        StringBuilder ids = new StringBuilder("1,2,");
        // System.err.println(ids.lastIndexOf(","));
        System.err.println(ids.deleteCharAt(ids.lastIndexOf(",")));
        return -1;
    }

    @Test
    public void test() {
        // System.err.println("hello".split(",").length);
        //Object[] objects = {"111111" , "2" , "3" , "4" , "5" , "6" , "7" , "88" , "9"};
        //System.err.println(replace("?a??bcde???fghijklm??n?" , objects));
        strLastIndexOf();
    }

    @Test
    public void testDataType() {
        System.err.printf("%b\n" ,"Q".equals(null) );
    }

    @Test
    public void test_x() {
        System.err.printf("%s,%s" , "hell" , 1233l);
    }

    @Test
    public void test_x_list() {

        List<Long> ls = new ArrayList<>();
        ls.add(1l);
        ls.add(1l);
        ls.add(1l);
        System.err.printf("%s" , ls.toString().replace("[" , "").replace("]" , ""));

    }

    @Test
    public void test_x_buidder() {
        StringBuilder sb = new StringBuilder();
        System.err.printf("%d" ,sb.length());
    }

}
