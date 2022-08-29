package com.wx.algorithm.leetcode.normal;

public class T_1470_ShuffleTheArray {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8};
        int n = 4;
        T_1470_ShuffleTheArray shuffleTheArray = new T_1470_ShuffleTheArray();
        int[] res = shuffleTheArray.shuffle(nums, n);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public int[] shuffle(int[] nums, int n) {
        int[] res = new int[n * 2];

        for (int i = 0; i < n; i++) {
            res[2*i] = nums[i];
            res[2*i+1] = nums[i+n]; 
        }

        return res;
    }
}