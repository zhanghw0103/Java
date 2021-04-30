package com.tulun.src1.IO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 回顾：
 * 1）什么样的对象可被序列化？
 * 对象所在类实现Serializable接口
 * 2）序列化过程：
 * a.创建一个流，ObjectOutputStream，这个输出流它是属于处理流，
 * 在节点流的基础上进行了封装
 * b.调用流对象的writeObject方法将对象写入磁盘中的文件
 * 3）反序列化的过程：
 * a.创建一个流，ObjectInputStream, 这个输入流它是属于处理流，
 * 在节点流的基础上进行了封装
 * b.调用流对象的readObject方法将对象对应的字节流读取出来，将其转换
 * 为所需要的对象
 * ClassNotFoundException
 * ArrayList中序列化与反序列化
 * ArrayList重写writeObject和readObject
 * 原因：ArrayList底层基于一个动态数组实现，每次在数组满后都会自动进行扩容，
 * 如果当前数组的长度为100，此时只放入了一个元素，使用默认的writeObject和
 * readObject就会使其序列化99个null元素，为了保证序列化的时候不会将无效
 * 元素进行序列化，首先将ArrayList底层存储元素的容器被transient关键字修饰，
 * 所以重写了writeObject和readObject去序列化ArrayList当中的有效元素
 *
 * transient作用：
 * 被transient关键字修饰的变量在序列化过程中不会被序列化的
 *
 * ObjectOutputStream如何调用到ArrayList类中的writeObject？
 * ObjectOutputStream.writeObject()-> writeObject0()->
 * writeOrdinaryObject -> writeSerialData -> invokeWriteMethod
 * -> writeObjectMethod.invoke(obj, new Object[]{})
 * 通过反射机制实现
 *
 */
public class TestDemo9 {
    public static void arrayListSerial(){
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("tulun");
        list.add("education");
        //ObjectOutputStream oos = null;
        try {
            //序列化过程
            //输出流对象
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stringList.txt"));
            //调用流对象的writeObject将当前对象写入到磁盘的文件中
            oos.writeObject(list);
            oos.close();

            //反序列化过程
            //输入流对象
            File file = new File("stringList.txt");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            //调用流对象的readObject读取当前对象的字节流
            List<String> newList = (List<String>)ois.readObject();
            System.out.println(newList);
            ois.close();
            if(file.exists()){
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        arrayListSerial();
    }
}
