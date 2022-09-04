package jucTest;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 淘宝面试题：
 * - 1.实现一个容器，提供两个方法 add size
 * 写两个线程 线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时 线程2给出提示并结束
 */
public class TestWithouCountDown {

    List list = new LinkedList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {

        TestWithouCountDown testWithoutSupport = new TestWithouCountDown();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch count1 = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("监控线程启动---");
//            if (testWithoutSupport.size() != 5) {
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("监控线程已捕捉");
            count1.countDown();

        }, "t2").start();

        new Thread(() -> {
            System.out.println("add方法启动");
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                testWithoutSupport.add(new Object());
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                if (i == 5) {
                    countDownLatch.countDown();
                    try {
                        count1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("add方法结束");

        }, "t1").start();
    }
}