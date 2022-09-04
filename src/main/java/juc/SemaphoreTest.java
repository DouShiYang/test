package juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 计数信号量。 从概念上讲，信号量维护一组许可。 如有必要，每个acquire块直到许可可用，然后获取它。
 * 每个release增加一个许可，可能会释放一个阻塞的收单方。 但是，没有使用实际的许可对象；
 * Semaphore只是计算可用的数量并相应地采取行动。
 * 信号量通常用于限制可以访问某些（物理或逻辑）资源的线程数。 例如，这是一个使用信号量来控制对项目池的访问的类：
 */
public class SemaphoreTest {

    public static void main(String[] args) {


        Semaphore semaphore = new Semaphore(1);

        new Thread(()->{
            try {
                semaphore.acquire();

                System.out.println("t1 start...");

                TimeUnit.SECONDS.sleep(2);
                System.out.println("t1 end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        },"t1").start();

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("t2 start...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("t2 end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }

        },"t2").start();

    }


}
