package util.io.calculator.bio;

import util.io.file.Close;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 阻塞式I/O创建的客户端
 *
 * @author yangtao__anxpp.com
 * @version 1.0
 */
public class Client {
    //默认的端口号
    private static int DEFAULT_SERVER_PORT = 12345;
    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void send(String expression) {
        send(DEFAULT_SERVER_PORT , expression);
    }

    public static void send(int port , String expression) {
        //System.out.println("算术表达式为："+expression);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String _null = null;
        try {
            // new Socket的时候,就会和服务端产生连接
            socket = new Socket(DEFAULT_SERVER_IP , port);
           // in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           // out = new PrintWriter(socket.getOutputStream() , true);
           // out.println(expression);
            //System.out.println(expression+"___结果为："+in.readLine());
            ///out.println(_null);
            //System.out.println(expression+"___结果为2："+in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Close.self.closed(in , out , socket);
        }
    }
}