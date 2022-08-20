package com.wx.algorithm.leetcode.normal;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author wangxiang
 * @date 2022/8/18 10:35
 * @description
 */
public class T_1344_LongestPrefix {
    public static void main(String[] args) {
//        int[] nums = {1};
//        int[] nums = {1, 1};
//        int[] nums = {1, 2, 3};
//        int[] nums = {1, 2, 3, 4, 5, 5, 5};
//        int[] nums = {2, 2, 1, 1, 5, 3, 3, 5};
        int[] nums = {10, 2, 8, 9, 3, 8, 1, 5, 2, 3, 7, 6};
//        int[] nums = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5};
        T_1344_LongestPrefix longestPrefix = new T_1344_LongestPrefix();
        int i = longestPrefix.maxEqualFreq(nums);
        System.out.println("i = " + i);
    }

    public int maxEqualFreq(int[] nums) {
        Map<Integer, Integer> map = new TreeMap<>();
        Map<Integer, Integer> numMap = new TreeMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            numMap.put(entry.getValue(), numMap.getOrDefault(entry.getValue(), 0) + 1);
        }

        for (int i = nums.length; i > 0; i--) {
            if (isPrefix(numMap)) {
                return i;
            } else {
                int j = i - 1;
                int num = map.get(nums[j]);
                if (map.get(nums[j]) == 1) {
                    map.remove(nums[j]);
                } else {
                    map.put(nums[j], map.get(nums[j]) - 1);
                }

                if (numMap.get(num) == 1) {
                    numMap.remove(num);
                } else {
                    numMap.put(num, numMap.get(num) - 1);
                }
                if (num != 1) {
                    numMap.put(num - 1, numMap.getOrDefault(num - 1, 0) + 1);
                }
            }
        }

        return -1;
    }


    private boolean isPrefix(Map<Integer, Integer> numMap) {
        if (numMap.size() == 1) {
            int key = numMap.keySet().stream().findFirst().get();
            int value = numMap.values().stream().findFirst().get();
            return key == 1 || value == 1;
        } else if (numMap.size() == 2) {
            int firstK = 0;
            int firstV = 0;
            int secondK = 0;
            int secondV = 0;
            int i = 0;
            for (Map.Entry<Integer, Integer> entry : numMap.entrySet()) {
                i++;
                if (i == 1) {
                    firstK = entry.getKey();
                    firstV = entry.getValue();
                }
                if (i == 2) {
                    secondK = entry.getKey();
                    secondV = entry.getValue();
                }
            }
            if (firstK == 1 && firstV == 1) {
                return true;
            }

            if (firstK - secondK == 1) {
                if (firstV == 1) {
                    return true;
                }
            }

            if (secondK - firstK == 1) {
                if (secondV == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
