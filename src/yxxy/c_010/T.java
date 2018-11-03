package yxxy.c_010;

import java.util.concurrent.TimeUnit;

public class T {
    /**
     * 继承下可能发生的情况   子类调用父类的同步方法
     */
    synchronized void m1() {
        System.out.println("m   start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m   end");
    }

    public static void main(String[] args) {
        new TT().m1();
    }

}

class TT extends T {
    @Override
    synchronized void m1() {
        System.out.println("child m start");
        super.m1();
        System.out.println("child m end");
    }
}
