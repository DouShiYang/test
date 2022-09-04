package jucTest;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 淘宝面试题：
 * - 1.实现一个容器，提供两个方法 add size
 * 写两个线程 线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时 线程2给出提示并结束
 */
public class TestLockSupport {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    System.out.println("到5了");
                    LockSupport.park();
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        TimeUnit.SECONDS.sleep(10);
        LockSupport.unpark(t1);
        System.out.println("等到10秒了---");

    }
}