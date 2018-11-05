package yxxy.c_023;

public class Singleton {
    /**
     * 线程安全的单例模式
     * z
     */
    private Singleton() {
        System.out.println("single");
    }

    private static class Inner {
        private static Singleton s = new Singleton();
    }

    public static Singleton getSingle() {
        return Inner.s;
    }

    public static void main(String[] args) {
        Thread[] ths = new Thread[200];
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                Singleton.getSingle();
            });
        }
    }
}
