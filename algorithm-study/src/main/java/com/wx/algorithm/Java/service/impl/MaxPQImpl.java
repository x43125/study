package com.wx.algorithm.Java.service.impl;

import com.wx.algorithm.Java.service.MaxPQ;
import com.wx.algorithm.Java.sort.util.SortUtil;

/**
 * 优先队列
 * 使用大顶堆原理
 */
public class MaxPQImpl implements MaxPQ {
    private int n = 0;
    private int[] arr;

    MaxPQImpl(int n) {
        arr = new int[n+1];
    }

    MaxPQImpl(int[] arr) {
        this.arr = arr;
        this.n = arr.length;
    }

    @Override
    public void insert(int v) {
        arr[++n] = v;
        swim(n);        
    }

    private void swim(int n) {
        while (n>1 && arr[n/2]<arr[n]) {
            SortUtil.exch(arr, n, n/2);
            n /= 2;
        }
    }

    @Override
    public int max() {
        return arr[1];
    }

    @Override
    public int delMax() {
        int max = arr[1];
        SortUtil.exch(arr, 1, n--);
        arr[n+1] = -1;
        sink(arr, 1, n);
        return max;
    }

    private void sink(int[] arr, int i, int n) {
        while (i*2 <= n) {
            int j = i*2;
            if (j<n && arr[j]<arr[j+1]) {
                j++;
            }
            if (arr[i] >= arr[j]) {
                break;
            }
            SortUtil.exch(arr, i, j);
        }
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    public void sort(int[] nums) {
        int length = nums.length;
        for (int i=length/2; i>=1; i--) {
            sink(nums, i, length);
        }
        while (length > 1) {
            SortUtil.exch(arr, 1, length--);
            sink(arr, 1, length);
        }
    }
}
