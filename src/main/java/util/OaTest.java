package util;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class OaTest {

    public OaTest() {
        DbUtil.init();
    }


    @Test
    public void test_x() {

    }

    public static void alloc() {
        byte[] b = new byte[2];
        b[0] = 1;
    }

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);
    }


}
