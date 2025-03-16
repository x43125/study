package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_189_Rotate {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        int[] newA = new int[n];

        for (int i = 0; i < n; i++) {
            int pre = i - k;
            if (pre < 0) {
                pre = n + pre;
            }
            newA[i] = nums[pre];
        }

        for (int i = 0; i < n; i++) {
            nums[i] = newA[i];
        }
    }

    public void rotate_2(int[] nums, int k) {
        int count = 0;
        int n = nums.length;
        k = k % n;
        if (k == 0) {
            return;
        }
        int start = k;
        int pre = nums[0];
        while (count < n) {
            int i = start;
            do {
                int temp = nums[i];
                nums[i] = pre;
                pre = temp;
                i = (i + k) % n;
                count++;
            } while (i != start);
            start++;
            pre = nums[start - k];
        }
    }

    public static void main(String[] args) {
        // int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        // int[] nums = { -1, -100, 3, 99 };
        int[] nums = { -1 };
        int k = 2;
        T_189_Rotate rotate = new T_189_Rotate();
        rotate.rotate_2(nums, k);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
