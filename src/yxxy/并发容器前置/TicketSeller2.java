package yxxy.并发容器前置;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {
    /**
     * Vector 同步容器 所有方法都自带锁
     */
    /**
     * 还是会有问题，判断跟操作分离
     */
    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
