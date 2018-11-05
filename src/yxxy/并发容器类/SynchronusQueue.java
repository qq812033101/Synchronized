package yxxy.并发容器类;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronusQueue {

    public static void main(String[] args) throws InterruptedException {
        //同步队列 是特殊的 TransferQueue
        //没有容量的队列，容量为0，生产者一生产完，消费者就必须要立马消费
        BlockingQueue<String> strs = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        strs.put("aaa"); //阻塞 等待消费者消费
        //strs.add("aaa");
        System.out.println(strs.size());
    }
}
