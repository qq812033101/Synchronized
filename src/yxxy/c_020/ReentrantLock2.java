package yxxy.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2 {
    Lock lock = new ReentrantLock();

    /**
     * ReentrantLock 可以完成 synchronized 同样的功能
     * 需要注意的是，必须 必须 必须 手动释放锁
     * synchronized 锁定遇到异常会自己释放锁，但是lock必须手动释放
     * ReentrantLock 手工锁
     */

    void m1() {
        try {
            lock.lock();//synchronized（this）
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        lock.lock();
        System.out.println("m2");
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLock2 r1 = new ReentrantLock2();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }
}
