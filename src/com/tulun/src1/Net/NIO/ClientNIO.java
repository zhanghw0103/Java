package com.tulun.src1.Net.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Set;

public class ClientNIO {
    private SocketChannel socketChannel;
    private Selector selector;
    private ByteBuffer recBuffer;
    private ByteBuffer sendBuffer;
    private int port = 5676;

    public ClientNIO() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        //建立连接和打开socketChannel
        recBuffer = ByteBuffer.allocate(1024);
        sendBuffer = ByteBuffer.allocate(1024);
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", port));
    }


    public void startClientNIO() {
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
        if (key.isValid() && key.isConnectable()) {  //是否可以建立连接
            //说明服务器已经处于运行态
//            SocketChannel clientChannel = (SocketChannel) key.channel();
//            clientChannel.finishConnect();
//            clientChannel == socketChannel;
            boolean b = socketChannel.finishConnect();  //完成连接的建立
            if (b) {  //只有b返回true之后连接才算建立完成
                //告诉selector监听写事件
                //发出请求
                String str = "client send hello";
                sendBuffer.put(str.getBytes());
                sendBuffer.flip();
                socketChannel.write(sendBuffer);
                socketChannel.register(selector, SelectionKey.OP_READ);
                //读事件： 当消息发送过来就触发
            }
        } else if (key.isValid() && key.isReadable()) {
            // System.out.println("***********************");
            recBuffer.clear();
            int read = socketChannel.read(recBuffer);
            // System.out.println("read: " + read);
            //要保证clientChannel就是发送数据的用户对应的channel
            System.out.println(new String(recBuffer.array(), 0, read));
            //服务器回复消息
            sendBuffer.clear();
            String str = "client send hello";
            sendBuffer.put(str.getBytes());
            sendBuffer.flip();
            socketChannel.write(sendBuffer);


        }
    }

    public static void main(String[] args) {
        try {
            new ClientNIO().startClientNIO();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
