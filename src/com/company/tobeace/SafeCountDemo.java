package com.company.tobeace;
import java.util.concurrent.atomic.AtomicLong;

public class SafeCountDemo {
    public static AtomicLong inc=new AtomicLong();
    public static synchronized void  increace(){
        inc.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            inc.set(0);
            count();
        }
    }

    private static void count() throws InterruptedException {
        final SafeCountDemo safeCountDemo=new SafeCountDemo();
        int threadCount=1000;
        long t1=System.currentTimeMillis();
        Thread thread=null;
        for (int i = 0; i < threadCount; i++) {
            thread=new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    safeCountDemo.increace();
                }
            });
            thread.start();
        }
        //加在这里是为了让线程结束。
        while (thread.getState()!= Thread.State.TERMINATED){
                Thread.sleep(1);
        }
        long t2=System.currentTimeMillis();
        System.out.println("SafeCountDemo "+ String.format("结果%s,耗时%s",inc,(t2-t1)));
    }
}
