package com.wx.algorithm.leetcode.hop2025.hot100;

// TODO:
public class T_31_NextPermutation {
    public void nextPermutation(int[] nums) {
        // 从右向左，找到第一个相邻的升序i,j
        // 此时j到end是降序
        // 从右向左在j到end中找到第一个大于nums[i]的数k，交换i,k
        // 然后将i到end升序排列
        int i = -1;
        for (int j = nums.length - 1; j > 0; j--) {
            if (nums[j - 1] < nums[j]) {
                i = j - 1;
                for (int k = nums.length - 1; k > i; k--) {
                    if (nums[k] > nums[i]) {
                        swap(nums, i, k);
                        break;
                    }
                }
                break;
            }
        }
        // 如果找到头都没有找到，说明已经全降序，则直接将整体倒置
        sort(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void sort(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 1 };
        T_31_NextPermutation nextPermutation = new T_31_NextPermutation();
        nextPermutation.nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
