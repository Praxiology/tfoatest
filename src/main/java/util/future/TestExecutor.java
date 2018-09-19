package util.future;

import java.util.concurrent.*;

public class TestExecutor {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("主线程执行");
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> result = executor.submit(new CountTask());
        System.out.println("主线程....");
        executor.shutdown();
        Integer i = result.get();
        System.out.println("主线程结束");
        System.out.println("主线程获取子线程结果为:" + i);

    }

    public static class CountTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("子线程开始计算");
            Thread.sleep(10000);
            System.out.println("子线程结束计算，共用时10秒");
            return 100;
        }

    }
}
