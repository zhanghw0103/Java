package com.tulun.src1.IO;

import java.io.File;
import java.io.IOException;

/**
 * IO Input Output
 * 1、基本概念
 * 文件分为文本文件 二进制文件
 * eg. 十进制199 文本文件 '1' '9' '9'  二进制文件 C7
 *
 * IO输入输出是程序所必须有的一部分，使用输入机制，允许程序
 * 读取外部数据，输出机制，允许程序写入数据到外部设备（磁盘、
 * 光盘、投影仪等）
 * 2、File类
 * File类可以通过文件路径来创建File实例
 * File file = new File("pathName");
 *
 * 路径 -》 相对路径、绝对路径
 * 绝对路径 C://xxx/xxx/xxx  /User/xxx
 * 相对路径 .表示当前目录 ..表示上一级目录
 *
 * boolean exists 表示当前路径是否存在
 * boolean isFile 判断当前路径是否是文件
 * boolean isDirectory 判断当前路径是否是一个文件夹
 * boolean isHidden  判断当前路径是否是一个隐藏文件
 * boolean delete 直接删除
 * boolean createNewFile 创建新文件
 * file.mkdir 创建目录 mkdirs
 * file.getName 获取文件名
 * file.getAbsolutePath 获取文件绝对路径
 * file.listFiles 获取当前路径下的所有文件
 */
public class TestDemo1 {
    public static void main(String[] args) {
        //练习1：创建两个文件对象，分别使用相对路径和绝对路径去创建
        File file1 = new File("./a.txt");
        File file2 = new File("/Users/lvting/b.txt");
        //练习2：检查文件是否存在，不存在创建新文件
        if(!file1.exists()){
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!file2.exists()){
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //练习3：创建单级目录 aaa/
        File file3 = new File("./aaa");
        file3.mkdir();
        //练习4：创建多级目录 aaa/bbb/ccc/
        File file4 = new File("./bbb/ccc/ddd");
        file4.mkdirs();
        //练习5：删除文件和目录
        file1.delete();
        file2.delete();
        file3.delete();
        file4.delete();//删除多级目录？
        //练习6：获取文件信息，包括文件名、文件大小、文件的绝对路径、文件的父路径
        File file = new File("./test.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(file.getName());
        System.out.println(file.length());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        System.out.println(file.getParentFile());
        //练习7：目录或文件的判断
        if(file.isFile()){
            System.out.println("它是一个文件");
        }else{
            System.out.println("它是一个目录");
        }
        //练习8：获取目录
        File dir = new File("/Users/lvting/aaa");
        dir.mkdir();

        File[] files = dir.listFiles();
        for(File f: files){
            System.out.println(f.getName());
        }
    }
}
