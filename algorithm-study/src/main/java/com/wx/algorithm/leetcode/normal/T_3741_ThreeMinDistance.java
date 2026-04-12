package com.wx.algorithm.leetcode.normal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class T_3741_ThreeMinDistance {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        // 与当前下标的值相同的下一个下标
        int[] next = new int[n];
        Arrays.fill(next, -1);
        Map<Integer, Integer> map = new HashMap<>();
        int ans = n + 1;
        for (int i=n-1; i>=0; i--) {
            if (map.containsKey(nums[i])) {
                // 判断有没有和当前一致的值，有的话，则将 【与当前下标数值一样的下一个下标】存到 next[i] 中
                next[i] = map.get(nums[i]);
            }
            // 更新nums[i]的值的最近一个下标，有点 dp的意思
            map.put(nums[i], i);
        }

        for (int i=0; i<n; i++) {
            int secondPos = next[i];
            if (secondPos != -1) {
                int thirdPos = next[secondPos];
                if (thirdPos != -1) {
                    ans = Math.min(ans, thirdPos-i);
                }
            }
        }

        return ans == n + 1 ? -1 : ans * 2;
    }

    public int minimumDistance_02(int[] nums) {
        int n = nums.length;
        // 与当前下标的值相同的前前一个下标
        int[] pre = new int[n];
        Arrays.fill(pre, -1);
        // 当前值的上一个下标
        Map<Integer, Integer> map = new HashMap<>();
        int ans = n + 1;

        for (int i=0; i<n; i++) {
            if (map.containsKey(nums[i])) {
                // 前一个值
                pre[i] = map.get(nums[i]);
                int secondPos = pre[i];
                if (secondPos != -1) {
                    // 再前一个值
                    int firstPos = pre[secondPos];
                    if (firstPos != -1) {
                        ans = Math.min(ans, i-firstPos);
                    }
                }
            }
            map.put(nums[i], i);
        }

        return ans == n + 1 ? -1 : ans * 2;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,1,1,3};
        T_3741_ThreeMinDistance t_3741_ThreeMinDistance = new T_3741_ThreeMinDistance();
        int minimumDistance = t_3741_ThreeMinDistance.minimumDistance_02(arr);
        System.out.println(minimumDistance);
    }
}
