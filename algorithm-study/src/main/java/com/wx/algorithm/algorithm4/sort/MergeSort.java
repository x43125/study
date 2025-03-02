package com.wx.algorithm.algorithm4.sort;

/**
 * 归并排序
 */
public class MergeSort extends SortBase {

    // 辅助数组
    private Comparable[] aux;

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    /**
     * 递归体
     * 
     * @param a
     * @param low
     * @param high
     */
    private void sort(Comparable[] a, int low, int high) {
        // 递归退出条件
        if (high <= low) {
            return;
        }

        int mid = low + (high - low) / 2;
        // 递归处理左侧
        sort(a, low, mid);
        // 递归处理右侧
        sort(a, mid + 1, high);
        // 合并左右已合并好的数据
        merge(a, low, mid, high);
    }

    /**
     * 将左右已排序好的数组，合并成一个排序数组
     * 
     * @param a
     * @param low
     * @param mid
     * @param high
     */
    private void merge(Comparable[] a, int low, int mid, int high) {
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        for (int k = low; k <= high; k++) {
            // 如果左侧已经走完，则剩余的全是右侧
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > high) {
                // 如果右侧已经走完，则剩余的全是左侧
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                // 如果都还有剩余，则比较，谁小，走谁
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }
}