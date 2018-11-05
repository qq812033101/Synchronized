package yxxy.并发容器类;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueTest {
    //有界队列 能装的元素个数是固定的
    static ArrayBlockingQueue<String> srs = new ArrayBlockingQueue<>(10);
    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            try {
                srs.put("a" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // srs.put("aaa"); //满了会阻塞
        // srs.add("aaa");
        //srs.offer("aaa");
        // srs.offer("aaa", 1, TimeUnit.SECONDS); //按时间段进行阻塞
        System.out.println(srs);
    }
}
