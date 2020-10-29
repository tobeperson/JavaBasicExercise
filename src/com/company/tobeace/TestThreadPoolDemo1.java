package com.company.tobeace;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/**
 * 执行器管理Thread 对象，简化并发编程，Executor 在客户端和任务执行之间提供了以及各间接层，中介对象将执行任务
 * ExecutorService 具有服务生命周期的Executor 知道如何构建恰当的上下文执行Runable对象
 * **/
public class TestThreadPoolDemo1 {
    public static void main(String[] args) {
      //  testnewCachedThreadPool();
        testnewSingleThreadExecutor();
      //  testnewFixedThreadPool();
    }
    public static void testnewCachedThreadPool(){
        ExecutorService exec= Executors.newCachedThreadPool();  //为每一个任务都创建一个线程
        for (int i = 0; i < 5; i++) {
            exec.execute(new TestRunableThreadDemo.LiftOff());
        }
        exec.shutdown();
    }
    public static void testnewSingleThreadExecutor(){
        ExecutorService exec= Executors.newSingleThreadExecutor();  //排队等候进行运行，
        for (int i = 0; i < 5; i++) {
            exec.execute(new TestRunableThreadDemo.LiftOff());
        }
        exec.shutdown();
    }
    public static void testnewFixedThreadPool(){
        //创建固定的三个线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3); //创建限制线程数量的线程
        for (int i = 0; i < 10; i++) {
            final int index=i;
            threadPool.submit(()->{
                System.out.println("正在执行线程任务"+index);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭线程池
//        List<Runnable> tasks = threadPool.shutdownNow();
//        System.out.println("剩余"+tasks.size()+"个任务未执行完");
        threadPool.shutdown();

    }
}
