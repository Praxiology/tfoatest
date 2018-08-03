package com.anxpp.io.calculator.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.anxpp.io.file.Calculator;
import com.anxpp.io.file.Close;

public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            String info = socket.toString()+""+Thread.currentThread().toString();
            //System.err.println(info);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream() , true);
            String expression;
            String result = null;
            while (true) {
                if ((expression = in.readLine()) == null) {
                    System.out.println("服务端接收的请求为空:"+info);
                    //
                    break;
                }
                try {
                    result = Calculator.Instance.cal(expression).toString();
                } catch (Exception e) {
                    result = "计算结果异常"+e.getMessage();
                }
                out.println(expression+":"+result+":"+info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Close.self.closed(in , out , socket);
        }
    }
}