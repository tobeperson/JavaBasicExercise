package com.company.tobeace;
//创建线程方式一：继承Thread类，重写run 方法，调用start开启线程
public class TestCreatThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("多线程---"+i);
        }
    }

    public static void main(String[] args) {
        TestCreatThread testThread1=new TestCreatThread();
        testThread1.run();  //调用run方法
        testThread1.start(); //调用start方法
        for (int i = 0; i < 20; i++) {
            System.out.println("主线程---"+i);
        }
    }
}
