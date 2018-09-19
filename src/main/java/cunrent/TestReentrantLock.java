package cunrent;

import java.util.Calendar;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

    public static void main(String[] args) {
        //testLock1();
        testLock();
    }

    public static void testLock1() throws Exception {
        ReentrantLockThread thread = new ReentrantLockThread();
        new Thread(thread).start();
        new Thread(thread).start();
        new Thread(thread).start();
    }

    public static void testLock() {
        TestLock tester = new TestLock();
        try {
            // 测试可重入，方法testReentry() 在同一线程中,可重复获取锁,执行获取锁后，显示信息的功能
            tester.testReentry();
            // 能执行到这里而不阻塞，表示锁可重入
            tester.testReentry();
            // 再次重入
            tester.testReentry();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放重入测试的锁，要按重入的数量解锁，否则其他线程无法获取该锁。
           // tester.getLock().unlock();
            //tester.getLock().unlock();
            tester.getLock().unlock();
        }

        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    tester.testReentry();
                }
            }).start();
        } catch (Exception e){

        }

        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        Calendar now = Calendar.getInstance();
                        System.out.println(now.getTime()+" "+Thread.currentThread().getName()+" try un lock.");
                        //tester.getLock().unlock();
                        if(tester.getLock().isLocked()){

                            tester.getLock().unlock();
                        }
                    }
                }
            }).start();
        } catch (Exception e){

        }

    }

}

class TestLock {
    /**
     * 可以重入
     */
    private ReentrantLock lock = null;

    public TestLock() {
        // 创建一个自由竞争的可重入锁
        lock = new ReentrantLock();
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void testReentry() {
        Calendar now = Calendar.getInstance();
        System.out.println(now.getTime()+" "+Thread.currentThread().getName()+" try get lock.");
        lock.lock();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(now.getTime()+" "+Thread.currentThread().getName()+" get lock.");
    }

}

class ReentrantLockThread implements Runnable {

    /**
     * 测试基本用法
     */

    // 创建一个ReentrantLock对象
    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            // 使用lock()方法加锁
            lock.lock();
            for (int i = 0; i < 30; i++) {
                System.out.println(Thread.currentThread().getName()+"输出了：  "+i);
            }
        } finally {
            // 别忘了执行unlock()方法释放锁
            lock.unlock();
        }

    }
}
