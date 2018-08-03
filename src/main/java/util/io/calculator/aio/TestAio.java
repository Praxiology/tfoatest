package util.io.calculator.aio;

import util.io.calculator.aio.client.ClientAio;
import util.io.calculator.aio.server.ServerAio;
import org.junit.Test;

import java.util.Scanner;

public class TestAio {

    @Test
    public void test_aio() throws Exception{
        //运行服务器
        ServerAio.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        ClientAio.start();
        System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while(ClientAio.sendMsg(scanner.nextLine()));
    }
}
