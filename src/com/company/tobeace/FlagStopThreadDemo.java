package com.company.tobeace;

public class FlagStopThreadDemo implements  Runnable{
    private int ticket = 20;
    private volatile boolean flag = true;
    @Override
    public void run() {
        synchronized (this){
            try {
            while(flag){
                ticket--;
                System.out.println(Thread.currentThread().getName()+" ,flag = "+ flag+"买了一张票，剩余的票还有 "+ ticket);
                    Thread.sleep(100);
                if(0 == ticket){
                    flag = false;
                    System.out.println(Thread.currentThread().getName()+" ,flag = "+ flag+"票卖完了 "+ ticket);
                }
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("finally");
            }
        }
    }
    public void setFlag (boolean flag){
        this.flag = flag;
    }

    public static void main(String[] args) throws InterruptedException {
        FlagStopThreadDemo flagStopThreadDemo = new FlagStopThreadDemo();
        Thread thread = new Thread(flagStopThreadDemo);
        thread.setName("Thread-flag");
        thread.start();
        Thread.sleep(300);
       // flagStopThreadDemo.setFlag(false);
        thread.interrupt();
    }
}
