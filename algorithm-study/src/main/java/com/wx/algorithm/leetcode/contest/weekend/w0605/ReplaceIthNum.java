package com.wx.algorithm.leetcode.contest.weekend.w0605;

import java.util.HashMap;
import java.util.Map;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/06/05
 */
public class ReplaceIthNum {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 4, 6};
//        int[][] operations = {{1, 3}, {4, 7}, {6, 1}};
        int[] nums = {1, 2};
        int[][] operations = {{1, 3}, {2, 1}, {3, 2}};
        ReplaceIthNum ithNum = new ReplaceIthNum();
        int[] ints = ithNum.arrayChange(nums, operations);
        for (int anInt : ints) {
            System.out.print(anInt+ " ");
        }
    }

    public int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            index.put(nums[i], i);
        }

        for (int[] operation : operations) {
            nums[index.get(operation[0])] = operation[1];
            index.put(operation[1], index.get(operation[0]));
        }

        return nums;
    }
}
