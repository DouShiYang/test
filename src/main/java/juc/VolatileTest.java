package juc;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

    volatile  boolean running = true;
    void m(){
        System.out.println("start...");
        while(running){


        }
        System.out.println("end...");
    }



    public static void main(String[] args) {
        VolatileTest valitileTest = new VolatileTest();
        Thread t1 = new Thread(valitileTest::m, "t1");
        t1.start();
//        t1.interrupt();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        valitileTest.running = false;
    }
}
