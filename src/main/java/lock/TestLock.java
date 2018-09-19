package lock;

public class TestLock {
    //锁方法是不是代表锁所在的对象?
    int a = 0;

    public synchronized void md1() {
        /*synchronized (this) {
            System.err.printf("1_1:%s\n" , a);
            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    md2();
                }
            });
            t3.start();*/
        md2();
        System.err.printf("1_1:%s\n" , a);
        int sharedState = 0;
        while (sharedState < 300000000) {
            int former = sharedState++;
            int latter = sharedState;
            a++;
            if (former != latter-1) {
            }
        }
        System.err.printf("1_2:%s\n" , a);
    }


    public synchronized void md2() {
        System.err.printf("2_1:%s\n" , a);
        int sharedState = 0;
        while (sharedState < 200000000) {
            int former = sharedState++;
            int latter = sharedState;
            a++;
            if (former != latter-1) {
            }
        }
        System.err.printf("2_2:%s\n" , a);
    }

    public static void main(String[] args) {
        TestLock lock = new TestLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.md1();
            }
        });

        /*Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.md2();
            }
        });*/
        t1.start();
        //t2.start();
    }
}
