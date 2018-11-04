package yxxy.c_016;

import java.util.concurrent.TimeUnit;

public class T {
    int count = 0;

    /**
     * synchronized 优化
     * 需要同步的代码块越少好。
     */
    synchronized void m1() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //当前逻辑只需要对这行代码加锁，那么就不应该在方法上加锁 而应该使用 代码块
        count++;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //当前逻辑只需要对这行代码加锁，那么就不应该在方法上加锁 而应该使用 代码块
        //锁小加锁范围，提高执行效率。
        synchronized (this) {
            count++;
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
