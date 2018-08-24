package util.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CopyFile {

    public static void main(String[] args) {
        try {
            FileUtils.copyDirectory(new File("E:\\workspace\\tomcate\\20180822\\"),new File("E:\\base\\tomcat\\demo\\"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
