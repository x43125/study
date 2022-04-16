package com.wx.juc.study.advance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/24
 */
public class ReentrantLockTest {

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

    private void remove(String s) {
        lock.unlock();
        try {
            list.remove(s);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {


    }
}
