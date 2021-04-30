package com.tulun.src1.Net.AIO.Handler;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    private ByteBuffer sendBuffer;
    private AsynchronousSocketChannel socketChannel;

    public ReadHandler(AsynchronousSocketChannel result) {
        this.socketChannel = result;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        System.out.println(new String(attachment.array(), 0, result));
        sendBuffer = ByteBuffer.allocate(1024);
        String str="server receive your meesage";
        sendBuffer.put(str.getBytes());
        sendBuffer.flip();
        socketChannel.write(sendBuffer, sendBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                socketChannel.read(attachment,attachment,new ReadHandler(socketChannel));
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {

    }
}
