package com.tulun.src1.Net.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOServer {
    private int port = 5676;
    private ServerSocketChannel serverChannel;
    private FileChannel fileChannel;
    // private Selector selector;
    private ByteBuffer recBuffer;
    private ByteBuffer sendBuffer;

    public NIOServer(){
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(port));
            recBuffer = ByteBuffer.allocate(1024);
            sendBuffer = ByteBuffer.allocate(1024);
            fileChannel = FileChannel.open(Paths.get(
                    "D:\\JavaProject\\Java/a.txt"),
                    StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startNIOServer() throws IOException {
        SocketChannel socketChannel = serverChannel.accept();
        //TCP 建立连接  三次握手   三次握手的终点
//            socketChannel请求连接的用户一一对应
        //后面讲使用该socketChannel进行和这个用户之间的通信
//                new Thread(new ServerHandler(clientSocket)).start();
        while (socketChannel.read(recBuffer) !=-1){
            recBuffer.flip();
            fileChannel.write(recBuffer);
            recBuffer.clear();
        }
        //接收客户端的数据

        //给客户端恢复信息
        sendBuffer.put("服务器已经收到文件".getBytes());
        sendBuffer.flip();
        socketChannel.write(sendBuffer);

        socketChannel.close();
        fileChannel.close();
        serverChannel.close();
    }

    public static void main(String[] args) {
        try {
            new NIOServer().startNIOServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
