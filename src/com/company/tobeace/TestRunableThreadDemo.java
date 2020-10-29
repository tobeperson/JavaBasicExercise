package com.company.tobeace;
/**
 *将Runable对象转变为工作任务的方式是提交给一个Thread 构造器
 * Thread 构造器只需要一个Runable对象，Thread的start方法为线程执行必须初始化操作
 * */
public class TestRunableThreadDemo{
    public static class LiftOff  implements Runnable{
        protected int countDown = 10;
        private static int taskCount = 0;
        private final int id = taskCount++;
        public LiftOff() {}
        public LiftOff(int countDown){
            this.countDown=countDown;
        }
        public String status(){
            return "#"+id+(countDown>0 ?countDown : "\nLiftOff\n")+" ";
        }
        @Override
        public void run() {
            while(countDown-->0){
                System.out.print(status());
                Thread.yield();  //让步建议，不一定会进行采纳
            }
        }
    }

    public static void main(String[] args) {
//        LiftOff launch= new LiftOff();
//        launch.run();
        Thread t=new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff! ");
    }
}
