package yxxy.c_021;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {
    /**
     * 面试题：
     * 写一个固定容量同步容器，拥有 put get 方法，已经 getCount 方法
     * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
     * 使用 wait  notify/notifyAll 来实现
     */

    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;

    public synchronized void put(T t) {
        //如果使用if 那么当条件不成立，往下执行到 lists.add(t) 这个代码的时候
        //还没往里面插入数据，另一个线程往容器里插入数据 这个时候容器就满了
        //这个时候在执行到 lists.add(t) 就会因为容器满 而报错
        // 而 使用 while 当条件不成立，往下执行代码的时候，因为while会在一次判断的条件，就会避免这个错误
        // wait 方法 往往 和 while 一起使用
        while (lists.size() == MAX) { //这里为什么用while  不用 if
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        this.notifyAll();//通知消费者线程进行消费
        //这里如果使用 notify那么很可能会唤 生产者这个线程 而不是叫醒消费者线程
        // 假如这个时候刚好容器满了，那么线程就进入wait 状态，而这个时候只有一个线程在执行 就是这个生产者这个线程 而这个线程又进入了 等待状态，那么程序就运行不了了
        // wait
    }

    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        this.notifyAll();//通知生产者进行生成
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) System.out.println(c.get());
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
    }
}
