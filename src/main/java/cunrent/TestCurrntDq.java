package cunrent;

import org.junit.Test;

import java.util.concurrent.*;

public class TestCurrntDq {

    public void thP(){
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,100,50L,TimeUnit.MILLISECONDS,queue);
        executor.getPoolSize();
        ExecutorService service = Executors.newFixedThreadPool(100);//无界线程

    }

    @Test
    public void test_LinkedBlockingQueue() throws Exception{

        /**
         * mark_concurrent:接收不为空元素
         * 压栈出栈过程,是否阻塞,是否抛异常
         */
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        queue.put(new Object());
        queue.offer(new Object());
        queue.add(new Object());

        queue.poll();
        queue.take();
        queue.remove();

    }


    /**
     * mark_concurrent:接收不为空元素
     */
    @Test
    public void test_ReentrantLock(){

    }


}
