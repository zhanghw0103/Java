package com.tulun.src1.Thread;

/**
 * 线程的生命周期 线程之间的状态转换 线程中常用的方法
 *
 * 生命周期：
 * NEW(新建状态)：new Thread()创建线程对象
 * RUNNABLE(就绪状态)：线程对象此时调用start方法，此时在JVM进程中创建了一个线程，
 * 线程并不是一经创建就直接得到执行，需要等到操作系统的其他资源，比如：处理器
 * BLOCKED(阻塞状态)：等到一个监视器锁进入到同步代码块或者同步方法中，代码块/
 * 方法某一个时刻只允许一个线程去执行，其他线程只能等待，这种情况下等待的线程会从
 * RUNNABLE状态转换到BLOCKED状态 Objcet.wait()
 * WAITING(等待状态)：调用Object.wait()/join()/LockSupport.park()等方法，
 * 此时线程从RUNNABLE转换到WAITING状态
 * TIMED_WAITING(睡眠状态)：调用带超时参数的THread.sleep(long millis)/
 * Object.wait(long timeout)/join(long milles)/LockSupport.parkNanos()/
 * LockSupport.parkUntil等方法都会使得当前线程进入到TIMED_WAITING状态
 * TERMINATED（终止状态）: 是线程的最终状态
 * 1) 线程正常运行结束
 * 2）线程运行出错
 * 3）JVM crash
 *
 * 线程之间的状态转换
 */
public class TestDemo4 {
    public static void main(String[] args) {
    }
}
