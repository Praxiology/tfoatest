package util.file;

import org.apache.commons.io.FileUtils;
import java.io.File;

public class CopyFile {

    public static void main(String[] args) {
        try {
            String from = "";
            String to = "";
            FileUtils.copyDirectory(new File(from),new File(to));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
