package com.tulun.src1.IO;

import java.io.*;

/**
 * 字节流 InputStream OutputStream
 * FileInputStream & FileOutputStream
 * 课堂练习：
 * 拷贝图片或音频文件
 * 图片1 -》 读取图片1 -》 写入图片1的副本 完成拷贝
 *
 * 0001 1100 1000 0000 0000 0000 0001 1000
 * 0001 1100   输入流读取一个字节  输出流写入一个字节到副本当中
 * 1000 0000
 * a.一个字节一个字节拷贝
 * b.定义缓存数组进行拷贝
 */
public class TestDemo4 {
    public static void main(String[] args) {
        //定义一个缓存数组去拷贝
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // 从源文件中读取字节
            fis = new FileInputStream("a.jpg");
            //写入字节到目标文件
            fos = new FileOutputStream("b1.jpg");
            byte[] bytes = new byte[1024];
            int tmp = -1;
            //从源文件中读取数据带缓存数组当中去
            //read(byte[]) return 实际读取的字节数
            while((tmp = fis.read(bytes)) != -1){
                //写入缓存数组当中的数组到目标文件中
                fos.write(bytes, 0, tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入字节到目标文件
//        //一个字节一个字节去拷贝
//        //从源文件中读取字节
//        FileInputStream fis = new FileInputStream("a.jpg");
//        //写入字节到目标文件
//        FileOutputStream fos = new FileOutputStream("b1.jpg");
//        //从输入流对象中读取字节
//        //int tmp = fis.read();//只读取一个字节
//        //通过输出流对象写入字节
//        //fos.write(tmp);
//        //从输入流对象中读取字节
//        int tmp = -1;
//        while((tmp = fis.read()) != -1){
//            //通过输出流对象写入字节
//            fos.write(tmp);
//        }
//        fis.close();
//        fos.close();
    }
}