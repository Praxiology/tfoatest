package com.anxpp.io.calculator.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class HandleUtil {

    public static HandleUtil handle = new HandleUtil();

    public void handleSelect(Handler handler,Selector selector){
        try{
            //无论是否有读写事件发生，selector每隔1s被唤醒一次
            selector.select(1000);
            //阻塞,只有当至少一个注册的事件发生的时候才会继续.
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            SelectionKey key = null;
            while(it.hasNext()){
                key = it.next();
                it.remove();
                try{
                    handler.handleInput(key);
                }catch(Exception e){
                    if(key != null){
                        key.cancel();
                        if(key.channel() != null){
                            key.channel().close();
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }

    }
    //异步发送消息
    public void doWrite(SocketChannel channel , String request) throws IOException {
        //将消息编码为字节数组
        byte[] bytes = request.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区的字节数组
        channel.write(writeBuffer);
        //****此处不含处理“写半包”的代码
    }


}
