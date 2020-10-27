package com.company.tobeace;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private int value = 200;
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ReadWriteLockDemo lock=new ReadWriteLockDemo();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.read();
                }
            },"read thread"+i).start();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.write((int)(Math.random()*100));
            }
        },"write thread").start();
    }
    public void read(){
        readWriteLock.readLock().lock();
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName()+"读取 "+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
    public void write(int value){
        readWriteLock.writeLock().lock();
        this.value=value;
        System.out.println(Thread.currentThread().getName()+"写入"+this.value);
        readWriteLock.writeLock().unlock();
    }
}
