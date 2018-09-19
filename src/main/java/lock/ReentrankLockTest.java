package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrankLockTest {

    public static void main(String[] args) {
        Demo13.runTest();
    }

}

/*
ReentrantLock,比synchronized轻量灵活强大的锁
 */
class Demo13 extends Thread {

    @Override
    public void run() {

        new DB().buy();

    }

     static void runTest() {
        Demo13 t1 = new Demo13();
        Demo13 t2 = new Demo13();
        Demo13 t3 = new Demo13();
        Demo13 t4 = new Demo13();
        Demo13 t5 = new Demo13();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

}

class DB {
    private static Lock lock = new ReentrantLock();// 用static表示该锁是该对象实例共享的
    static int i = 5;// 用static表示该变量是对象实例共享的

    public void buy() {
        //lock.lock();
        i--;
        System.out.println(i);
       // lock.unlock();

    }
}
