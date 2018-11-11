package yxxy.线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelComputing {
    //通过线程池，完成并行计算小案例  计算1-20W之间有多少个质数
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //常规 单线程下计算的时间
        long start = System.currentTimeMillis();
        List<Integer> results = getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println("单线程下计算需要时间");
        System.out.println(end - start);

        System.out.println();

        //多线程下并行计算需要的时间
        //模拟cuo核心数
        final int cpuCoreNum = 4;

        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);//根据cup核心数量 加载对应数量的线程池

        //分片计算  越大的数，素数的计算时间越长，小数多分，大数少分，提高计算效率
        MyTask t1 = new MyTask(1, 80000);
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);
        //调用线程
        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);
        //计算时间
        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println("多线程并行计算下需要时间");
        System.out.println(end - start);
        //关闭线程
        service.shutdown();
    }

    static class MyTask implements Callable<List<Integer>> {

        int startPos, endPos;

        public MyTask(int s, int e) {
            this.startPos = s;
            this.endPos = e;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }
    }

    //判断是否是质数
    static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    //获取质数
    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (isPrime(i)) results.add(i);
        }
        return results;
    }

}
