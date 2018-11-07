package yxxy.并发容器类;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();
        /**
         *  核心容器 用的最多的
         *  offer() 类似add 但是add的方法下有容量限制，超过容量会抛出异常
         *  offer()则不会 返回值是个 boolean 类型
         */

        for (int i = 0; i < 10; i++) {
            strs.offer("a" + i);
        }
        System.out.println(strs);
        System.out.println(strs.size());

        //poll 取第一个元素并删除
        System.out.println(strs.poll());
        System.out.println(strs.size());

        //peek 取出第一个元素但不删除
        System.out.println(strs.peek());
        System.out.println(strs.size());


    }
}
