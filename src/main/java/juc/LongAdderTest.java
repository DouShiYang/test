package juc;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {


    static long count2 = 0l;

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    //longadder的原理是 采用分段 计算
    static LongAdder longAdder = new LongAdder();


    public static void main(String[] args) {
      Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    atomicInteger.incrementAndGet();
                }
            });
        }

        long start = System.currentTimeMillis();
        Arrays.stream(threads).forEach(Thread::start);
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("atomic:"+ atomicInteger.get() +"  time:"+ (end-start));


        Object o = new Object();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100000; j++) {
                        synchronized (o) {
                            count2++;
                        }
                    }
                }
            });
        }
        start= System.currentTimeMillis();
        Arrays.stream(threads).forEach(Thread::start);
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        System.out.println("synchronized :"+ count2 +"  time:"+ (end-start));

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    longAdder.increment();
                }
            });
        }
        Arrays.stream(threads).forEach(Thread::start);
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        System.out.println("longadder :"+ longAdder.longValue() +"  time:"+ (end-start));
    }

}
