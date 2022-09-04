package juc;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * 门栓
 *
 *
 * 与join的比较：
 *     join必须等当前线程结束 才能往下走
 */
public class CountDownLunchTest {


    public static void main(String[] args) {

        long l = System.currentTimeMillis();
//        useCountDown();
        try {
            useJoin();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();


        System.out.println(end-l);




    }



    public static void useCountDown() {

        Thread[] threads = new Thread[100000];

        CountDownLatch countDownLunch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i]= new Thread(()->{
                int result = 0;
                for (int j = 0; j < 1000000; j++) {
                    result += j;
                }
                countDownLunch.countDown();
            });
        }

        Arrays.stream(threads).forEach(Thread::start);


        try {
            countDownLunch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch");


    }


    public static void useJoin() throws InterruptedException {

        Thread[] threads = new Thread[100000];

        for (int i = 0; i < threads.length; i++) {
            threads[i]= new Thread(()->{
                int result = 0;
                for (int j = 0; j < 1000000; j++) {
                    result += j;
                }

            });
        }

        Arrays.stream(threads).forEach(Thread::start);
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("end join");



    }




}
