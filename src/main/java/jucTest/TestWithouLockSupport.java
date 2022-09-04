package jucTest;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * 淘宝面试题：
 * - 1.实现一个容器，提供两个方法 add size
 * 写两个线程 线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时 线程2给出提示并结束
 */
public class TestWithouLockSupport {

    List list = new LinkedList();

    public void add(Object o) {
        list.add(o);
    }

    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) throws InterruptedException {

        TestWithouLockSupport testWithoutSupport = new TestWithouLockSupport();

        t2 = new Thread(() -> {
            System.out.println("监控线程启动---");
            LockSupport.park();
            System.out.println("监控线程已捕捉");
            LockSupport.unpark(t1);

        }, "t2");
        t2.start();

        t1 = new Thread(() -> {
            System.out.println("add方法启动---");
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                testWithoutSupport.add(new Object());
                if (i == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
            System.out.println("add方法结束");
        }, "t1");
        t1.start();
    }
}