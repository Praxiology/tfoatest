package util.model;

import org.junit.Test;

public class formulaUtil {


    @Test
    public void run() {
        String formula = "[[100104,208201],[year,month]]";
        //=TQ[[100104],[208204,-208204],[year,month]]
        String[] arr = formula.replace("[" , "").replace("]" , "").split(",");
        for (String ar : arr) {
            System.err.printf("%s\n" , ar);
        }
    }

}
