package com.wx.algorithm.Java.sort;

import com.wx.algorithm.Java.sort.util.SortUtil;

/**
 * 快速排序
 * 为保证不被有序性影响，最好先对数组做一次随机打乱
 * 注意边界
 */
public class Quick {
    public static void main(String[] args) {
        long num = 2705398416L;
        int[] arr = SortUtil.init(num);
        System.out.println("Insert Sort:");
        System.out.printf("原: ");
        SortUtil.print(arr);
        sort(arr, 0, arr.length - 1);
        System.out.printf("现: ");
        SortUtil.print(arr);
    }

    public static void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = quickSort(arr, left, right);
        sort(arr, left, index - 1);
        sort(arr, index + 1, right);
    }

    private static int quickSort(int[] arr, int left, int right) {
        int i = left;
        int j = right + 1;
        int val = arr[left];
        while (true) {
            while (arr[++i] < val && i < right) {
            }
            while (arr[--j] > val) {
            }
            if (i >= j) {
                break;
            }
            SortUtil.swap(arr, i, j);
        }
        SortUtil.swap(arr, left, j);
        return j;
    }
}
