package juc;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentranLockTest {

    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();

        reentrantLock.readLock();

        reentrantLock.writeLock();


    }
}
