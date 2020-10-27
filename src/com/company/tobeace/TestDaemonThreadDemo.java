package com.company.tobeace;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class TestDaemonThreadDemo {
    public static class SimpleDaemons implements Runnable{

        @Override
        public void run() {
            try{
                while(true){
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(Thread.currentThread()+" "+this);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
                System.out.println("Sleep Interupted");
            }
        }
    }
    public static void testDaemon(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);  //设置为后台守护线程
            daemon.start();
        }
        System.out.println("ALL daemon Started!");
        TimeUnit.MILLISECONDS.sleep(175);  //调节时间可以看到不同的输出情况
    }
    /**
     *
     * */
    public static class DaemonThreadFactort implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            Thread t=new Thread(r);
            t.setDaemon(true);
            return t;
        }
    }
    public static class DaemonFromFactory implements Runnable{

        @Override
        public void run() {
            try{
                while(true){
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(Thread.currentThread()+" "+this);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
                System.out.println("Sleep Interupted");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool(new DaemonThreadFactort());
        //重载为接受一个
        for (int i = 0; i < 10; i++) {
            executorService.submit(new DaemonFromFactory());
        }
        System.out.println("All daemon Started");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
