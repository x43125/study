package com.wx.algorithm.Java.sort;

import com.wx.algorithm.Java.sort.util.SortUtil;

/**
 * 希尔排序
 */
public class Shell {
    public static void main(String[] args) {
        long num = 01L;
        int[] arr = SortUtil.init(num);
        System.out.println("Shell Sort:");
        System.out.printf("原: ");
        SortUtil.print(arr);
        sort(arr);
        System.out.printf("现: ");
        SortUtil.print(arr);
    }

    public static void sort(int[] arr) {
        int length = arr.length;
        // 重点 h 的选择
        int h = 4;
        for (int j = h; j > 0; --j) {
            for (int k = length - 1; k >= length - 1 - j; --k) {
                for (int i = k; i >= j; i -= j) {
                    if (arr[i] < arr[i - j]) { 
                        SortUtil.exch(arr, i, i - j);
                    }
                }
            }
        }
    }
}
