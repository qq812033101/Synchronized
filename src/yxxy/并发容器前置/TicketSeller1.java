package yxxy.并发容器前置;

import java.util.ArrayList;
import java.util.List;

public class TicketSeller1 {
    /**
     * 有n张火车票
     *  同时有10个窗口对外销售
     *  请写一个程序模拟
     *  分析下面的程序可能会产生哪些问题？
     *  重复销售？超量销售？
     */

    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
