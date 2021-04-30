package com.tulun.src1.Thread;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 复习：
 * 守护线程 setDamen(true);
 * 线程的生命周期
 * 新课：
 * 线程常用方法
 * 1）start() 用来启动一个线程 将其添加一个线程组当中 此时线程就会处于
 * Runnable就绪状态
 * 2）sleep() Thread.sleep()
 * sleep方法使得当掐按线程指定毫秒级的休眠，暂停执行，不会放弃monitor
 * 锁的使用权
 * Thread A： monitor lock   sleep
 * Thread B:  期望获取monitor lock
 * jdk1.5之后，引入枚举类型TimeUnit,对sleep方法对其进行了封装
 * 省去了时间单位换算的步骤
 *    TimeUnit.HOURS.sleep(3);
 *    TimeUnit.MINUTES.sleep(27);
 *    TimeUnit.SECONDS.sleep(8);
 * 3）yield() 属于启发式的方法
 * 线程A.yield（）,会提醒调度器线程A愿意放弃本次的cpu资源，如果cpu资源
 * 不紧张，处理器有可能会忽略这种提示
 * 面试题：yield()和sleep()的区别
 * 4) join()
 * 含义：thread B中调用threadA.join（），此时thread B进入到等待状态，
 * 直到当前threadA结束自己的生命周期或者达到join方法的超时时间。
 *
 * 课堂练习：t1先输出0～9, t2输出，主线程输出
 *
 * 5) 实现线程中断的方法
 * a.interrupt() 将java线程当中的中断状态位置为true
 * thread A : sleep()/join()/wait throw InterruptedException 可中断方法
 * 以上方法都会使得当前进入阻塞状态，另外一个线程调用被阻塞线程的interrupt方法会
 * 打断当前的这种阻塞状态，抛出一个InterruptedException的异常，这样的方法称之为
 * 可中断方法。 并不是结束当前被阻塞线程的生命周期，只是打断了当前线程的阻塞状态。
 * thread B : thread A对象.interrupt()
 * b.isInterrupted() 判断中断状态位是否位true
 * c.interrupted() 判断中断状态位是否为true
 * 区别在于interrupted方法调用之后会擦除掉线程的interrupt标识
 *
 * 6) wait/notify/notifyAll
 * wait调用该synchornized同步代码块或方法当中，使得当前线程进入阻塞
 * notify/notifyAll唤醒当前的阻塞
 * Object类中的方法
 */

public class TestDemo5 {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while(true){
                    System.out.println(Thread.interrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
//        System.out.println(thread.isInterrupted());
//        thread.interrupt();
//        System.out.println(thread.isInterrupted());
    }
}

//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                    System.out.println("子线程运行结束");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("主线程运行结束");

//        Thread t1 = new Thread("t1"){
//            @Override
//            public void run() {
//                for(int i=0; i<10; i++){
//                    System.out.println("thread: "+Thread.currentThread().getName()+", "+i);
//                }
//                try {
//                    TimeUnit.MILLISECONDS.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        Thread t2 = new Thread("t2"){
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println("thread: " + Thread.currentThread().getName() + ", " + i);
//                }
//                try {
//                    TimeUnit.MILLISECONDS.sleep(100);
//                } catch (InterruptedException e) {
//
//                }
//            }
//        };
//        try {
//            t1.start();
//            t1.join();
//            t2.start();
//            t2.join();
//            for(int i=0; i<10; i++){
//                System.out.println("thread: "+Thread.currentThread().getName()+", "+i);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Thread thread = new Thread(){
//            @Override
//            public void run() {
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    System.out.println("I am be interrupted");
//                }
//            }
//        };
//        thread.start();
//        try {
//            TimeUnit.MILLISECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread.interrupt();