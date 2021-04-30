package com.tulun.src1.Net.AIO.Server;

import com.tulun.src1.Net.AIO.Handler.AcceptHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;

public class ServerAIO {
    private int port = 5676;
    private AsynchronousServerSocketChannel serverSocketChannel;


    public AsynchronousServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }
    public ServerAIO() {
        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServerAIO() {
        serverSocketChannel.accept(this,new AcceptHandler());

    }



}
