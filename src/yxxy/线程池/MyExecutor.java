package yxxy.线程池;

import java.util.concurrent.Executor;


public class MyExecutor implements Executor {
    /*
        Executor 接口 最顶层接口
        执行器  只有一个 execute 方法。就是用来执行之自定义任务。

     */
    public static void main(String[] args) {
        new MyExecutor().execute(() -> System.out.println("hello "));
    }


    @Override
    public void execute(Runnable command) {
        //new Thread(command).start();
        command.run();
    }
}
