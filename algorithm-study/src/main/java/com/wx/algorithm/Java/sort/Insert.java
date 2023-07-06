package com.wx.algorithm.Java.sort;

import com.wx.algorithm.Java.sort.util.SortUtil;

/**
 * 插入排序
 *
 * @author wangxiang
 */
public class Insert {
    public static void main(String[] args) {
        long num = 78651578943215497L;
        int[] arr = SortUtil.init(num);
        System.out.println("Insert Sort:");
        System.out.print("原: ");
        SortUtil.print(arr);
        sort(arr);
        System.out.print("现: ");
        SortUtil.print(arr);
    }

    public static void sort(int[] arr) {
        int n = arr.length;
        int i, j;
        for (i = 1; i < n; i++) {
            // 找到目标
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    SortUtil.swap(arr, j, j + 1);
                } else {
                    break;
                }
            }
        }
    }
}
