package util.tom;

import util.PathUtil;

import java.lang.reflect.Method;

public class TestClass {

    public static void main(String[] args) {
        run2();
    }

    public static void run2() {
        MyClassLoader classLoader = new MyClassLoader();
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
