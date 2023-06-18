package com.wx.base.juc.advance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/24
 */
public class ReentrantLockStudy {

    private List<String> list = new ArrayList<>();
    private volatile ReentrantLock lock = new ReentrantLock();

    private void add(String s) {
        lock.lock();
        try {
            list.add(s);
        } finally {
            lock.unlock();
        }
    }

    private String get(int index) {
        lock.lock();
        try {
            return list.get(index);
        } finally {
            lock.unlock();
        }
    }

    private void remove(String s) {
        lock.lock();
        try {
            list.remove(s);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockStudy study = new ReentrantLockStudy();
        study.add("aa");
        study.add("bb");
        study.add("cc");

        System.out.println("0 " + study.get(0));
        System.out.println("1 " + study.get(1));
        System.out.println("2 " + study.get(2));

        study.remove("bb");
        System.out.println("1 " + study.get(1));
    }
}
