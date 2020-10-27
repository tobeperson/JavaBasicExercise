package com.company.tobeace;

import java.util.concurrent.CountDownLatch;

public class JoinCounterDemo {
    static int count = 0;
    public static void incc(){
        count++;
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            count=0;
            count();
        }
    }
    private static void count() throws InterruptedException {
        int threadCount=1000;
        long t1=System.currentTimeMillis();
        Thread thread=null;
        for (int i = 0; i < threadCount; i++) {
            thread=new Thread(()->{
                    for (int i1 = 0; i1 < 1000; i1++) {
                        incc();
                    }
            });
            thread.start();
            thread.join();  //线程等待
        }
        long t2=System.currentTimeMillis();
        System.out.println("JoinCounterDemo "+ String.format("结果%s,耗时%s",count,(t2-t1)));
    }
}
