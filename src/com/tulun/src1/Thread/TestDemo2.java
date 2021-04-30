package com.tulun.src1.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 1、进程和线程的区别和联系？
 * 1）进程是资源分配的最小单位，线程是程序执行(CPU调度)的最小单位。
 * 2）进程有自己的独立地址空间，每启动一个进程，系统就会为它分配地址空间，建立数据表来维护代码段、堆栈段和数据段，这种操作非常昂贵。 线程是共享进程中的数据的，使用相同的地址空间，因此CPU切换一个线程的花费远比进程要小很多，同时创建一个线程的开销也比进程要小很多。
 * 3）线程之间的通信更方便，同一进程下的线程共享全局变量、静态变量等数据，而进程之间的通信需要以通信的方式（IPC)进行。不过如何处理好同步与互斥是编写多线程程序的难点。
 * 4）但是多进程程序更健壮，多线程程序只要有一个线程死掉，那么对于其共享资源的其他线程也会产生影响，而一个进程死掉并不会对另外一个进程造成影响，因为进程有自己独立的地址空间。
 * 使用场景：
 * 因为进程是资源分配的基本单位，线程是程序执行的最小单元。以及进程与线程之间的健壮性来考虑。
 * 1）在程序中，如果需要频繁创建和销毁的使用线程。因为进程创建和销毁开销很大（需要不停的分配资源），但是线程频繁的调用只是改变CPU的执行，开销小。
 * 2）如果需要程序更加的稳定安全时，可以选择进程。如果追求速度，就选择线程。
 * 2、互联网中如何提高系统的并发能力？
 * · 垂直扩展
 * 提升单机的处理能力
 * 1)增强单机的硬件性能：增加CPU的核数、内存升级、磁盘扩容
 * 2)提升系统的架构能力：使用Cache来提高效率
 * · 水平扩展
 * 集群、分布式都是水平的扩展方案
 * 集群：多个人做同一事（同时多顾几个厨师同时炒菜）
 * 分布式：一个复杂的事情，拆分成几个简单的步骤，分别找不同的人去完成（1.洗菜 2.切菜 3.炒菜）
 * 1)站点层扩容：通过Nginx反向代理，实现高并发的系统，将服务部署在多个服务器上
 * 2)服务层扩容：通过RPC框架实现远程调用：Dubbo，Spring Clodud,将业务逻辑分拆成不同的RPC Client,
 * Clident完成各自的不同的业务，如果并发量比较大，新增加RPC Client
 * 3)数据层扩容：一台数据库拆分成多态，分库分表，主从复制，读写分离
 * 3、Callable接口和Runnable的区别
 * a.Callable接口实现的是call方法，Runnable接口实现的是run方法
 * b.Callable的任务执行后有返回值，Runnable没有返回值
 * c.call()方法会抛出异常，run方法不能抛出异常
 * d.
 *
 *
 */
//a
class CallableDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i=0; i<=10000;i++){
            sum += i;
        }
        return sum;
    }
}

public class TestDemo2 {
    public static void main(String[] args) {
        /**
         * 通过Callable和FutureTask创建线程
         * a.创建Callable接口的实现类，同时实现call方法
         * b.创建Callable实现类的对象，使用FutureTask包装当前Callable对象，
         * FutureTask对象封装Callable对象中call方法的返回
         * c.使用FutureTask对象作为Thread的参数创建并且启动线程
         * d.调用FutureTask对象的get()来获取子线程执行的结果
         */
        //b
        Callable<Integer> callableDemo = new CallableDemo();
        FutureTask<Integer> task = new FutureTask<>(callableDemo);
        //c
        Thread thread = new Thread(task);
        thread.start();

        try {
            //d
            Integer result = task.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
