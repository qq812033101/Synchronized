package yxxy.并发容器类;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    // 无界队列 默认排序
    static DelayQueue<MyTask> tasks = new DelayQueue();
    static Random r = new Random();

    static class MyTask implements Delayed {

        long runningTime;

        MyTask(long rt) {
            this.runningTime = rt;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "runningTime=" + runningTime +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask(now + 1000);
        MyTask t2 = new MyTask(now + 2000);
        MyTask t3 = new MyTask(now + 3000);
        MyTask t4 = new MyTask(now + 4000);
        MyTask t5 = new MyTask(now + 5000);
        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);
        System.out.println(tasks);
        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }

}
