package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/6/17 17:00
 * @description
 */
public class T15ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {0, 0, 0};
        List<List<Integer>> threeSumList = threeSum(nums);
        for (List<Integer> list : threeSumList) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    /**
     * 先排序，然后固定一个a，最后b,c从两头向中间逼近
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        // 先排序数组
        Arrays.sort(nums);
        // 然后固定一个a
        for (int i = 0; i < nums.length - 2; i++) {
            // 如果最左侧已经大于0了，则无需再找
            if (nums[i] > 0) {
                break;
            }
            // 如果与前一个相同的话，则过滤掉否则可能重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            // 如果最右侧值已经小于0了，则无需再找
            if (nums[k] < 0) {
                break;
            }
            // 剩下两个数从两头向中间逼近来查找
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果相同则添加，并将j向右滑
                if (sum == 0) {
                    List<Integer> childList = new ArrayList<>();
                    childList.add(nums[i]);
                    childList.add(nums[j]);
                    childList.add(nums[k]);
                    result.add(childList);
                    j++;
                    // 直接滑倒没有重复的那个值
                    while (nums[j] == nums[j - 1] && j < k) {
                        j++;
                    }
                } else if (sum < 0) {
                    j++;
                    // 直接滑倒没有重复的那个值
                    while (nums[j] == nums[j - 1] && j < k) {
                        j++;
                    }
                } else {
                    k--;
                    // 直接滑倒没有重复的那个值
                    while (nums[k] == nums[k + 1] && j < k) {
                        k--;
                    }
                }
            }
        }

        return result;
    }
}
