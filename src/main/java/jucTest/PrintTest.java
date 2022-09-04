package jucTest;

import java.util.concurrent.locks.LockSupport;

/**
 * 按照顺序打印A1B2c3d4e5---Z26
 */
public class PrintTest {

    static int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};

    static String[] str = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};


    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (int i : a) {
                LockSupport.park();
                System.out.print(i);
                LockSupport.unpark(t2);

            }
        }, "t1");


        t2 = new Thread(() -> {
            for (String s : str) {
                System.out.print(s);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        }, "t2");

        t2.start();
        t1.start();


    }
}
