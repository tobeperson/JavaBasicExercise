package com.company.tobeace;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ReferenceDemo {
    public static class User {
        private String name;
        private int age;
        public User(String name,int age) {
            this.name=name;
            this.age=age;
        }
    }
    public static void main(String[] args) throws InterruptedException{
        //弱引用，gc回收对象
        WeakReference weakReference = new WeakReference( new User( "huanglaoxie",24));
        System.gc();
        System.out.println("弱引用，手动触发GC:" + weakReference. get());
        //虚引用，
        ReferenceQueue referenceQueue1 = new ReferenceQueue( );
        PhantomReference phantomReference = new PhantomReference(new User("huanglaoxie", 24),referenceQueue1);
        System.out.println("虛引用，什么也不做，获取:" + phantomReference.get());
        //软引用，内存不足回收
        ReferenceQueue<User> referenceQueue = new ReferenceQueue();
        SoftReference softReference = new SoftReference(new User("huanglaoxie",24),referenceQueue );
        System.gc();
        System.out.println("软引用，手动触发GC:" + softReference.get());
        WeakHashMap<User, String> weakHashMap = new WeakHashMap();
        //强引用
        User huanglaoxie = new User("huanglaoxie",24);
        weakHashMap.put(huanglaoxie, "huanglaoxie");
        System.out.println("有强引用的时候:map大小"+ weakHashMap. size());
    }
}