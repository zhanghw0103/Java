package com.tulun.src1.Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 回顾：
 * 1）并发编程的三大特性
 * 原子性、有序性、可见性
 * 2）synchronized关键字
 * 保证原子性、有序性、可见性
 * - 同步代码块
 * monitorenter
 * monitorexit
 * - 同步方法
 * ACC_SYNCHORNIZED
 * Synchornized的演变
 * 总结博客：下周五上课之前 synchronized博客总结(使用/底层/锁升级)
 *
 * volatile关键字
 * 1）保证可见性 与Java内存模型相关
 * 2）保证有序性 volatile关键字修饰变量，修饰的变量会存在一个lock;的前缀，这个前缀
 * 相当于是一个内存屏障
 * 这个内存屏障可以提供：
 * a.确保指令重排序时不会将后面的代码排到内存屏障之前
 * b.确保指令重排序时不会将前面的代码排到内存屏障之后
 * c.确保在执行到内存屏障修饰的指令时前面的代码已经全部执行完成
 * d.强制地将线程工作内存中的修改刷新到主内存当中
 * e.如果是写操作，则会导致其他线程工作内存当中的缓存数据失效
 * eg.
 * int x = 10;
 * int y = x;
 * volatile int z = 100; //内存屏障
 * int sum = y + z;
 *
 * thread A : z++; z=101; -》刷新到主内存
 * thread B : z=100 -》从主内存中获取最新的数据
 *
 * 课堂练习：创建10个线程对index++ 1000次，获取10个线程加加之后的结果
 * 注意：volatile关键字不具备保证原子性的语义，只能够禁止指令重排序，保证共享变量修改
 * 立即刷新至主内存
 */
public class TestDemo8 {
    public volatile static int index = 0;
    private static CountDownLatch countDownLatch = new CountDownLatch(10);//创建一个给定当前等待线程数目的countdownlatch对象

    public static void increase(){
        index++;//非原子操作
    }

    public static void main(String[] args) {
        for(int i=0; i<10; i++){
            new Thread(){
                @Override
                public void run() {
                    for(int i=0; i<1000; i++){
                        increase();
                    }
                    //将CountDownLatch中的计数器-1
                    countDownLatch.countDown();
                }
            }.start();
        }
        try {
            //调用await使得当前线程阻塞，直到计数器减为0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(index);
    }
}


//    private volatile static int initValue = 0; //1
//    private final static int MAX = 5; //2
//    public static void main(String[] args) {
//        new Thread("reader") {
//            //initValue副本
//            @Override
//            public void run() {
//                int localValue = initValue;
//
//                while (localValue < MAX) {
//                    if (initValue != localValue) {
//                        System.out.println("The initValue is updated to " + initValue);
//                        localValue = initValue;
//                    }
//                }
//            }
//        }.start();
//
//        new Thread("updater") {
//            @Override
//            public void run() {
//                //initValue副本
//                int localValue = initValue;
//
//                while (localValue < MAX) {
//                    System.out.println("The initValue is changed " + (++localValue));
//                    initValue = localValue;
//                    //短暂休眠，目的为了让reader线程立即获取最新的initValue，输出
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
//    }
