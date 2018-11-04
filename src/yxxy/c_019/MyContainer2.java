package yxxy.c_019;

import java.util.ArrayList;
import java.util.List;

public class MyContainer2 {
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
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer2 c = new MyContainer2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add" + i);
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (c.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();

    }
}
