package util;

import org.junit.Test;

import java.io.File;

public class PathUtil {

    public static String getAppPath() {
        return getAppPath(null);
    }

    public static String getAppPath(Class cls) {
        return getAppPath(cls,null);
    }

    public static String getAppPath(Class cls , String appName) {
        if (cls == null) {
            cls = PathUtil.class;
        }
        String path2 = cls.getResource("").getPath();
        if (null != appName) {
            int start = path2.indexOf(appName);
            path2 = path2.substring(0 , start)+appName;
        } else {
            String packagePath = File.separator+cls.getPackage().getName().replace("." , File.separator)+File.separator;
            path2 = path2.replace(packagePath , "");
        }

        return path2;
    }


    @Test
    public void testP() {
        System.err.println(getAppPath());
    }

}
