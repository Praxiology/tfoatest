package util.tom;

import java.io.*;

public class ThreadClassLoader extends Thread {

    @Override
    public void run() {
        //判断类上下文加载器,是不是我们指定的加载器
        System.err.printf("clName:%s\n" ,Thread.currentThread().getContextClassLoader().getClass().getName() );
    }

    public static void main(String[] args) {
        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {
                (new ThreadClassLoader()).start();
            }
        });
        //设置线程上下文类加载器
        td.setContextClassLoader(new MySelfClassLoader());
        td.start();
    }
}

class MySelfClassLoader extends ClassLoader {

    private String root;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name , classData , 0 , classData.length);
        }
    }

    private byte[] loadClassData(String className) {
        String fileName = root+File.separatorChar+className.replace('.' , File.separatorChar)+".class";
        try {
            InputStream ins = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer , 0 , length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
