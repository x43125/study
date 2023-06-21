package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/6/21 09:56
 * @description
 */
public class T56MergeIntervals {
    public static void main(String[] args) {
//        int[][] intervals = {{1, 3}, {2, 6}, {2, 5}, {8, 10}, {7, 10},{7,9}, {15, 18}};
//        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] intervals = {{1, 4}, {4, 5}};
//        int[][] intervals = {{1, 3},{1,4}, {4, 4}};
//        int[][] intervals = {{1, 3}};
        int[][] intervals = {{1, 4}, {2, 3}};
        // {{1, 3}, {2, 5}, {2, 6}, {7, 9}, {7, 10},{8,10}, {15, 18}};
        int[][] merge = merge(intervals);
        for (int[] nums : merge) {
            for (int num : nums) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

    }

    /**
     * 9:56 -
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        // 按照第一个值排序，如果第一个值相同，则按照第二个值排序
        Arrays.sort(intervals, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int n = intervals.length;
        int[] pre = intervals[0];
        List<int[]> ansList = new ArrayList<>();
        for (int[] nums : intervals) {
            if (nums[0] <= pre[1] && nums[1] >= pre[1]) {
                pre[1] = nums[1];
            } else if (nums[1] >= pre[1]){
                ansList.add(pre);
                pre = Arrays.copyOf(nums, 2);
            }
        }

        ansList.add(pre);
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
