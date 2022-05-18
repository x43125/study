package com.wx.algorithm.labuladong.sort;

/**
 * @Descrption: 归并排序
 * @Author: x43125
 * @Date: 22/05/18
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {3, 2, 65, 1, 4, 3, 2};
        sort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


    private static int[] temp;

    public static void sort(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    public static void sort(int[] nums, int left, int right) {
        // 之所以是 == 而不是 > 是因为这是排序，当只有一个值的时候就不需要在排序了
        if (left == right) {
            return;
        }

        int mid = ((right - left) >> 1) + left;
        sort(nums, left, mid);
        sort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private static void merge(int[] nums, int lo, int mid, int hi) {
        if (hi + 1 - lo >= 0) System.arraycopy(nums, lo, temp, lo, hi + 1 - lo);

        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                // 左半边数组已全部被合并
                nums[p] = temp[j++];
            } else if (j == hi + 1) {
                // 右半边数组已全部被合并
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}
