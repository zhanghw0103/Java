package com.tulun.src1.Net.AIO.Handler;

import com.tulun.src1.Net.AIO.Server.ServerAIO;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel,ServerAIO> {
    private ByteBuffer readBuffer;

    @Override
    public void completed(AsynchronousSocketChannel result, ServerAIO attachment) {
        attachment.getServerSocketChannel().accept(attachment,this);
        readBuffer=ByteBuffer.allocate(1024);
        result.read(readBuffer,readBuffer,new ReadHandler(result));
    }

    @Override
    public void failed(Throwable exc, ServerAIO attachment) {

    }
}
