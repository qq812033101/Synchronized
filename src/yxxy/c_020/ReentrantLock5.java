package yxxy.c_020;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock5 extends Thread {
    /**
     * ReentrantLock 还可以指定为公平锁
     * 默认为不公平锁
     */
    private static ReentrantLock lock = new ReentrantLock(true);//参数为true则表示为公平锁，请对比输出结果

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 r5 = new ReentrantLock5();
        Thread th1 = new Thread(r5);
        Thread th2 = new Thread(r5);
        th1.start();
        th2.start();
    }
}
