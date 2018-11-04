package yxxy.c_022;

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {
    /**
     * 线程局部变量
     */
    /**
     * ThreadLocal 是使用空间换时间， synchronized 是使用时间换空间
     * 比如在 hibernate 中 session就存在于  ThreadLocal 中 避免 synchronized 的使用
     * ThreadLocal：
     * 每个线程都存在自己的变量，跟别的线程不存在冲突。不管别的线程如何更改，自己线程内的变量都不会被改变。
     * 也就是说，每个线程里都一份变量的拷贝，各自线程修改各自的拷贝。
     *  缺点： 可能会导致内存泄漏
     */
    /*volatile static Person p = new Person();*/
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();

    }

    static class Person {
        String name = "zhangsan";
    }
}

