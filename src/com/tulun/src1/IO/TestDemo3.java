package com.tulun.src1.IO;

import java.io.File;
import java.util.Scanner;

/**
 * 1、如何删除多级文件夹
 * aaa/bbb/ccc  aaa/a.txt  aaa/ddd
 * 2、从键盘上输入一个路径，打印出这个路径下所有.java文件
 */

public class TestDemo3 {
    public static void remove(File file){
        if(file == null || !file.exists()){
            return;
        }

        if(file.isFile()){
            file.delete();
        }
        deleteDir(file);
    }
    public static void deleteDir(File file){
        File[] files = file.listFiles();
        //递归的终止条件
        if(files == null){
            //表示当前文件夹时一个空的文件夹
            //满足终止条件时的处理方法
            return;
        }
        //相同的逻辑
        //遍历该文件的对象的集合，判断如果该文件对象是一个文件则
        //直接删除，反之则递归调用
        for(File f: files){
            if(f.isFile()){
                f.delete();
            }else{
                deleteDir(f);
            }
        }
        //删除空的文件夹
        file.delete();
    }

    public static void printJavaFile(File dir){
        if(dir == null || ! dir.exists()){
            return;
        }
        //获取该目录所有的文件对象
        File[] subFile = dir.listFiles();
        //判断文件对象集合是否为空，即该目录是否是一个空目录
        if(subFile == null){
            return;
        }
        //遍历文件对象集合，打印以.java结尾的文件对象
        for(File f: subFile){
            if(f.isFile() && f.getName().endsWith(".java")){
                System.out.println(f);
            }else{
                printJavaFile(f);
            }
        }
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个文件夹路径：");
        String path = scan.nextLine();

    }
}
