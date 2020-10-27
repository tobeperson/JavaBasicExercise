package com.company.tobeace;

import java.util.concurrent.atomic.AtomicInteger;
//全局threadLocal 变量。并发隔离操作，线程和threadlocal 绑定
public class ThreadLocalDemo {
    public static ThreadLocal<String> threadLocal=new ThreadLocal<String>();
    public static class MyThread extends Thread{
        private static AtomicInteger atomicInt=new AtomicInteger();
        @Override
        public void run(){
            for (int i = 0; i < 4; i++) {
                threadLocal.set(atomicInt.addAndGet(1)+"");
                System.out.println("thread name is "+this.getName()+" value is "+threadLocal.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        Thread th1=new MyThread();
        Thread th2=new MyThread();
        Thread th3=new MyThread();
        th1.setName("Thread-1");
        th2.setName("Thread-2");
        th3.setName("Thread-3");
        th1.start();
        th2.start();
        th3.start();
    }
}
