package com.anxpp.io.calculator.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public interface Handler {

     void handleInput(SelectionKey key) throws IOException;

   // void doWrite(SocketChannel channel , String request) throws IOException;
}
