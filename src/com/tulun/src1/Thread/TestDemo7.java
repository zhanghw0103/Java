package com.tulun.src1.Thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程同步
 * 模拟银行叫号系统
 * 1）某个号码会有重复
 * 线程A:index=index+1 index=65 cpu将执行权利交给了线程B +1=66 sout 66
 * 线程B:index=index+1=65+1=66 sout 66 cpu将执行权利交给了线程A
 * 2）某个号码被略过
 * 线程A:index=index+1=65+1=66 cpu将执行权利交给了线程B
 * 线程B:index=index+1=66+1=67 sout(67) 线程A再也无法获得当前cpu的使用权
 * 3）号码有可能超过当前最大值
 * 线程A:max=500 index=499 cpu将执行权利交给了线程B
 * 线程B：index=index+1=499+1=500 sout 500 线程A再次去执行 index=500+1
 * 原因为多个线程同时操作共享资源，出现数据不一致的问题
 * <p>
 * 为什么需要线程同步？
 * 共享资源会有多个线程同步操作的时候，并且我们没有进行任何的同步操作，就会
 * 发生冲突，因为我们不清楚每一个线程什么时候开始执行什么时候执行结束，也就
 * 无法控制当前线程的最终结果
 * <p>
 * 并发编程的三大特性
 * 原子性
 * 原子操作是不可分割的操作，一个原子操作是不会被其他线程打断的，所以不需要同步
 * 一个原子操作
 * int i = 10;
 * i++; i,i+1,i=结果 读取-》修改-》写入
 * 多个原子操作合起来则不是一个原子操作，这时就需要进行同步
 * <p>
 * 可见性
 * 当一个线程对共享变量进行了修改，那么另外可以立即看到修改后的最新值
 * <p>
 * 有序性
 * 程序代码在执行过程中的先后顺序  Java编译器会在运行期优化代码的执行顺序，
 * 导致了代码的执行顺序未必就是开发者编写代码时的顺序
 * <p>
 * 临界资源：同一时刻只允许一个线程访问的资源
 * 临界区：访问临界资源的代码段
 * <p>
 * synchronized关键字 防止线程干扰和内存一致性错误
 * 1）同步方法
 * public synchornized void sync(){
 * //表示要访问这个成员方法必须获取当前方法所在类的this引用的锁
 * }
 * 2）同步代码块
 * public final Object obj = new Object();
 * public void sync(){
 * synchornized(obj){
 * //需要保证独占性的资源
 * }
 * }
 * 课堂练习：实现两个线程，线程A输出5，4，3，2，1之后线程B再次输出5，4，3，2，1
 * <p>
 * 底层原理
 * 1) Java中的对象头、monitor
 * 对象-》对象头、实例数据、对齐填充字节
 * 对象头-》mark word（记录对象和锁相关的信息）
 * 指向类的指针
 * 数据长度(数组对象才会有)
 * 2）同步代码块
 * monitorenter:
 * a.每一个对象都跟一个monitor相关联，一个monitor的lock在某一个时刻只能够被一个
 * 线程锁获得，在一个线程中想要获取monitor的所有权，接下里有如下几件事情发生：
 * b.如果montior的计数器为0，则意味者当前monitor的lock还没有被获得，某个线程
 * 获得之后对该计数器加一，该线程就是这个monitor的所有者了
 * c.如果当前这个线程已经获取这把monitor lock，再次获取导致monitor计数器再次+！
 * d.如果monitor已经被其他线程所拥有，则当前线程会被陷入阻塞状态直到monitor的计
 * 数器变为，再次去尝试获取对monitor的所有权
 * monitorexit
 * 释放对monitor所有权即对monitor的计数器-1，当计数器结果为0，那就意味着当前
 * 线程不再拥有对monitor的使用锁
 * 3）同步方法
 * class文件中静态常量池ACC_SYNCHRONIZED，表示当前的方法是同步方法
 * synchornized锁的升级 下节课讲
 * synchronized可重入锁
 * 可重入锁：同一个线程重复请求自己持有的锁对象，可以请求成功而不会发生死锁
 */
//class Mythread extends Thread{
//Mythread threadA = new Mythread();
//Mythread threadB = new Mythread();
//threadA.start();
//threadB.start();
class Mythread implements Runnable {
    public synchronized void test1() {
        //获取MyThread类当前this引用的对象锁
        int i = 5;
        while (i >= 1) {
            System.out.println(Thread.currentThread().getName() + "::" + i--);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test2() {
        synchronized (Mythread.class) {
            //获取当前Mythread类中class对象的对象锁
            int i = 5;
            while (i >= 1) {
                System.out.println(Thread.currentThread().getName() + "::" + i--);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        //test1();
        test2();
    }
}

class SuperTest {
    public synchronized void superTestFunc() {
        System.out.println("superTest doing something");
    }
}

public class TestDemo7 extends SuperTest {
//    private final static Object lock = new Object();
//    public synchronized static void func(){
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    //        synchronized (lock){
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public synchronized void testDemoFunc() {
        superTestFunc();
        System.out.println("testDemo do something");
    }

    public static void main(String[] args) {
        //可重入锁
        TestDemo7 testDemo7 = new TestDemo7();
        testDemo7.testDemoFunc();

//        Runnable runnable = new Mythread();
//        Thread threadA = new Thread(runnable, "threadA");
//        Thread threadB = new Thread(runnable, "threadB");
//        threadA.start();
//        threadB.start();

//        for(int i=0; i<5; i++){
//            new Thread(){
//                @Override
//                public void run() {
//                    func();
//                }
//            }.start();
//        }
    }
}

//    private volatile static int initValue = 0; //1
//    private final static int MAX = 5; //2
//    public static void main(String[] args) {
//        new Thread("reader"){
//            //initValue副本
//            @Override
//            public void run() {
//                int localValue = initValue;
//
//                while(localValue < MAX ){
//                    if(initValue != localValue){
//                        System.out.println("The initValue is updated to "+initValue);
//                        localValue = initValue;
//                    }
//                }
//            }
//        }.start();
//
//        new Thread("updater"){
//            @Override
//            public void run() {
//                //initValue副本
//                int localValue = initValue;
//
//                while(localValue < MAX ){
//                    System.out.println("The initValue is changed "+(++localValue));
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

//    private boolean flag = false;
//    public void load(){
//        if(!flag){
//            func(); //1
//            flag = true; //2
//        }
//    }
//
//    public void func(){
//        if(!flag){
//            //
//        }
//    }