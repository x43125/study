package com.wx.juc.study.advance.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/04
 */
public class AtomicCollectionsStudy {
    static AtomicIntegerArray atomicArray = new AtomicIntegerArray(5);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < atomicArray.length(); i++) {
                atomicArray.set(i, i + 10);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < atomicArray.length(); i++) {
                atomicArray.set(i, i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < atomicArray.length(); i++) {
                System.out.print(atomicArray.get(i) + " ");
            }
            System.out.println();
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(atomicArray.get(3));
        System.out.println(atomicArray.getAndIncrement(3));
        System.out.println(atomicArray.get(3));
    }
}
