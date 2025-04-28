package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_287_DuplicateNo {
    public int findDuplicate(int[] nums) {
        // 1.要能领悟出这一题是做环状检测
        // 2.要能写出来，next 类比 i -> nums[i]
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        int pre1 = 0;
        while (pre1 != slow) {
            pre1 = nums[pre1];
            slow = nums[slow];
        }

        return pre1;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        T_287_DuplicateNo duplicateNo = new T_287_DuplicateNo();
        int duplicate = duplicateNo.findDuplicate(nums);
        System.out.println(duplicate);
    }
}
