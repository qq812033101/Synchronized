package yxxy.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
    /*
    *  线程池就是一堆线程，然后装在某个容器中等待运行。
    *  好处：线程执行完之后不会销毁，而是回到池中，当有任务需要线程的时候可以去池中拿就可以,节省系统资源
    *
    *  池中维护着2个队列，一个执行队列，一个结束队列。
    *  任务执行完后，线程会进入等待状态，当一定时间没被调用，则会被销毁
    * */

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);//起了5个线程放入容器中 底层容器采用的是一个 链表阻塞容器
        for (int i = 0; i < 6; i++) {
            //还可以使用submit方法
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
        service.shutdown(); //关闭线程，正常的关闭，等任务执行完之后在关

        System.out.println(service.isTerminated()); //判断线程池中的任务是否都执行完
        System.out.println(service.isShutdown());//判断线程是否关闭。执行到这里的时候，线程是进入关闭状态中，因为任务还没执行完
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
        System.out.println(service);
    }
}
