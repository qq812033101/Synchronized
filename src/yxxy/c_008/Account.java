package yxxy.c_008;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读问题
 */
public class Account {
    String name;
    double blance;

    public synchronized void set(String name, double blance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.blance = blance;
    }

    public /*synchronized*/ double getBlance(String name) {
        return this.blance;
    }

    /**
     * 存加锁，取没加锁。 存的时候同时又去取，就会导致 取钱比存钱先执行。
     * 线程的时间差问题
     * 参考007 同步和非同步方法是否可以同时调用
     */

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(() -> a.set("zhangSan", 100.0)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBlance("zhangSan"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBlance("zhangSan"));
    }

}
