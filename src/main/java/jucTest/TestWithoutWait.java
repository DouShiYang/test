package jucTest;


import java.util.LinkedList;
import java.util.List;

/**
 *
 * 淘宝面试题：
 * - 1.实现一个容器，提供两个方法 add size
 * 写两个线程 线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时 线程2给出提示并结束
 */
public class TestWithoutWait {

    List list = new LinkedList();

    public  void add(Object o){
        list.add(o);
    }

    public  int size(){
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {

        TestWithoutWait testWithoutSupport = new TestWithoutWait();

        final Object lock = new Object();

        new Thread(()->{
            synchronized (lock){
                System.out.println("监控线程启动---");
                if (testWithoutSupport.size()!=5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("监控线程已捕捉");
                lock.notify();
            }
        },"t2").start();

       new Thread(() -> {
           System.out.println("add方法启动");
           synchronized (lock) {
               for (int i = 0; i < 10; i++) {
                   System.out.println(i);
                   testWithoutSupport.add(new Object());
                   if (i == 5) {
                       lock.notify();
                       try {
                           lock.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               }
               System.out.println("add方法结束");
           }
        },"t1").start();
    }
}