package com.company.tobeace;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import static java.util.concurrent.TimeUnit.SECONDS;
/**
 * 使用volatile域保存取消状态
 * 生成素数的程序运行1s钟
 * */
public class UseVolatileSaveStateDemo {
    public static class PrimeGenerator implements Runnable {
        private final List<BigInteger> primes = new ArrayList<BigInteger>();
        private volatile boolean cancelled;
        @Override
        public void run() {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                p = p.nextProbablePrime();
                synchronized (this){
                    primes.add(p);
                }
            }
        }
        public void cancel(){
            cancelled=true;
        }
        public synchronized List<BigInteger> get(){
            return new ArrayList<BigInteger>(primes);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrimeGenerator generator=new PrimeGenerator();
        new Thread(generator).start();
        try {
            SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        List<BigInteger> primes= generator.get();
        for (BigInteger prime : primes) {
            System.out.print(prime+" ");
        }
    }
}
