package jucTest;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 当前方法 有问题 无法实现面试1
 * 淘宝面试题：
 * - 1.实现一个容器，提供两个方法 add size
 * 写两个线程 线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时 线程2给出提示并结束
 */
public class TestWithoutVolitile {

//    List list = new LinkedList();

     volatile List list = Collections.synchronizedList(new LinkedList());


    public synchronized void add(Object o){
        list.add(o);
    }

    public synchronized int size(){
        return list.size();
    }


    public static void main(String[] args) throws InterruptedException {

        TestWithoutVolitile testWithoutSupport = new TestWithoutVolitile();


       new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                testWithoutSupport.add(new Object());
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        },"t1").start();



        new Thread(()->{
            while (true){
                if (testWithoutSupport.size()==5) {
                    break;
                }
            }
            System.out.println("有5了 跳出");
        },"t2").start();
    }
}