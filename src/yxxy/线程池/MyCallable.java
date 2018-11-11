package yxxy.线程池;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {

    /*
     *  Callable 跟Runnable 非常的相似
     *  他们被设计出来都是默认被线程调用，但是两者还是有区别的
     *  Runnable 的 run 方法没有返回值  Callable call方法则有。
     *  run方法重写不能抛异常，call方法可以
     *  两者使用场景：
     *          1 当线程调用完任务之后，需要一个返回值，则使用call
     *          2 需要抛异常的情况下 使用call
     * */

    @Override
    public Object call() throws Exception {
        return null;
    }
}
