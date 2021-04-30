package com.tulun.src1.IO;

import java.io.*;
import java.util.ArrayList;

/**
 * Java中的序列化与反序列化
 *
 * 类的对象会随着程序的结束而被销毁，如何在不重新创建对象
 * 的情况下调用当前类
 * 对象的序列化是一个将对象转换为字节流的过程，可以将其永久的
 * 保存在磁盘上或者在网络中传输到其他任何地方；反之，就将磁盘
 * 上的字节流恢复成对象的过程称之为反序列化。
 *
 * 面试题：什么是序列化和反序列化，序列化的目的是什么？
 *
 * 如何使得对象被序列化？
 * 使得对象所在的类实现Serializable接口，表示当前这个类
 * 可序列化, Serializable是一个标记接口，意味着当前接口不含邮
 * 任何属性或者方法，仅仅是用来标记可序列化
 *
 * serialVersionUID
 * 标识符，当使用对象时将这个序列化ID标记在对象上，表示当前
 * 这个对象是一个可序列化的对象，同时也可以通过序列化工具找到
 * 当前的对象的serialVersionUID
 *
 * 实现序列化的步骤：
 * 1) 创建一个ObjectOutputStream对象
 * 2）写到磁盘上调用writeObject()
 *
 * 实现反序列化的步骤：
 * 1）创建一个ObjectInputStream对象
 * 2）从磁盘上读取当前的二进制流并将其转换为一个Person对象则调用readObject()
 */

class Person implements Serializable{
    private String name;
    private int age;

    public Person(String name, int age){
        System.out.println("实例化对象");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class TestDemo8 {
    public static void main(String[] args) {
        //序列化Person对象
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("a.txt"));
            Person p = new Person("tulun", 18);
            oos.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //反序列出Person对象
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("a.txt"));
            Person p = (Person)ois.readObject();
            System.out.println(p.getName()+":: "+p.getAge());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
