package com.wx.algorithm.leetcode.codetop;

public class T_215_FindKThLargest {
    public int findKthLargest(int[] nums, int k) {
        // 构建大顶堆
        buildMaxHeap(nums);
        int length = nums.length;
        for (int i = 0; i < k - 1; i++) {
            int index = nums.length - i - 1;
            swap(nums, 0, index);
            length--;
            // 处理从0开始的向下的分支，直到length截止
            maxHeapify(nums, 0, length);
        }

        return nums[0];
    }

    private void buildMaxHeap(int[] nums) {
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, i, nums.length);
        }
    }

    private void maxHeapify(int[] nums, int i, int length) {
        int l = i * 2 + 1, r = (i + 1) * 2, largestIndex = i;
        if (l < length && nums[largestIndex] < nums[l]) {
            largestIndex = l;
        }
        if (r < length && nums[largestIndex] < nums[r]) {
            largestIndex = r;
        }
        if (largestIndex != i) {
            swap(nums, i, largestIndex);
            maxHeapify(nums, largestIndex, length);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        T_215_FindKThLargest findKThLargest = new T_215_FindKThLargest();
        int kthLargest = findKThLargest.findKthLargest(nums, 1);
        System.out.println(kthLargest);
    }
}
