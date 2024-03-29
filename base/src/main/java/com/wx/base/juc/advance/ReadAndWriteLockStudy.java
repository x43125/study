package com.wx.base.juc.advance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/24
 */
public class ReadAndWriteLockStudy {

    private List<String> list = new ArrayList<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    private void add(String s) {
        writeLock.lock();
        try {
            list.add(s);
        } finally {
            writeLock.unlock();
        }
    }

    private void remove(String s) {
        writeLock.lock();
        try {
            list.remove(s);
        } finally {
            writeLock.unlock();
        }
    }

    private String get(int index) {
        readLock.lock();
        try {
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadAndWriteLockStudy study = new ReadAndWriteLockStudy();
        study.add("a");
        study.add("b");
        study.add("c");

        System.out.println("0 " + study.get(0));
        System.out.println("1 " + study.get(1));
        System.out.println("2 " + study.get(2));

        study.remove("b");
        System.out.println("1 " + study.get(1));
    }
}
