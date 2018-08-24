package util;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * 数字类型的工具
 */
public class NumberUtil {

    public static boolean equalsIntegerTest() {
        BigDecimal bd = new BigDecimal("123.123");
        bd.toString();
        Integer a = new Integer(2);
        Integer b = new Integer(2);
        return a.equals(b);
    }

    public static boolean equalsLong(Long longV1 , Long longV2) {
        if (null == longV1 && null == longV2) {
            return true;
        }
        if (null != longV1) {
            return longV1.equals(longV2);
        }
        return false;
    }

    @Test
    public void test() {

        System.err.printf("%b" , equalsIntegerTest());

        // Long v1 = null;
        // Long v2 = 3l;

        // System.err.println(equalsLong(v1 , null));

        //String lv = "123";

        //System.err.printf("%s,%d",v2.toString(),Long.valueOf(lv));
        // System.err.println(equalsLong(v1,Long.valueOf(lv)));

        //System.err.println(lv.equals(v1.toString()));


        //System.err.println(lv.equals(String.valueOf(v1)));


        //String _d1 = "1.20";
        // String _d2 = "1.2";
        //Double _v = 0.00d;
        //System.err.printf("%b" , _v.equals(Double.valueOf(_d1)-Double.valueOf(_d2)));
    }


    @Test
    public void test_x1() {


        String[] arr1 = {"u1" , "u2"};
        String[] arr2 = {"c1"};

        int _unit = 0;

        for (; ; ) {
            int _a = _unit % 2;
            int c_index = ((_a == 1) ? (_unit-1) : _unit) / 2;
            if (4 != c_index) {
                System.err.printf("_unit:%d,_a:%d,c_index:%d\n" , _unit , _a , c_index);
                _unit++;
            } else {
                break;
            }
        }



       /* for (String a1 : arr1) {
            System.err.printf("arr1:%s,arr2:%s\n" , arr1[_unit++] , arr2[((_unit % 2 != 1) ? (_unit-1) : _unit) / 2]);
        }*/
    }

    @Test
    public void test_x2() {

        System.err.printf("%d,%d" , 2 / 2 , 2 % 2);

    }


}
