package yxxy.c_017;

import java.util.concurrent.TimeUnit;

public class T {
    private Object o = new Object();

    /*
        锁定对象o 如果o的属性发生改变，不影响锁
        但是如果o的指向了另一个对象，则锁定的对象发生改变
        应该避免将锁定的“引用变成另外的对象”
     */
    public void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        Thread t2 = new Thread(t::m, "t2");
        t.o = new Object();//锁对象发生改变。线程2得以执行
        t2.start();
    }
}
