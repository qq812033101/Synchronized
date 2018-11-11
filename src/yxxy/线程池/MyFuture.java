package yxxy.线程池;

import java.util.concurrent.*;

public class MyFuture {
    /*
     *  未来任务
     * */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 未来任务执行完之后的寄过是 Integer 类型
        // FutureTask 构造函数中要传入的是一个 Callable实现类。
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();
        System.out.println(task.get());//阻塞

        //**********************************//
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        System.out.println(f.get());
        System.out.println(f.isDone());
        service.shutdown();
    }
}
