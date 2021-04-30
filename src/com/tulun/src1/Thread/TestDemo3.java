package com.tulun.src1.Thread;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程
 * The java virtual machine exits when the only threads running are
 * all daemon threads;
 *
 * JVM总没有一个非守护线程，JVM进程会退出
 * 守护线程能够自动结束生命周期，非守护则不具备这一特点
 * 课堂练习：
 * 大家用匿名线程的方式创建一个线程，当前线程持续睡眠1s，TimeUnit.SECONDS.sleep(1);
 * 主线程睡眠1000ms，输入main thread fininshed。
 */
public class TestDemo3 {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    while(true){
                        TimeUnit.MILLISECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //将子线程变为守护线程
        thread.setDaemon(true);//在线程启动之前去调用
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread fininshed");
    }
}
