package com.company.tobeace;

import java.util.concurrent.CountDownLatch;

public class UnsafeCountDemo {
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
        CountDownLatch countDownLatch=new CountDownLatch(threadCount);
        long t1=System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
          new Thread(()->{
                try {
                    for (int i1 = 0; i1 < 1000; i1++) {
                        incc();
                    }
                }finally {
                    countDownLatch.countDown();
                }
            }).start();
            }
        long t2=System.currentTimeMillis();
        System.out.println("UnSafeCountDemo "+ String.format("结果%s,耗时%s",count,(t2-t1)));
    }


}
