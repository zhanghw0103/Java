package com.tulun.src1.IO;

import java.io.*;

/**
 * 转换流和缓冲流 （高级流）
 * 装饰器模式 - 设计模式
 *
 * 转换流
 * 输入流 -》 实际是字节流通向字符流的桥梁  InputStreamReader
 * 输出流 -》 实际是字符流通向字节流的桥梁  OutputStreamWriter
 *
 * OutStreamWriter可以指定编码表，将流中字符按照编码表转换为字节
 * InputStreamReader可以指定编码表读取字节将其按照编码解析为字符
 *
 * 缓冲流
 * 以字节流为例，BufferedInuputStream/BufferedOutputStream,
 * BufferedInputStream内置一个缓冲数组，其会一次性读取8192字节
 * 的数据存入缓冲区当中，BufferedOutStream直接从缓冲区中拿到数据，
 * 写入到磁盘
 *
 * 课堂练习：
 * 拷贝一本书，使用4种方式进行拷贝
 * 1）使用字节流一个字节一个字节去拷贝
 * 2）使用缓冲数组去拷贝
 * 3）使用缓冲流一个字节一个字节去拷贝
 * 4）使用缓冲流+缓冲数组去拷贝
 *
 * 1）3）
 * 2）4）
 */
public class TestDemo7 {
    //采用字节流，一个一个字节去拷贝
    public static void method1(String src, String dest){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);

            //读数据
            int len;
            while((len = fis.read()) != -1){
                //写数据
                fos.write(len);
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
    }
    //采用字节流，一次多个字节拷贝，即使用缓冲数组
    public static void method2(String src, String dest){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);

            //读数据
            int len;
            byte[] buf = new byte[1024];
            while((len = fis.read(buf)) != -1){
                //写数据
                fos.write(buf, 0, len);
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
    }
    //采用缓冲流，一次一个字节的拷贝
    public static void method3(String src, String dest){
        BufferedInputStream bis = null; //缓冲输入流
        BufferedOutputStream bos = null;//缓冲输出流
        try {
            bis = new BufferedInputStream(new FileInputStream(src));
            bos = new BufferedOutputStream(new FileOutputStream(dest));

            //读数据
            int len;
            while((len=bis.read() ) != -1){
                bos.write(len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //采用缓冲流，一次多个字节的拷贝，即使用缓冲数组
    public static void method4(String src, String dest){
        BufferedInputStream bis = null; //缓冲输入流
        BufferedOutputStream bos = null;//缓冲输出流
        try {
            bis = new BufferedInputStream(new FileInputStream(src));
            bos = new BufferedOutputStream(new FileOutputStream(dest));

            //读数据
            int len;
            byte[] buf = new byte[1024];
            while((len=bis.read(buf) ) != -1){
                bos.write(buf, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String src = "/Users/lvting/Desktop/深入理解Java虚拟机.pdf";
        String dest1 = "/Users/lvting/Desktop/深入理解Java虚拟机1.pdf";
        String dest2 = "/Users/lvting/Desktop/深入理解Java虚拟机2.pdf";
        String dest3 = "/Users/lvting/Desktop/深入理解Java虚拟机3.pdf";
        String dest4 = "/Users/lvting/Desktop/深入理解Java虚拟机4.pdf";
//        method1(src, dest1);//耗时
//        method2(src, dest2); //680ms
//        method3(src, dest3);//2182ms
        method4(src, dest4);//280ms
        long endTime = System.currentTimeMillis();
        System.out.println("耗时时间："+(endTime-startTime));
    }
}

//    InputStreamReader isr;
//    OutputStreamWriter osw;
//    {
//        try{
//            //创建一个转换输入流，将字节流转换为字符流
//            isr = new InputStreamReader(new FileInputStream("a.txt"), "utf-8");
//            //使用转换输入流读取字节流中字节
//            int tmp = -1;
//            while((tmp = isr.read()) != -1){
//                System.out.println((char)tmp);
//            }
//
//            //创建一个转换输出流，将字符流转换为字节流
//            osw = new OutputStreamWriter(new FileOutputStream("b.txt"), "utf-8");
//            osw.write("你好");//写入字符
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally{
//            try {
//                isr.close();
//                osw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }