package util.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadFile {



    @Test
    public void test_x() {
        try {
            String path = "";
            StringBuilder sd = new StringBuilder();
            sd.append("StringBuilder sd = new StringBuilder();");
            Properties prop = new Properties();
            prop.load(new FileInputStream(new File(path)));
            prop.forEach((k,v)->{
                sd.append("sd.append(\""+k+"="+v+"\");\n");
            });
            System.err.printf("%s\n" ,sd.toString() );
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
