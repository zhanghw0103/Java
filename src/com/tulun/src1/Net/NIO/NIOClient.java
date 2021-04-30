package com.tulun.src1.Net.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOClient {
    private SocketChannel socketChannel;
    private FileChannel fileChannel;
    // private Selector selector;
    private ByteBuffer recBuffer;
    private ByteBuffer sendBuffer;
    private int port = 5676;

    public NIOClient() throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", port));
        //建立连接和打开socketChannel
        recBuffer = ByteBuffer.allocate(1024);
        sendBuffer = ByteBuffer.allocate(1024);
        fileChannel = FileChannel.open(Paths.get(
                "D:\\JavaProject\\Java\\a.txt"),
                StandardOpenOption.READ);
    }

    public void startClient() throws IOException {
        while (fileChannel.read(sendBuffer) != -1) {
            sendBuffer.flip();
            socketChannel.write(sendBuffer);
            sendBuffer.clear();
        }

        //等待接收服务的回值
        int len = -1;
        while ((len = socketChannel.read(recBuffer)) != -1) {
            System.out.println(new String(recBuffer.array(), 0, len));
            recBuffer.clear();
        }

        //关闭所有的资源

    }

    public static void main(String[] args) {
        try {
            new NIOClient().startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
