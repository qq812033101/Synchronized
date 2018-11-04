package yxxy.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock4 {
    Lock lock = new ReentrantLock();

    /**
     * ReentrantLock 可以完成 synchronized 同样的功能
     * 需要注意的是，必须 必须 必须 手动释放锁
     * synchronized 锁定遇到异常会自己释放锁，但是lock必须手动释放
     * ReentrantLock 手工锁
     */
    /**
     * 使用 ReentrantLock 还可以调用 lockIncorruptibly方法，可以对线程 interrupt 方法做出和响应
     * 在一个线程等待锁的过程中，可以被打断
     */

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("t1.start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1--end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                /*lock.lock();*/ //这个方法不会被打断
                lock.lockInterruptibly();//可以对 interrupt 方法做出响应
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();//打断线程2的等待
    }
}
