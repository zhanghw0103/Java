package com.tulun.src1.Thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程的优先级
 * 每个线程都有优先级，优先级默认为5，优先级为1～10
 * Java线程优先级继承于创建它的线程
 *
 * setPriority(int priority)
 * getPriority()
 *
 * ThreadGroup用于对于一批线程进行管理
 *
 * 问题：当前代码子线程为什么没有继承主线程的优先级？
 *
 * 注意：优先级只增加抢到cpu执行权的概率
 *
 * 博客：
 * 线程和进程基本认识
 * 线程的创建
 * 线程六状态以及转换
 * 线程中常用的方法
 */

public class TestDemo6 {
    public static void main(String[] args) {
        Thread subThread = new Thread("子线程"){
            @Override
            public void run() {
                for(int i=1; i<=100; i++){
                    System.out.println(Thread.currentThread().getName()+":: "+i);
                }
            }
        };
        subThread.setPriority(Thread.MAX_PRIORITY);
        subThread.start();
        System.out.println(subThread.getName()+"的优先级为:: "+subThread.getPriority());
        System.out.println(Thread.currentThread().getName()+"的优先级为:: "+Thread.currentThread().getPriority());
        for(int i=1; i<=100; i++){
            System.out.println(Thread.currentThread().getName()+":: "+i);
        }
//        Thread thread = new Thread(){
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName()+ ":: "+Thread.currentThread().getPriority());
//            }
//        };
//        Thread.currentThread().setPriority(6);
//        System.out.println(Thread.currentThread().getName() + " begin");
//        System.out.println(Thread.currentThread().getName() + ":: "+Thread.currentThread().getPriority());
//        thread.start();
//        System.out.println(Thread.currentThread().getName() + " end");
    }
}
