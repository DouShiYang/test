package jucTest;


import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 淘宝面试题：
 * - 2.面试题：写一个固定容量同步容器 拥有put和get方法  以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class Test2ConditionWithWCondition<T> {

    final private LinkedList<T> list = new LinkedList<>();

    final private int max = 10;

    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    /**
     * put方法
     *
     * @throws InterruptedException
     */
    public void put(T t) {
        //如果当前容器已经超过了最大数 则不可以再添加数据，需要通知消费者进行消费
        try {
            lock.lock();
            while (count == max) {
                producer.await();
            }
            //否则的话 可以继续加入到容器
            System.out.println(Thread.currentThread().getName() + "添加数据第" + count + "个数据：" + t);
            list.add(t);
            ++count;
            consumer.signalAll();
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }


    /**
     * get方法
     *
     * 算法
     */
    public void get() {
        try {
            lock.lock();
            while (count == 0) {
                //此线程进行等待
                consumer.await();
            }
            T t = list.removeFirst();
            System.out.println(Thread.currentThread().getName() + "取出元素:" + t);
            count--;

            //通知生产者进行生产
            producer.signalAll();
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        Test2ConditionWithWCondition testLockCondition = new Test2ConditionWithWCondition();

        //启动消费线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    testLockCondition.get();
                }
            }, "t" + i).start();
        }

        TimeUnit.SECONDS.sleep(1);

        //启动生产线程
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    testLockCondition.put(j);
                }
            }, "t" + i).start();
        }
    }
}