package com.wx.algorithm.leetcode.hot100;

import java.util.*;

/**
 * @author wangxiang
 * @date 2023/6/20 10:15
 * @description
 */
public class T239MaxSlidingWindow {

    public static void main(String[] args) {
//        int[] nums = {7, 2, 4};
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] nums = {1, -1};
        int k = 3;
        int[] maxArray = maxSlidingWindow2(nums, k);
        for (int max : maxArray) {
            System.out.print(max + " ");
        }
        System.out.println();
    }

    /**
     * 暴力法：超时
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 如果k==1则为每次都是最大值
        if (k == 1) {
            return nums;
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];

        // 当前窗口内的最大值 和 最大值的个数
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        result[0] = max;

        for (int i = 1; i < n - k + 1; i++) {
            if (nums[i + k - 1] >= max) {
                max = nums[i + k - 1];
            } else {
                if (nums[i - 1] == max) {
                    max = Integer.MIN_VALUE;
                    for (int j = 0; j < k; j++) {
                        if (nums[i + j] > max) {
                            max = nums[i + j];
                        }
                    }
                }
            }
            result[i] = max;
        }

        return result;
    }

    /**
     * optimize1: 有线队列
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        // 如果k==1则为每次都是最大值
        if (k == 1) {
            return nums;
        }

        int n = nums.length;
        // 大顶堆：如果两个数组的0值不想等则按照值从大到小排序；如果0值相等则按照index从大到小排序
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);

        // 初始化前k个
        for (int i = 0; i < k; i++) {
            int[] nowIndex = new int[]{nums[i], i};
            queue.offer(nowIndex);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = queue.peek()[0];
        for (int i = k; i < n; i++) {
            queue.offer(new int[]{nums[i], i});
            // 如果队列中大顶是在当前窗口的左侧的则删除掉他
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            // 此时的最大值即为当前窗口下的最大值
            ans[i - k + 1] = queue.peek()[0];
        }

        return ans;
    }
}

