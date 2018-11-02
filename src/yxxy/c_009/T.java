package yxxy.c_009;

import java.util.concurrent.TimeUnit;

public class T {
    //一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候任会得到该对象的锁
    //也就是说:synchronized获得的锁是可以重入的
    synchronized void m1() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }
}
