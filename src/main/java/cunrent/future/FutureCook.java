package cunrent.future;

import java.util.concurrent.*;

public class FutureCook {

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        // 第一步 网购厨具
        Callable<Chuju> onlineShopping = new Callable<Chuju>() {

            @Override
            public Chuju call() throws Exception {
                System.out.println("第一步：下单");
                System.out.println("第一步：等待送货");
                Thread.sleep(10000L);  // 模拟送货时间
                System.out.println("第一步：快递送到");
                return new Chuju();
            }

        };

        // 第二步 去超市购买食材
        Callable<Shicai> markShopping = new Callable<Shicai>() {
            @Override
            public Shicai call() throws Exception {
                System.out.println("第二步：去超市");
                System.out.println("第二步：买食材");
                Thread.sleep(10000L);  // 模拟送货时间
                System.out.println("第二步：从超市到家");
                return new Shicai();
            }

        };
        //
        FutureTask<Shicai> ShicaiTask = new FutureTask<Shicai>(markShopping);
        FutureTask<Chuju> Chujutask = new FutureTask<Chuju>(onlineShopping);

        //不断循环判断厨具是否准备好
        Thread askChuJuTask = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    do {
                        Thread.sleep(100L);
                    } while (!Chujutask.isDone());
                    System.err.printf("%s\n" ,"厨具准备好了" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //不断循环判断食材是否准备好
        Thread asKShicaiTask = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    do {
                        Thread.sleep(100L);
                    } while (!ShicaiTask.isDone());
                    System.err.printf("%s\n" ,"食材准备好了" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //不断循环判断食材是否准备好
        Thread asKTask = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    do {
                        Thread.sleep(300L);
                    } while (askChuJuTask.isAlive() || asKShicaiTask.isAlive());
                    System.err.printf("%s\n" ,"都准备好了" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //交给线程池处理
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(Chujutask);
        service.submit(ShicaiTask);
        service.submit(askChuJuTask);
        service.submit(asKShicaiTask);
        service.shutdown();

        while (!service.isTerminated()){
            System.err.printf("%s\n" , "没有准备好");
            Thread.sleep(1000L);
        }
        System.err.printf("总共用时:(%s)ms,%s\n" , System.currentTimeMillis()-startTime,"准备好,开始展示厨艺");
    }

    // 厨具类
    static class Chuju {
    }

    // 食材类
    static class Shicai {
    }

}