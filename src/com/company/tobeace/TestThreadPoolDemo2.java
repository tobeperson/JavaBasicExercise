package com.company.tobeace;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestThreadPoolDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testThreadPoolExecutor();
    }

    private static void testThreadPoolExecutor() throws ExecutionException, InterruptedException {
        int corePoolSize =2;//最小活跃线程数
        int maximumPoolSize =5;//最大活跃线程数
        int keepAliveTime=5;//空闲超过5s将要回收
        TimeUnit unit = TimeUnit.SECONDS;
        //阻塞队列
        BlockingQueue<Runnable> workQueue =null;
        workQueue = new ArrayBlockingQueue<>(5);//基于数组的先进先出队列，有界
        workQueue = new LinkedBlockingQueue<>();//基于链表的先进先出队列，无界
        workQueue = new SynchronousQueue<>();//无缓冲的等待队列，无界
        //拒绝策略
        RejectedExecutionHandler rejected = null;
        rejected = new ThreadPoolExecutor.AbortPolicy();//默认，队列满了丢任务抛出异常
        rejected = new ThreadPoolExecutor.DiscardPolicy();//队列满了丢任务不异常
        rejected = new ThreadPoolExecutor.DiscardOldestPolicy();//将最早进入队列的任务删，之后再尝试加入队列
        rejected = new ThreadPoolExecutor.CallerRunsPolicy();//如果添加到线程池失败，那么主线程会自己去执行该任务
        //使用的线程池
        ExecutorService threadPool = null;
        threadPool = Executors.newCachedThreadPool();//有缓冲的线程池，线程数 JVM 控制
        threadPool = Executors.newFixedThreadPool(3);//固定大小的线程池
        threadPool = Executors.newScheduledThreadPool(2);
        threadPool = Executors.newSingleThreadExecutor();//单线程的线程池，只有一个线程在工作
        threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                rejected);//默认线程池，可控制参数比较多
        //执行无返回值线程
        TaskRunnable taskRunnable=new TaskRunnable();
        threadPool.execute(taskRunnable);

        List<Future<String>> futureList=new ArrayList<>();
        //执行有返回值线程
        for (int i = 0; i < 10; i++) {
            TaskCallable taskCallable= new TaskCallable();
            Future<String> future=threadPool.submit(taskCallable);
            futureList.add(future);
        }
        for (int i = 0; i < futureList.size(); i++) {
            String result=futureList.get(i).get();
            System.out.println(i+" result= "+result);
        }

    }

    private static class TaskRunnable implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("result name: "+ Thread.currentThread().getName());
        }
    }

    private static class TaskCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            return Thread.currentThread().getName();
        }
    }
}
