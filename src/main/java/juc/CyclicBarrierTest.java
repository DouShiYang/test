package juc;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 龙珠凑齐  干炮
 */
public class CyclicBarrierTest {



    static CyclicBarrier cyclicBarrier =  new CyclicBarrier(20, new Runnable() {
        @Override
        public void run() {
            System.out.println("人够了，发车");
        }
    });


    public static void main(String[] args) {


        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
