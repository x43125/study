package com.wx.algorithm.leetcode.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Descrption: Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * @Author: x43125
 * @Date: 22/06/04
 */
public class T_15_ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        T_15_ThreeSum threeSum = new T_15_ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(nums);
        for (List<Integer> list : lists) {
            for (Integer t : list) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int m = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        // 排序
        Arrays.sort(nums);

        for (int i = 0; i < m - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = -nums[i];
            int k = m - 1;
            for (int j = i + 1; j < m - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (j < k && nums[j] + nums[k] > target) {
                    --k;
                }
                if (j == k) {
                    break;
                }
                if (nums[j] + nums[k] == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    res.add(temp);
                }
            }
        }

        return res;
    }
}
