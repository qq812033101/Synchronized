package yxxy.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock3 {
    Lock lock = new ReentrantLock();

    /**
     * ReentrantLock 可以完成 synchronized 同样的功能
     * 需要注意的是，必须 必须 必须 手动释放锁
     * synchronized 锁定遇到异常会自己释放锁，但是lock必须手动释放
     * ReentrantLock 手工锁
     */
    /**
     * 使用 ReentrantLock 可以进行 尝试锁定 tryLock 这样无法锁定，或者在制定时间内无法锁定，线程可以决定是否等待
     */

    void m1() {
        lock.lock();
        try {
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

    /**
     * 使用tryLock 进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据 tryLock 的返回值来判断是否锁定
     * 也可以制定 tryLock 的时间 由于 tryLock(time)需要抛出异常 所以要注意 unlock的处理
     * 必须放在 finally中
     */
    void m2() {
        /*boolean locked = lock.tryLock();

        if(locked)lock.unlock();*/
        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2...." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock3 r3 = new ReentrantLock3();
        new Thread(r3::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r3::m2).start();
    }
}
