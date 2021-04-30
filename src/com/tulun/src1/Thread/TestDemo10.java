package com.tulun.src1.Thread;

import org.omg.CORBA.TIMEOUT;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 回顾
 * 悲观锁
 * 乐观锁
 * 1）CAS 内存V 预期值A 将写入的值B
 * java.util.concurrent.atomic包下
 * CAS引发的问题：
 * a.ABA问题  A->B->A
 *   通过版本号解决
 * b.CAS只能保证一个共享变量的原子性
 *   多个共享变量使用CAS操作还能够去保证其原子性吗？  不能
 *   Java AtomicReference -》多个共享变量
 * c.自旋CAS如果长时间不成功，给cpu带来很大的执行开销
 *
 * 2）版本号
 * 共享数据中增加一个字段version，表示该数据的版本号，如果当前的数据发生
 * 改变，版本号加1
 * thread1 -> sharedData + version -> alter version进行判断，如果
 * 当前version与之前拿到的version一致才会进行操作
 *
 * 程序死锁
 * 1)造成死锁的必要条件：
 * a.互斥条件：某个资源在某一时间段内只能由一个线程占用
 * b.不可抢占条件：线程所得到的资源在未使用完毕前，资源申请者不能强行夺取
 * 资源占有者手中的资源
 * c.占有且申请条件：线程至少已经占有一个资源，此时又申请新的资源，由于当前
 * 新资源被其他线程所占用，该线程阻塞
 * d.循环且等待条件：一个线程等待其他线程释放资源，其他线程又在等待另外的线程释放资源，
 * 直到最后一个线程等待第一个线程释放资源，这使得所有的线程锁住
 * 2）常见的死锁问题：
 * a.交叉锁可能引起的的程序死锁问题
 * b.内存不足
 * 并发请求系统内存，如果当前系统内存不足，也有可能出现死锁的问题
 * thread1 10MB 执行都需要30MB的内存 系统可用内存20MB
 * thread2 20MB 执行都需要30MB的内存 系统可用内存20MB
 * c.一问一答式的数据交换
 * 服务器开启某个端口，等待客户端去访问，客户端发起访问请求等到接收服务器端返回的资源，
 * 可能由于网络问题服务器端错过了客户端的请求
 * d.死循环引起的死锁
 *
 * 哲学家就餐问题：
 * 死锁代码
 * 如何解决死锁问题的发生？
 *
 * 3）避免死锁的发生：
 * 银行家算法
 *
 */


class ChopSticks{
//    protected String name;
//    public ChopStick(String name){
//        this.name = name;
//    }
    //key表示筷子的编号，value表示筷子可用状态，false是可用，true是不可用
    protected static HashMap<Integer, Boolean> map = new HashMap<>();

    static{
        map.put(0, false);
        map.put(1, false);
        map.put(2, false);
        map.put(3, false);
        map.put(4, false);

    }

    public synchronized void getChopsticks(){
        String currentName = Thread.currentThread().getName();
        int leftChop = currentName.charAt(currentName.length()-1)-'0';
        int rightChop = (leftChop+1) % 5; //leftChop+1;
        while(map.get(leftChop) || map.get(rightChop)){
            //有一个为true表示当前这个筷子正在被其他哲学家所使用
            //当前线程需要阻塞等待
            try {
                this.wait(); //释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        map.put(leftChop, true);
        map.put(rightChop, true);
        System.out.println(Thread.currentThread().getName() + " got the chopsticks " + leftChop +
        " and "+rightChop);
    }

    public synchronized void freeChopsticks(){
        String currentName = Thread.currentThread().getName();
        int leftChop = currentName.charAt(currentName.length()-1)-'0';
        int rightChop = (leftChop+1) % 5; //leftChop+1;
        map.put(leftChop, false);
        map.put(rightChop, false);
        this.notifyAll();//唤醒等待当前这双筷子的哲学家
    }
}

//class PhilosopherThread extends Thread{
//    private ChopStick leftChop;
//    private ChopStick rightChop;
//    private String name;
//
//    public PhilosopherThread(ChopStick leftChop, ChopStick rightChop, String name) {
//        this.leftChop = leftChop;
//        this.rightChop = rightChop;
//        this.name = name;
//    }
//
//    @Override
//    public void run() {
//        synchronized (leftChop){
//            System.out.println(name + " got the chopstick "+leftChop.name);
//            synchronized (rightChop){
//                System.out.println(name + " got the chopstick "+rightChop.name);
//                System.out.println(name + "is eating ");
//                try {
//                    TimeUnit.MILLISECONDS.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(name + "release the chopsticks "+leftChop.name+" and "+rightChop.name);
//            }
//        }
//    }
//}

public class TestDemo10 {
    public static void main(String[] args) {
//        ChopStick chopStick0 = new ChopStick("0");
//        ChopStick chopStick1 = new ChopStick("1");
//        ChopStick chopStick2 = new ChopStick("2");
//        ChopStick chopStick3 = new ChopStick("3");
//        ChopStick chopStick4 = new ChopStick("4");
//
//        new PhilosopherThread(chopStick0, chopStick1, "thread0").start();
//        new PhilosopherThread(chopStick1, chopStick2, "thread1").start();
//        new PhilosopherThread(chopStick2, chopStick3, "thread2").start();
//        new PhilosopherThread(chopStick3, chopStick4, "thread4").start();
//        new PhilosopherThread(chopStick4, chopStick0, "thread4").start();
        ChopSticks chopSticks = new ChopSticks();
        for(int i=0; i<5; i++){
            new Thread("thread" + String.valueOf(i)){
                @Override
                public void run() {
                    while(true){
                        //获得左右两边筷子
                        chopSticks.getChopsticks();
                        System.out.println(Thread.currentThread().getName() + " is eating ");
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //释放手中的筷子
                        chopSticks.freeChopsticks();
                    }
                }
            }.start();
        }
    }
}

//    private final Object R1 = new Object();
//    private final Object R2 = new Object();
//    //thread1 获取R1之后 切换到thread2
//    //获取R2,此时R2被thread2使用，阻塞
//    public void fun1(){
//        synchronized (R1){
//            synchronized (R2){
//                //dosomething
//            }
//        }
//    }
//
//    //thread2 获取R2之后，期望获取R1，此时R1被thread1去使用，阻塞，切换到therad1
//    public void fun2(){
//        synchronized (R2){
//            synchronized (R1){
//                //dosomething
//            }
//        }
//    }