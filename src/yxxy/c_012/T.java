package yxxy.c_012;

import java.util.concurrent.TimeUnit;

public class T {
    /**
     * volatile 关键字。 使一个变量在多个线程之间可见。
     * A B 线程都用到一个变量，java 默认是A线程中保留一份copy  如果B线程改了该变量，则A线程未必知道
     * 使用 volatile 会让所有线程都读到变量的修改值
     * <p>
     * 在下面的代码，running 是存在堆内存 T 对象中
     * 当线程 t1 开始运行的时候，会把runing值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy 并不会每次都
     * 内存中取读，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止
     * 使用 volatile 将会降至所有线程都去堆内存中读取 running的值
     * <p>
     * volatile 并不能保证多个线程共同修改running变量是所带的不一致问题，也就是说 volatile 不能替代 synchronized
     */


   /* volatile */ boolean running = true;//对比一下没有 volatile的情况下，整个程序运行结果的区别

    void m() {
        System.out.println("m start");
        while (running) {

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(() -> t.m(), "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
