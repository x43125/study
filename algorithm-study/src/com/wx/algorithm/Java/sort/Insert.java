package com.wx.algorithm.Java.sort;

import com.wx.algorithm.Java.sort.util.SortUtil;

/**
 * 插入排序
 */
public class Insert {
    public static void main(String[] args) {
        long num = 78651578943215497L;
        int[] arr = SortUtil.init(num);
        System.out.println("Insert Sort:");
        System.out.printf("原: ");
        SortUtil.print(arr);
        sort(arr);
        System.out.printf("现: ");
        SortUtil.print(arr);
    }

    public static void sort(int[] arr) {
        int length = arr.length;
        for (int i=1; i<length; ++i) {
            for (int j=i; j>=1; --j) {
                if (arr[j] < arr[j-1]) {
                    SortUtil.exch(arr, j, j-1);
                }
            }
        }
    }
}
