package util.tom;

import java.io.*;

public class ClassUtil {

    public static byte[] getClassBytes(String path){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            InputStream ins = new FileInputStream(path);
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer , 0 , length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

}
