package yxxy.c_005;

public class T implements Runnable {
    private int count = 10;//该属性存在堆中，只有一份。


    @Override
    public /*synchronized*/ void run() {
        //不加锁的情况下会有线程重入问题
        //当第A线程访问到count--的时候，count值为9
        //当A准备执行打印的时候，B线程进入，打断了A线程，执行了 count-- count值为8
        //这时候当A执行打印语句的时候，count值就变成了8 而不是9
        //解决办法----加锁
        count--;
        System.out.println(Thread.currentThread().getName() + "count" + count);
    }

    public static void main(String[] args) {
        T t = new T();//只new了一个对象，好多线程共同访问这个对象
        for (int i = 0; i < 5; i++) {
            new Thread(t, "thread" + i).start();
        }

    }
}

