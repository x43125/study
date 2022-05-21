package com.wx.algorithm.labuladong.rollback;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/21
 */
public class CanPartitionKSubSets {
    public static void main(String[] args) {
        CanPartitionKSubSets kSubSet = new CanPartitionKSubSets();

        int[] nums = {4,3,2,3,5,2,1};
        int k = 4;

        System.out.println(kSubSet.canPartitionKSubsets(nums, k));
    }

    static int target;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        target = sum / k;
        boolean[] used = new boolean[nums.length];
        return backTrack(nums, k, used, 0, 0);
    }

    private boolean backTrack(int[] nums, int k, boolean[] used, int bucket, int start) {
        if (k == 0) {
            return true;
        }

        if (bucket == target) {
            return backTrack(nums, k - 1, used, 0, 0);
        }

        for (int i = start; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            if (bucket + nums[i] > target) {
                continue;
            }

            used[i] = true;
            bucket += nums[i];
            if (backTrack(nums, k, used, bucket, i + 1)) {
                return true;
            }
//            backTrack(nums, k, used, bucket, i + 1);
            used[i] = false;
            bucket -= nums[i];
        }

        return false;
    }
}
