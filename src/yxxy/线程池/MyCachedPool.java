package yxxy.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyCachedPool {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws InterruptedException {
        //缓存线程池 弹性线程.
        //初始时，一个线程都没有。来一个任务起一个线程，最大现场数取决于缓存的大小。
        //当一个线程执行结束后进入空闲状态后，这时候刚好一个任务进来，需要调用线程，那么这个空闲的线程就会去执行这个新任务。
        //线程的默认空闲时间只有60秒，过了便会销毁。 可以自定义空闲时间
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);

        TimeUnit.SECONDS.sleep(80);

        System.out.println(service);
    }

}
