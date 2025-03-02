package com.wx.algorithm.algorithm4.sort;

/**
 * 选择排序
 */
public class ChooseSort extends SortBase {

    @Override
    public void sort(Comparable[] a) {
        // 从头开始遍历，每次将剩余最小的值选出来，放到此次位置，每一轮固定一个值的最终位置
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }
}
