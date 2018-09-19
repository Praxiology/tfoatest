package util.tom;

import sun.misc.Launcher;
import util.PathUtil;

import java.lang.reflect.Method;
import java.net.URL;

public class TestClass {

    public static void main(String[] args) {
        run4();
    }

    public static void run4() {

        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {
               // Thread.currentThread().setContextClassLoader();
                (new ThreadClassLoader()).start();
            }
        });
        td.setContextClassLoader(new MyClassLoader());
        td.start();
    }




    public static void run3() {
        try {
            TestClass.class.getClassLoader().getResource("");
            Class<?>  obj = TestClass.class.getClassLoader().loadClass("E:\\temp\\log\\OaTest");
            if (obj != null) {
                Object o = obj.newInstance();
                Method run2 = o.getClass().getDeclaredMethod("alloc",null);
                System.err.printf("ty:%s\n" , run2.getReturnType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run2() {
        MyClassLoader classLoader = new MyClassLoader();
        TestClass.class.getClassLoader();
       // classLoader.setRoot("E:\\workspace\\APG02\\work\\tfoatest\\target\\classes\\");
        classLoader.setRoot(PathUtil.getAppPath(TestClass.class));

        Class<?> testClass = null;
        try {
            testClass = classLoader.loadClass("util.tom.TestClass");
            Object object = testClass.newInstance();
            Method run2 = object.getClass().getDeclaredMethod("run2",null);
            //run2.invoke(object,new Object[0]);
            System.out.printf("run2:%s\nappCld:%s\n", run2.getName(),object.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void run() {
        ClassLoader loader = TestClass.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }

    }


}
