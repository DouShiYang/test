package jucTest;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * zk分布式锁 测试方式
 */
public class TestDistributedLock {

    //定义共享资源
    private static int count = 10;

    private static void printCountNumber() {
        System.out.println("***********" + Thread.currentThread().getName() + "**********");
        System.out.println("当前值：" + count);
        count--;

        //睡2秒
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("***********" + Thread.currentThread().getName() + "**********");
    }


    public static void main(String[] args) {
        //定义客户端重试的策略
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);
        //定义ZK的一个客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("bigdata111:2181")
                .retryPolicy(policy)
                .build();

        //在ZK生成锁  ---> 就是ZK的目录
        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client, "/mylock");

        // 启动10个线程去访问共享资源
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {

                public void run() {
                    try {
                        //请求得到锁
                        lock.acquire();
                        //访问共享资源
                        printCountNumber();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        //释放锁
                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
