package com.tulun.src1.IO;

import java.io.*;

/**
 * 使用RandomAccessFile在指定位置追加“西安图论软件科技有限公司”
 * RandomAccessFile不能直接去追加内容
 * 1）移动文件指针到指定位置后，复制指定位置之后的所有内容
 * 2）在指定位置追加内容
 * 3）复制文件的内容追加到文件之后
 */
public class TestDemo11 {
    public static void main(String[] args) {
        RandomAccessFile raf = null;
        File tmp = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            tmp = File.createTempFile("tmp", null);
            raf = new RandomAccessFile("b.txt", "rw");

            //需要让文件指针移动到该位置
            int pos = (int)(Math.random() * raf.length());
            raf.seek(pos);

            //复制指定位置之后的所有内容到tmp文件中
            fis = new FileInputStream(tmp);
            fos = new FileOutputStream(tmp);

            byte[] buf = new byte[256];
            int hasRead = 0;
            while((hasRead = raf.read(buf)) > 0){
                fos.write(buf, 0, hasRead);
            }

            //在指定位置追加内容
            raf.write("西安图论软件科技有限公司".getBytes());


            //复制tmp文件中的内容到该文件之后
            while((hasRead = fis.read(buf)) > 0){
                raf.write(buf, 0 , hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
