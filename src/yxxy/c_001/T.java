package yxxy.c_001;

public class T {
    private int count = 10;
    private Object o = new Object();

    /*
        o 握着钥匙的监视对象且钥匙只有一把，其余线程要执行带锁的代码块，就必须去找o获取钥匙.

        当钥匙给了A线程之后，B线程想执行就必须要等A线程执行完加了锁的代码块，把钥匙还给o B在从o那里获取到钥匙后才能执行。
        互斥锁: 在加锁代码块运行完之前，只允许获得钥匙的线程执行，其余线程只能等待到获取钥匙为止。
     */
    public void m() {
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + "count" + count);
        }
    }
}
