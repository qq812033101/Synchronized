package yxxy.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class MyContainer4 {
    /**
     * 实现一个容器，根据两个方法 add size
     * 写两个线程，线程1添加10个元素到容器，现车2实现监控元素的个数，当个数到5个时
     * 线程2给出提示 并结束
     *  问题：t2 不能结束 原因 list 不可见
     */

    /**
     *  添加了 volatile 之后 t2 能够接收到通知，但是t2线程的死循环很浪费cpu
     *  如果不用死循环应该怎么做呢？
     */

    /**
     * 这里使用wait 跟 notify做到  wait会释放锁，而notify不会
     * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
     */
    /**
     * 这里使用wait 跟 notify做到  wait会释放锁，而notify不会
     * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
     * <p>
     * 程序运行结果，并不是在size=5的时候t2退出，而是t1结束的时候t2退出
     * notify后，t1必须释放锁，t2退出后也必须notify通知t1继续执行
     */
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer4 c = new MyContainer4();
        Object lock = new Object();

        new Thread(() -> {
            System.out.println("t2启动");
            synchronized (lock) {
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                lock.notify();
            }

        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {

            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    if (c.size() == 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("add" + i);
                }
            }
        }, "t1").start();


    }
}
