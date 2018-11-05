package yxxy.并发容器类;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {
    //无界队列，可以塞无限多的数据，同时自带阻塞
    //put() 如果满了 就会等待
    //take() 如果空了 就会等待
    static LinkedBlockingQueue<String> strs = new LinkedBlockingQueue<>();
    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "take - " + strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c").start();
        }

    }
}
