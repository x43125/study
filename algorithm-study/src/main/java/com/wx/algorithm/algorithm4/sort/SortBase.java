package com.wx.algorithm.algorithm4.sort;

import java.util.Scanner;

/**
 * @author Shawn
 * @date 2025/2/28 09:04
 * @description
 */
public class SortBase {
    /**
     * 选择排序
     *
     * @param a
     */
    public static void chooseSort(Comparable[] a) {
        // TODO 各种排序算法
        // 1.选择排序
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                // 如果a[i]小于a[j]
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            // 最后交换i下标和最小值下标的两个值
            exch(a, i, min);
        }
    }

    /**
     * 插入排序
     * 
     * @param a
     */
    public static void insertSort(Comparable[] a) {
        int n = a.length;
        // 外层控制起点
        for (int i=1; i<n; i++) {
            // 内层控制比较
            for (int j=i; j>0; j--) {
                if (less(a[j], a[j-1])) {
                    exch(a, j, j-1);
                }
            }
        }
    }

    /**
     * 比较
     *
     * @param v
     * @param w
     * @return
     */
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换
     *
     * @param a
     * @param i
     * @param j
     */
    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] a = sc.nextLine().split(" ");
        Integer[] n = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            n[i] = Integer.parseInt(a[i]);
        }

        sc.close();
        // chooseSort(n);
        insertSort(n);
        assert isSorted(n);
        show(n);
    }
}
