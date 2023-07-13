package com.wx.algorithm.Java.sort;

import com.wx.algorithm.Java.sort.util.SortUtil;

/**
 * @author wangxiang
 * @date 2023/7/7 08:14
 * @description
 */
public class SortAllToolKit {
    public static void main(String[] args) {
//        long num = 270051398416L;
//        int[] nums = SortUtil.init(num);
        int[] nums = {2, 7, 2, 5, 1, 9, 4, 9, 5, 7, 3, 3};

        System.out.print("原: ");
        SortUtil.print(nums);
//        System.out.println("Quick Sort:");
//        quickSort(nums);
        System.out.println("Merge Sort:");
        mergeSort1(nums);
//        mergeSort2(nums);
        System.out.print("现: ");
        SortUtil.print(nums);
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left, j = right, x = nums[left];

        while (i < j) {
            while (i < j && nums[j] > x) {
                j--;
            }
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }
            while (i < j && nums[i] < x) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = x;
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    /**
     * 归并排序1:自底向上
     *
     * @param nums
     */
    public static void mergeSort1(int[] nums) {
        mergeSort1(nums, 0, nums.length - 1);
    }

    public static void mergeSort1(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        // 除以2
        int mid = (left + right) >> 1;
        mergeSort1(nums, left, mid);
        mergeSort1(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    /**
     * 合并两个有序数组
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] nums, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int k = 0, rightStart = mid + 1, leftStart = left;
        while (leftStart <= mid && rightStart <= right) {
            if (nums[leftStart] < nums[rightStart]) {
                tmp[k++] = nums[leftStart++];
            } else {
                tmp[k++] = nums[rightStart++];
            }
        }
        while (leftStart <= mid) {
            tmp[k++] = nums[leftStart++];
        }
        while (rightStart <= right) {
            tmp[k++] = nums[rightStart++];
        }
        for (int i = 0; i < tmp.length; i++) {
            nums[i + left] = tmp[i];
        }
    }

    /**
     * 自底向上
     *
     * @param nums
     */
    public static void mergeSort2(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i *= 2) {
            merge2(nums, n, i);
        }
    }

    private static void merge2(int[] nums, int n, int gap) {
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
