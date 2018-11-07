package yxxy.并发容器类;

import java.util.concurrent.LinkedTransferQueue;

public class TransferQueueTest {
    public static void main(String[] args) throws InterruptedException {
        //跟普通的queue是一样，但提供了一个特殊的方法 transfer
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa"); //自带阻塞

    }
}
