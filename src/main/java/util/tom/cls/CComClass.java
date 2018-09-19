package util.tom.cls;

import java.util.Stack;

public class CComClass extends PComClass implements Runnable {

    private static final Object obj = new Object();

    private Stack<Object> stack = new Stack<>();

    public String CComClassGetStr() {
        return "CComClass : i am CComClass obj";
    }

    @Override
    public void run() {
        synchronized (stack) {
            stack.push(obj);
            stack.push(null);
            stack.push(-1);
            stack.push(0);
            stack.push(System.currentTimeMillis());
            stack.push(super.infGetStr());
            stack.push(super.AbsGetStr());
            stack.push(super.PComClassGetStr());
            stack.push(this.CComClassGetStr());
            stack.forEach(e->{
                System.err.printf("%s\n" ,e != null ?e.toString():null);
            });
        }
    }


    public static void main(String[] args) {
        CComClass cc = new CComClass();
        (new Thread(cc)).start();
        (new Thread(cc)).start();
        (new Thread(cc)).start();
    }


}
