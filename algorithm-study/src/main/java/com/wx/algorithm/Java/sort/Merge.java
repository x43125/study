package com.wx.algorithm.Java.sort;

import com.wx.algorithm.Java.sort.util.SortUtil;

/**
 * 归并排序
 *
 * @author wangxiang
 */
public class Merge {
    public static void main(String[] args) {
        long num = 78651578943215497L;
        int[] arr = SortUtil.init(num);
        System.out.println("Selection Sort:");
        System.out.print("原: ");
        SortUtil.print(arr);
        // sortUp2Bottom(arr);
        sort2(arr);
        System.out.print("现: ");
        SortUtil.print(arr);
    }

    /**
     * 自顶向下
     *
     * @param arr
     */
    private static void sortUp2Bottom(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] aux = new int[arr.length];
        for (int i = left; i <= right; i++) {
            aux[i] = arr[i];
        }

        int i = left;
        int j = mid + 1;
        for (int k = i; k <= right; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > right) {
                arr[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
    }

    private static void sortBottom2Up(int[] arr) {
        int length = arr.length;
        for (int sz = 1; sz < length; sz = sz + sz) {
            for (int left = 0; left < length - sz; left += sz + sz) {
                merge(arr, left, left + sz - 1, Math.min(left + sz + sz - 1, length - 1));
            }
        }
    }


    /**
     * 自底向上
     *
     * @param nums
     */
    public static void sort1(int[] nums) {
        if (nums == null) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) >> 1;
        sort(nums, left, mid);
        sort(nums, mid + 1, right);
        merge2(nums, left, mid, right);
    }

    private static void merge2(int[] nums, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        if (k >= 0) {
            System.arraycopy(tmp, 0, nums, left, k);
        }
    }

    /**
     * 自下往上
     *
     * @param nums
     */
    public static void sort2(int[] nums) {
        if (nums == null) {
            return;
        }

        int n = nums.length;
        for (int i = 1; i < n; i <<= 1) {
            merge3(nums, n, i);
        }
    }

    private static void merge3(int[] nums, int n, int gap) {
        int i;
        // 两两合并
        for (i = 0; i + 2 * gap - 1 < n; i += (2 * gap)) {
            merge(nums, i, i + gap - 1, i + 2 * gap - 1);
        }

        // 如果最后单出来一个，则与前面的合并
        if (i + gap - 1 < n - 1) {
            merge(nums, i, i + gap - 1, n - 1);
        }

    }


}
