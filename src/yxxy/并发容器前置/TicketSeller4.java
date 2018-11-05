package yxxy.并发容器前置;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TicketSeller4 {
    /**
     * 队列并发容器
     */
    static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();// poll() 拿出一个数据 如果拿不着，返回值则为空
                    if (s == null) break;
                    else System.out.println("销售了--" + s);
                }
            }).start();
        }
    }
}
