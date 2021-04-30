package com.tulun.src1.Net.UDP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class ClientUDPNIO {
    private DatagramChannel socketChannel;
    private Selector selector;
    private ByteBuffer recBuffer;
    private ByteBuffer sendBuffer;
    private int port = 5676;
    private InetSocketAddress address;

    public ClientUDPNIO() throws IOException {
        selector = Selector.open();
        socketChannel = DatagramChannel.open();
        socketChannel.configureBlocking(false);
        //建立连接和打开socketChannel
        recBuffer = ByteBuffer.allocate(1024);
        sendBuffer = ByteBuffer.allocate(1024);
        address = new InetSocketAddress("127.0.0.1", port);
    }


    public void startClientNIO() {
        //
        try {
            String str = "client send hello";
            sendBuffer.put(str.getBytes());
            sendBuffer.flip();
            socketChannel.send(sendBuffer, address);
            System.out.println("address:" + address);
            socketChannel.register(selector, SelectionKey.OP_READ);
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
        if (key.isValid() && key.isReadable()) {
            System.out.println("--------------------------");
            recBuffer.clear();
            socketChannel.receive(recBuffer);
            System.out.println(new String(recBuffer.array(), 0, recBuffer.limit()));
            sendBuffer.clear();
            String str = "client send hello";
            sendBuffer.put(str.getBytes());
            sendBuffer.flip();
            socketChannel.send(sendBuffer, address);
        }
    }

    public static void main(String[] args) {
        try {
            new ClientUDPNIO().startClientNIO();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
