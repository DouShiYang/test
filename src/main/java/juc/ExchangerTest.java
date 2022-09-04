package juc;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

    static Exchanger<String> exchanger = new Exchanger<String>();

    public static void main(String[] args) {
        new Thread(()->{
            String s1 = "s1";
            try {
                String exchange = exchanger.exchange(s1);
                System.out.println("t1 输出的是："+ exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            String s1 = "s2";
            try {
                String exchange = exchanger.exchange(s1);
                System.out.println("t2 输出的是："+ exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
