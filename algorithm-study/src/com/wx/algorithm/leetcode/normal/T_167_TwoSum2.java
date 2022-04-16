package com.wx.algorithm.leetcode.normal;

import java.util.HashMap;
import java.util.Map;

public class T_167_TwoSum2 {
    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target-numbers[i])) {
                return new int[]{map.get(target-numbers[i])+1,i+1};
            } else {
                map.put(numbers[i], i);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] arr = {};
        int[] twoSum = twoSum(arr, 12);
        System.out.println(twoSum[0] + " : " + twoSum[1]);
    }
}
