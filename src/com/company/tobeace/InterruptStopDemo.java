package com.company.tobeace;

public class InterruptStopDemo extends Thread{
    @Override
    public void run() {
        try {
        for (int i = 0; i < 50000; i++) {
            if (this.isInterrupted()){
                System.out.println("i quit");
                throw new InterruptedException();
            }
            System.out.println(i);
        }
        } catch (Exception e) {
            System.out.println("something happen");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptStopDemo interruptStopDemo=new InterruptStopDemo();
        interruptStopDemo.start();
        Thread.sleep(100);
        interruptStopDemo.interrupt();
    }
}
