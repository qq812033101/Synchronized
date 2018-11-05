package yxxy.并发容器类;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteList {
    public static void main(String[] args) {

        /**
         * 写实复制 CopyOnWriteArrayList<>();
         *  写的效率非常低，读的效率非常高
         *  在往容器添加数据的时候，它会把自己复制一份并把把数据添加进去
         *  然后引用指向复制的这个容器
         *  优点：从容器往外读数据的时候 可以不用加锁
         *  适合 少写 多读 的场景
         */
        //List<String> list = new ArrayList<>(); //整个会出现并发问题
        //List<String> list = new Vector<>();
        List<String> list = new CopyOnWriteArrayList<>();

        Random r = new Random();
        Thread[] ths = new Thread[100];
        for (int i = 0; i < ths.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        list.add("a" + r.nextInt(10000));
                    }
                }
            };
            ths[i] = new Thread(task);
        }
        runAndComputeTime(ths);
        System.out.println(list.size());
    }

    static void runAndComputeTime(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
