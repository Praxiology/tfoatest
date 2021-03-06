package util.file;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

public class DeleteFileUtil {

    public static final String path = "";

    @Test
    public void test_delete_file() throws Exception {
        File dir = new File(path);
        //FileUtils.deleteDirectory(dir);
        getChileDirFilesAndRemove(dir , ".java");
    }

    public static void delPath() {
        String[] paths = {"E:\\base\\" ,
                "E:\\temp\\spring5\\" ,
                "E:\\temp\\tomcate\\" ,
                "E:\\gow\\","D:\\Go\\"};
    }

    public static File getChileDirFilesAndRemove(File file , String patten) throws Exception {
        if (file != null) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File fe : files) {
                    // System.err.printf("%s\n" ,fe.getCanonicalFile().getName() );
                    if (fe.isDirectory()) {
                        getChileDirFilesAndRemove(fe , patten);
                    } else {
                        fe.deleteOnExit();
                    }
                }
            }
        }
        return null;
    }

    @Test
    public void test_df() {
        File dir = new File("E:\\workspace\\APG02\\test\\oatest\\oatest\\src\\main\\java\\com");
        dir.deleteOnExit();
    }
}
