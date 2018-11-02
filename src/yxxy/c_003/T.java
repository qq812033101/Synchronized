package yxxy.c_003;

public class T {
    private int count = 10;

    public synchronized void m() {
        //等同于  synchronized (this)
        count--;
        System.out.println(Thread.currentThread().getName() + "count" + count);
    }
}
