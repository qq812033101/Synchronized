package yxxy.c_020;

import java.util.concurrent.TimeUnit;

public class ReentrantLock1 {

    /**
     * ReentrantLock 替代 synchronized
     * 本例中 由于m1锁定的是this,只有m1执行完 m2才能执行
     * 这里是复习 synchronized 的原始语义
     *
     * ReentrantLock：重入锁
     */

    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2() {
        System.out.println("m2");
    }

    public static void main(String[] args) {
        ReentrantLock1 r1 = new ReentrantLock1();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }
}
