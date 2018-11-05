package yxxy.并发容器类;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class ConcurrentMap {
    public static void main(String[] args) {
        // Map<String,String> map = new ConcurrentHashMap<>();
        //  Map<String,String> map = new ConcurrentSkipListMap<>();
        /**
         * ConcurrentSkipListMap 高并发并且排序
         */
        /**
         *  往 Hashtable 添加数据的时候，是锁定整个容器
         *  而 ConcurrentHashMap 则是默认把容器分成16段，每次插入数据只锁定其一段
         */
        Map<String, String> map = new Hashtable<>();
        //Map<String, String> map = new HashMap<>();

        Random r = new Random();
        Thread[] ths = new Thread[1000];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
                    latch.countDown();
                }
            });
        }

        Arrays.asList(ths).forEach(t -> t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
