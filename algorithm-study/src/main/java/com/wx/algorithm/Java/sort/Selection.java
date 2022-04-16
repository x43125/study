package com.wx.algorithm.Java.sort;

import com.wx.algorithm.Java.sort.util.SortUtil;

/**
 * 选择排序
 * O(n^2)
 */
public class Selection {
    public static void main(String[] args) {
        long num = 78651578943215497L;
        int[] arr = SortUtil.init(num);
        System.out.println("Selection Sort:");
        System.out.printf("原: ");
        SortUtil.print(arr);
        sort(arr);
        System.out.printf("现: ");
        SortUtil.print(arr);
    }

    public static void sort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; ++i) {
            int min = Integer.MAX_VALUE;
            int right = -1;
            for (int j = i + 1; j < length; ++j) {
                if (arr[j] < min) {
                    right = j;
                    min = arr[j];
                }
            }
            SortUtil.exch(arr, i, right);
        }
    }


}
