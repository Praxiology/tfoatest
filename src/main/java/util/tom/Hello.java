package util.tom;

import sun.misc.ClassLoaderUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Hello {

    Hello(){
        try {
            ClassLoader cl= Hello.class.getClassLoader();
           Method md = cl.getClass().getMethod("defineClass");
           byte[] classData = ClassUtil.getClassBytes("E:\\temp\\log\\Hi.class");
            Object _obj = md.invoke(cl,classData,0,classData.length);
            if (_obj != null) {
                Object o = ((Class<?>)_obj).newInstance();
                Field field = o.getClass().getDeclaredField("name");
                System.err.printf("fd:%s\n" , field.toGenericString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   // Hi i;

    void  sayHi(){
    }

    public static void main(String[] args) {
        new Hello();
    }

}
