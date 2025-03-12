package com.wx.algorithm.leetcode.hop2025;

import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class T_239_MaxSlideWindow {
    public static void main(String[] args) {
        int[] nums = { 1, 3, -1, -3, -2, 3, 6, 7 };
        // int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;
        T_239_MaxSlideWindow maxSlideWindow = new T_239_MaxSlideWindow();
        int[] res = maxSlideWindow.maxSlidingWindow3(nums, k);
        for (int max : res) {
            System.out.print(max + " ");
        }
        System.out.println();
    }

    /**
     * 暴力法
     * 
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        Queue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] != o2[1] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            q.offer(new int[]{nums[i], i});
        }
        res[0] = q.peek()[0];

        for (int i = k; i < nums.length; i++) {
            q.offer(new int[]{nums[i], i});
            while (q.peek()[1] < i-k+1) {
                q.poll();
            }
            res[i-k+1] = q.peek()[0];
        }

        return res;
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {

        // 大顶堆，为了判断位置是否超出滑动窗口，所以存储数组，0-值，1-index
        Queue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] != o2[1] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });

        for (int i = 0; i < k; i++) {
            q.offer(new int[]{nums[i], i});
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        result[0] = q.peek()[0];

        for (int i = k; i < n; i++) {
            q.offer(new int[]{nums[i], i});
            while(q.peek()[1] < i - k + 1) {
                q.poll();
            }
            result[i-k+1] = q.peek()[0];
        }

        return result;
    }
}
