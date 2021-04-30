package com.tulun.src1.Thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 悲观锁、乐观锁
 * 悲观锁：总是假设最坏的情况，每一次去拿数据都默认别人会修改，所以每次拿数据
 * 都会上锁，这样就会导致有其他人想要拿数据就会阻塞直到获取到这把锁。
 * synchornized关键字的实现是悲观锁
 * 悲观锁机制存在的问题：
 * 1）多线程竞争下，加锁、解锁都会导致比较多的上下文切换和调度延时，引起性能问题
 * 2）一个线程池有锁会导致其他需要此锁的线程阻塞
 * 3）数据量大时，独占锁回导致效率低下
 * 4）如果一个优先级高的线程等待一个优先级低的线程释放锁会导致优先级导致，引起性能
 * 问题
 *
 * 乐观锁：每次那数据都默认认为别人不会修改，所以不会上锁，在更新的时候判断再次期间
 * 有没有人修改过这个数据。乐观锁适用于多读的场景，这样可以提高吞吐量。
 * 乐观锁的实现方式：
 * 1）CAS
 * CAS操作包含有三个操作数：
 * 需要读写的内存位置（V）
 * 进行比较的预期值（A）
 * 将写入的新值（B）
 *
 * Java对CAS的支持：
 * jdk1.5之后新增java.util.concurrent.atomic包下的AtomicXXX类都是
 * 建立在CAS的基础之上，在性能上都会有很大的提升
 *
 * AtomicInteger
 * i++ 包含三个独立操作，非原子操作
 * getAndIncrement
 *
 * 课堂练习：
 * value1 没有进行任何线程安全的保护
 * value2 使用乐观锁 CAS
 * value3 使用悲观锁 synchronized
 * 运行1000个线程，同时对value1，2，3进行自增操作，看下最终的结果
 *
 * unsafe是用来帮助Java访问操作系统底层资源的类，如分配释放、释放内存
 * volatile int value 保证可见性和有序性
 * AtomicInteger中，volatile和CAS一起保证线程安全性
 *
 * 2）版本号机制
 */
public class TestDemo9 {
    private static int value1 = 0; //线程不安全
    private static AtomicInteger value2 = new AtomicInteger(0);//CAS
    private static int value3 = 0; //synchornized

    private static synchronized void increaseValue3(){
        value3++;
    }

    public static void main(String[] args) {
        for(int i=0; i<1000; i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    value1++;
                    value2.getAndIncrement();
                    increaseValue3();
                }
            }.start();
        }
        //查看活跃线程数目
        //活跃数目要大于2，主要原因是idea工具的原因，会有一个monitor线程用于监控
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("线程不安全："+value1);
            System.out.println("乐观锁："+value2);
            System.out.println("悲观锁："+value3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
