package com.tulun.src1.Net.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        try {
            //创建SocketChannel通道
            SocketChannel socketChannel = SocketChannel.open();

            //设置socketChannel为非阻塞
            socketChannel.configureBlocking(false);

            //实例化复用器
            Selector selector = Selector.open();

            //连接服务端,该connect不会阻塞，会立即返回 boolean true:连接成功  false：表示还未连接成功
            if (!socketChannel.connect(new InetSocketAddress("127.0.0.1", 6666))) {
                //表示连接不成功 当前正在连接,将当前的可连接事件交给内核帮助监听
                socketChannel.register(selector, SelectionKey.OP_CONNECT);

                //等待连接完成
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    if (selectionKey.isConnectable()) socketChannel.finishConnect();
                }
            }

            //要么是连接成功
            //给服务端发送数据

            String msg = "hello\n";
            ByteBuffer byteBuffer = ByteBuffer.allocate(msg.length());
            //将数据写入到Buffer中
            byteBuffer.put(msg.getBytes());
            //读写模式切换
            byteBuffer.flip();

            //将数据从 Buffer中写入channel通道，对于Buffer而言，是读取数据
            socketChannel.write(byteBuffer);


            //关闭资源
            selector.close();
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}