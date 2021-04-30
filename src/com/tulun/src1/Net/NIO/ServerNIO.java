package com.tulun.src1.Net.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;


public class ServerNIO {
    private int port = 5676;
    private ServerSocketChannel serverChannel;
    private Selector selector;
    //实现非阻塞模式的关键
    private ByteBuffer recBuffer;
    private ByteBuffer sendBuffer;

    public ServerNIO() {
        try {
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(port));
            serverChannel.configureBlocking(false); // 将channel置为非阻塞模式
            recBuffer = ByteBuffer.allocate(1024);
            sendBuffer = ByteBuffer.allocate(1024);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServerNIO() {
        try {
            while (true) {
                int select = selector.select();//返回发生的事件个数
                if (select > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    //发生具体的事件  对具体事件做对应处理
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        handlerKey(key);
                        iterator.remove();  //处理完的事件删除
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlerKey(SelectionKey key) throws IOException {
        // SocketChannel clientChannel = null;  //最后一个用户的clientChannel
        if (key.isValid() && key.isAcceptable()) {  //判断是否是accept事件
            SocketChannel clientChannel = serverChannel.accept();
            //肯定是立马得到返回值
            clientChannel.configureBlocking(false);
            //让selector监听读写事件
            clientChannel.register(selector, SelectionKey.OP_READ);
            //服务器主动发数据
        } else if (key.isValid() && key.isReadable()) {
            recBuffer.clear();
            //数据就已经发送过来
            SocketChannel clientChannel = (SocketChannel) key.channel();
            //发生读事件对应的channel
            int read = clientChannel.read(recBuffer);
            System.out.println(new String(recBuffer.array(), 0, read));
            //服务器回复消息
            String str = "server recive your msg";
            sendBuffer.put(str.getBytes());
            sendBuffer.flip();
            clientChannel.write(sendBuffer);
            sendBuffer.clear();
        }
    }

    public static void main(String[] args) {
        new ServerNIO().startServerNIO();
    }
}
