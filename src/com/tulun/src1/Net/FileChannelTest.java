package com.tulun.src1.Net;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelTest {
    public static void main(String[] args) {
//        FileInputStream f = null;
//        try {
//            f = new FileInputStream("");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        f.getChannel();
        FileChannel fileChannel = null;
        try {
            //读和写可以在同一个channel上进行
            fileChannel = FileChannel.open(Paths.get(
                    "D:\\Java\\a.txt"),
                    StandardOpenOption.APPEND,StandardOpenOption.CREATE);
//             r  rw
            String str = "hello";
            ByteBuffer buffer = ByteBuffer.allocate(str.length());
            buffer.put(str.getBytes());
            buffer.rewind();
            fileChannel.write(buffer);
//            fileChannel.read(buffer);
//            System.out.println(new String(buffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读  写  创建  追加
    }
}

