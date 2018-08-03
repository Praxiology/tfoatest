package util.model;

import org.junit.Test;

public class TempTest {

    @Test
    public void testIF() {
        String name = "2";
        int age = 2;
        int result = 1;
        if ("1".equals(name) || 2 == age) {
            result = 2;
        } else if ("2".equals(name)) {
            result = 3;
        }

        System.err.println(result);

    }


}
