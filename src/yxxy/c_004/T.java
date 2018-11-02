package yxxy.c_004;

public class T {
    private static int count = 10;

    public synchronized static void m() {
        //这里等同于 synchronized(yxxy.c_004.T.class)
        count--;
        System.out.println(Thread.currentThread().getName() + "count" + count);
    }

    public static void mm() {
        //这里写synchronized(this)是否可以？ 不可以，静态下不需要new对象，不存在this
        synchronized (T.class) {
            // 这里锁定的是 当前这个类的 Class 对象
            // Class clazz = T.class;
            count--;
        }
    }
}
