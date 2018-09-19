package lock;

public class ThreadSafeSample {
    public int sharedState;
    public void nonSafeAction() {
        synchronized (this){
            int a = 0;
            while (sharedState < 10000000) {
                int former = sharedState++;
                int latter = sharedState;
                if (former != latter - 1) {
                    a++;
                }
            }
            System.out.println(a);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSample sample = new ThreadSafeSample();
        Thread threadA = new Thread(){
            public void run(){
                sample.nonSafeAction();
            }
        };
        Thread threadB = new Thread(){
            public void run(){
                sample.nonSafeAction();
            }
        };
        threadA.start();
        threadB.start();
        //threadA.join();
        //threadB.join();
    }
}
