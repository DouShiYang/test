package juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {


    static ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readWriteLock = reentrantLock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock  = reentrantLock.writeLock();

    public static void main(String[] args) {

    }
}
