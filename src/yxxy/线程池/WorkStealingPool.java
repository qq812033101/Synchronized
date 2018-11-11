package yxxy.线程池;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkStealingPool {
    public static void main(String[] args) throws IOException {
        //主动线程池  线程数根据cup的核心数来决定
        //池中的每一个线程都维护者各自的一个任务空间，当某一个线程任务空间里的任务都执行完了,
        //那么该线程会主动的去别的线程的任务空间中拿取任务执行。
        //主动找活干~
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(3000));
        service.execute(new R(3000));
        service.execute(new R(3000));

        //由于产生的是精灵线程（守护线程，后台线程），主线程不阻塞的话，看不到输出；
        System.in.read();
    }

    static class R implements Runnable {
        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("time"+" "+Thread.currentThread().getName());
        }
    }
}
