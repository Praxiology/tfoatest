package cunrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestCondition {
    public static void main(String[] args) {
        final AtomicInteger integer = new AtomicInteger();
        final BoundedBuffer boundedBuffer = new BoundedBuffer();
        for (int i = 0; i < 20; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("t1 run");
                    //for (int i=0;i<100;i++) {
                    try {
                        Thread.sleep(10L);
                        boundedBuffer.put(integer.incrementAndGet());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // }

            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("t2 run");
                    //for (int i=0;i<100;i++) {
                    try {
                        Thread.sleep(1000L);
                        Object val = boundedBuffer.take();
                        System.out.println(val);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // }

            });

            t1.start();
            t2.start();
        }
    }

}
class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[5];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
       System .out.println(Thread.currentThread().getName()+"put wait lock");
        lock.lock();//获得当前对象的锁
       System.out.println(Thread.currentThread().getName()+"put get lock");
        try {
            while (count == items.length) {
                System.out.println(Thread.currentThread().getName()+"buffer full, please wait");
                notFull.await();//阻塞当前线程
            }

            items[putptr] = x;
            if (++putptr == items.length)
                putptr = 0;
            ++count;
            notEmpty.signal();//唤醒其他线程
        } finally {
            System.out.println(Thread.currentThread().getName()+"put un lock");
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"take wait lock");
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"take get lock");
        try {
            while (count == 0) {
                System.out.println(Thread.currentThread().getName()+"no elements, please wait");
                notEmpty.await();//阻塞当前线程
            }
            Object x = items[takeptr];
            if (++takeptr == items.length)
                takeptr = 0;
            --count;
            notFull.signal();//唤醒其他线程
            return x;
        } finally { System.out.println(Thread.currentThread().getName()+"take un lock");
            lock.unlock();
        }
    }
}
