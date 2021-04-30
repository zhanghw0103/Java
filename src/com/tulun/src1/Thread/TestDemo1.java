package com.tulun.src1.Thread;

/**
 * 吕婷
 * 1、进程和线程的基本认识
 * 2、并发和并行的区别
 * 3、创建线程
 * 4、常用的方法，线程状态，线程状态转换
 * 5、线程安全
 * 6、volatile（可见性、有序性）
 * 7、锁 synchronized ReentrantLock
 * 8、锁的级别 锁升级
 * 9、死锁 检测 避免 恢复
 * 10、线程通信
 * 11、wait/notify/notifyAll  await/signal/signaAll
 * 12、生产者消费者模型
 * 13、原子类 AtomicXXX
 * 14、阻塞队列
 * 15、线程池
 *
 * 深入理解Java虚拟机 12、13章
 * Java核心技术卷I 14章
 * Java并发编程艺术 通读
 *
 * JVM一个进程，JVM中需要有很多线程支持其运行
 * 串行 -》 批处理 -》进程（并发执行）-》线程
 *
 * 进程是一个正在执行的程序，一个程序可以同时执行多个任务（线程）。
 * 进程独占内存空间，保持各自运行状态，相互之间不会干扰，进程是并发
 * 执行程序在执行过程资源分配和管理的基本单位（资源分配的最小单位）。
 * 每个进程都有自己的独立的地址空间，每启动一个进程，系统就会分配地址
 * 空间。
 * 通常，每一个任务称作一个线程，线程有时候会被称为轻量级的额进程，
 * 它是程序执行的最小单位，一个进程可以拥有多个线程，多个线程之间
 * 共享进程的地址空间以及一些进程级别的其他资源，但是各个线程拥有
 * 自己的栈空间。
 * 面试题：
 * 1）进程和线程的区别和联系？
 * 2）进程和线程的使用场景下？
 * JVM内存结构
 *
 * 并发和并行
 * 并发指的是多个线程操作一个资源，不是同时执行，而是交替执行，单核CPU，
 * 只不过因为cpu的时间片很短，速度太快，看起来同时执行
 * 并行才是真正的同时执行，多核cpu，每一个线程都可以使用一个单独的cpu资源
 * 来运行
 * QPS：每秒能够响应的请求数
 * 平均响应时间：并发数/平均响应时间=QPS
 * 并发用户数：系统可以承载的最大用户量
 * 吞吐量：单位时间内处理的请求数
 * 面试题：互联网系统架构中，如何提高系统的并发能力？
 *
 * 创建线程
 * 一边看电视、一边吃饭、多线程情况下如何编写程序
 * 1）继承Thread类，重写run()（当前线程的执行逻辑）方法
 * 2) 实现Runnable接口，重写run()方法
 * 3) 匿名线程，匿名类
 * 4) 实现Callable接口，重写call()方法  自己去验证
 * 面试题：Callable和Runnable接口
 *
 * 课堂练习：模拟银行叫号系统
 * start方法剖析
 * start方法本身是用来启动一个线程，并将其添加到一个线程组里面，这时线程获取
 * cpu资源之后就会执行所定义的run方法的逻辑
 *
 * run方法本身只是一个普通的方法，并不会启动新的线程
 *
 */

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("hello world");
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("implemnts Runnable to get a thread");
    }
}

class WatchTv extends Thread{
    @Override
    public void run() {
        System.out.println("watch TV");
    }
}

public class TestDemo1 {
    public static void main(String[] args) {
        new Thread("watchTv"){
            @Override
            public void run() {
                System.out.println("watch TV");
            }
        }.start();

        new Thread("eating"){
            @Override
            public void run() {
                System.out.println("eating");
            }
        }.start();

//        Thread thread = new Thread(new MyRunnable());//线程对象
//        thread.start();//启动线程
//        thread.start();

//        new WatchTv().start();
//        System.out.println("eating");

//        Thread thread = new MyThread();//实例化线程对象
//        thread.start();//启动线程
    }
}
