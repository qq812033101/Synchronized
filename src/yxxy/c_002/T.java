package yxxy.c_002;

public class T {
    private int count = 10;

    public void m() {
        synchronized (this) {
            /*
             * synchronized 锁定的是一个对象
             *
             *  this 自锁自解 当前这个类 T 不是访问者。
             * */
            count--;
            System.out.println(Thread.currentThread().getName() + "count" + count);
        }
    }
}
