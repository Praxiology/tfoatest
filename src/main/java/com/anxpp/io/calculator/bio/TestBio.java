package com.anxpp.io.calculator.bio;

import java.io.IOException;
import java.util.Random;

/**
 * 测试方法报告
 * 1:Socket,客户端向服务端发送第一次数据请求时,服务端会接收到两次请求
 *   1:第一次是底层的链接确认
 *   2:第二次是实际业务的请求
 * @author
 * @version 1.0
 */
public class TestBio {
    //测试主方法
    public static void main(String[] args) throws InterruptedException {
        //运行服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerBetter.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        char operators[] = {'+' , '-' , '*' , '/'};
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @SuppressWarnings("static-access")
                @Override
                public void run() {
                    while (true) {
                        //随机产生算术表达式
                        String expression = random.nextInt(10)+""+operators[random.nextInt(4)]+(random.nextInt(10)+1);
                        Client.send(expression+"+"+Thread.currentThread().getId());
                        try {
                            Thread.currentThread().sleep(random.nextInt(1000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }
    }
}