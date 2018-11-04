package yxxy.c_018;

public class T {
    String s1 = "hello";
    String s2 = "hello";

    /**
     * 不要以字符串常量作为锁对象
     * 在下面的例子中，m1 m2其实锁定的都是同一个对象
     * 这种情况还会发生比较诡异的现象，比如当你用到了一个类库，在该类库中代码锁定了
     * 字符串 hello 但是你读不到该源码，所以你在自己的代码中也锁定了hello 这时候就很
     * 可能发生死锁阻塞，因为你的程序和你用到的类库不经意间用到了同一把锁
     */

    void m1() {
        synchronized (s1) {

        }
    }

    void m2() {
        synchronized (s2) {

        }
    }
}
