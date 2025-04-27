package com.wx.algorithm.algorithm4.sort;

/**
 * 快排
 */
public class QuickSort extends SortBase {
    @Override
    public void sort(Comparable[] a) {
        // 递归定位每个数
        sort(a, 0, a.length - 1);
    }

    /**
     * 递归函数
     * @param a
     * @param low
     * @param high
     */
    private void sort(Comparable[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        // 先找到本次递归里，的待排序的数的位置
        int j = partition(a, low, high);
        // 递归其左侧数组该排序的数
        sort(a, low, j-1);
        // 递归其右侧数组该排序的数
        sort(a, j+1, high);
    }

    /**
     * 既分组又排序
     * @param a
     * @param low
     * @param high
     * @return
     */
    private int partition(Comparable[] a, int low, int high) {
        // 双指针找数
        // 因为下面统一用的是++i, --j
        // 又因为i的第一个是默认作为待排序的数的所以不需要 i = low+1
        // 但是因为--j，所以就需要 j=high+1了
        int i = low, j = high + 1;
        Comparable v = a[low];
        // 循环找本次数组
        while (true) {
            // 从左侧循环直到找到比待排序数大的数，直接走到底的话，说明都比v小
            while (less(a[++i], v) && i < high);    
            // 从右侧循环直到找到比待排序数小或相等的数，如果直接走到底的话，说明都比v大
            while (less(v, a[--j]) && j > low);
            if (i >= j) break;
            // 如果还是在本次扫描的过程里，则交换i，j的数，因为a[i]应该在v右侧，a[j]应该在v左侧，刚好交换就可以了
            exch(a, i, j);
        }
        // 最后将v替换到指定位置即可
        exch(a, low, j);
        // 返回最终位置
        return j;
    }
}
