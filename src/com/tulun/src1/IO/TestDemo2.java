package com.tulun.src1.IO;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

/**
 * java.io包下的类和接口支持Java中IO
 *
 * IO是我们要实现的目的，为了达到这样一个目的，需要有一种机制，这种机制就是
 * 流机制，数据流是一串连续不断的数据的集合
 *
 * IO指的是一组有顺序的、有起点的（程序），有终点的（外部设备）字节数据的集合，
 * 是对数据传输的抽象
 * 流的分类
 * 1）按照流向：分为输入流和输出流
 * 输入流指的是从数据源读取数据到程序的过程
 * 输出流指的是从程序将数据流向数据源的过程
 * 2）按照传输的类型：分为字符流和字节流
 * 字符流可以处理字符，字节流可以处理字节，处理任意类型的对象
 * 字符流：将原始数据解析为字符的序列，是会依赖当前平台的编码方式，字符流的输入和输出
 * 需要进行编码和解码
 * 字节流：将数据解析为二进制数据，读写均为字节数据
 * 效率比较高，二进制数据不需要进行编码和解码，可移植性更高一些，与主机的编码方式无关
 * 总结：字符流和字节流的区别
 * a.读写单位 b.处理对象 c.处理效率 d.可移植性
 * 3）按照角色：分为节点流和处理流(装饰器设计模式)
 * 节点流指的是程序直接相连得到的数据源
 * 处理流指的是在已存在的流之上做了一层封装，通过封装之后的类来实现读取/写入的功能
 *
 * 字节流
 * 字节流有两大类 InputStream OutputStream
 * InputStream & FileInputStream 文件输入流
 * 构造函数 FileInputStream(String name)
 *         FileInputStream(File file)
 * 成员方法 int read()
 *         int read(byte b[])
 *         int read(byte b[], int off, int len)
 *
 * 注意：
 * a.FileInputStream在构造对象，有可能抛出文件找不到的异常
 * b.read()表示每次读取一个字节的数据，把字节转换为int返回
 * 面试题：read读取的是一个字节，为什么返回的是int，而不是byte?
 * -1 ->byte  1111 1111
 * -1 ->int 1111 1111 1111 1111 1111 1111 1111 1111
 * c.文件流使用完之后，要关闭，避免占用资源
 *
 * OutputStream & FileOutputStream 文件输出流
 * 课堂练习：使用文件输出流写入一些数据到某一个文件当中
 */
public class TestDemo2 {
    public static void main(String[] args){
        FileOutputStream fos = null;
        try {
            fos  = new FileOutputStream("b.txt");
            //fos.write(33);
            byte[] bytes = {33, 34, 35, 36, 37, 38};
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        File file = new File("./a.txt");
//        if(!file.exists()){
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(file);
//            //从输入流中读取数据
////            int tmp;
////            while((tmp = fis.read()) != -1){
////                System.out.println(tmp);
////            }
//            byte[] bytes = new byte[fis.available()];//返回当前文件字节数
//            fis.read(bytes);
//            System.out.println(Arrays.toString(bytes));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                fis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}


//得到系统默认的编码方式
//        Charset charset = Charset.defaultCharset();
//        System.out.println(charset);//utf-8
//
//        System.out.println("===================");
//        //当前系统支持所有的编码方式
//        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
//        Iterator<Map.Entry<String, Charset>> iterator = stringCharsetSortedMap.entrySet().iterator();
//        while(iterator.hasNext()){
//            Map.Entry<String, Charset> entry = iterator.next();
//            System.out.println(entry.getKey()+":: "+entry.getValue());
//        }