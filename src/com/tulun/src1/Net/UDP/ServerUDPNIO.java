package com.tulun.src1.Net.UDP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;


public class ServerUDPNIO {
    private int port = 5676;
    private DatagramChannel serverChannel;//(1)UDP开发的工具包  （2）具有通道本身功能
    private Selector selector;
    //实现非阻塞模式的关键
    private ByteBuffer recBuffer;
    private ByteBuffer recBuffer1;
    private ByteBuffer sendBuffer;

    public ServerUDPNIO() {
        try {
            selector = Selector.open();
            serverChannel = DatagramChannel.open();
//            serverChannel.socket();//
            serverChannel.bind(new InetSocketAddress(port));
            serverChannel.configureBlocking(false); // 将channel置为非阻塞模式
            recBuffer = ByteBuffer.allocate(1024);
            sendBuffer = ByteBuffer.allocate(1024);
            recBuffer1 = ByteBuffer.allocate(1024);
            //udp协议  不需要建立连接
            serverChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServerUDPNIO() {
        try {
            while (true) {
                int select = selector.select();//返回发生的事件个数  阻塞方法   5
                System.out.println(select);
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
        if (key.isValid() && key.isReadable()) {
            recBuffer.clear();
            DatagramChannel clientChannel = (DatagramChannel) key.channel();
            InetSocketAddress address = (InetSocketAddress) clientChannel.receive(recBuffer);
            //返回值：客户端地址 address :IP 和port 的整合
//            InetAddress address1 = address.getAddress();
            System.out.println(new String(recBuffer.array(), 0, recBuffer.limit()));
            sendBuffer.clear();
            String str = "server recive your msg";
            sendBuffer.put(str.getBytes());
            sendBuffer.flip();
            clientChannel.send(sendBuffer, address);
        }
    }

    public static void main(String[] args) {
        new ServerUDPNIO().startServerUDPNIO();
    }
}
