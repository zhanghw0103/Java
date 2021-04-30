package com.tulun.src1;

import com.tulun.src1.List.ArrayListTest;
import list.List;

import java.util.ArrayList;

public class ProduceCustomer {
    public volatile static int num = 0;
    public volatile boolean flag = true;
    public static final int max = 2;
    public static final ArrayList<Integer> pool = new ArrayList();

    public synchronized void produce() throws InterruptedException {
        while (flag) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//            }
            synchronized (pool) {
                while (pool.size() == max) {
                    System.out.println("停止生产");
                    pool.wait();
                }
                pool.add(num);
                System.out.println("生产数 " + num + "当前容量 " + pool.size());
                num++;
                pool.notifyAll();
            }
        }
    }

    public void customer() throws InterruptedException {
        while (flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            synchronized (pool) {
                while (pool.size() == 0) {
                    System.out.println("容量为0 等待");
                    pool.wait();
                }
                int temp = pool.get(0);
                pool.remove(0);
                System.out.println("消费数 " + temp + "当前容量" + pool.size());
                pool.notifyAll();
            }
        }
    }
}
