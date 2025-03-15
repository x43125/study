package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class T_239_MaxSlideWindow {

    /**
     * 滑动窗口中最大的数字
     * 方法一：语法糖：优先队列
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // nums: 1, 2, 3, 2    n=3, 
        // k = 2
        int n = nums.length;
        int[] max = new int[n - k + 1];
        // 值-下标
        // 重点！！！ 如果是C语言怎么实现呢？
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 优先根据值排序，其次根据下标排序
                return o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0];
            }            
        });

        for (int i = 0; i < k; i++) {
            queue.add(new int[]{nums[i], i});
        }

        max[0] = queue.peek()[0];

        for (int i = k; i < n; i++) {
            queue.offer(new int[]{nums[i], i});
            while (queue.peek()[1] < i - k +1) {
                queue.poll();
            }
            max[i-k+1] = queue.peek()[0];
        }

        return max;
    }

    public int[] maxSlidingWindow_2(int[] nums, int k) {
        int n = nums.length;
        int[] max = new int[n - k + 1];

        // 双端-单调队列
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!q.isEmpty() && nums[i] >= nums[q.peekLast()]) {
                q.pollLast();
            }
            q.offerLast(i);
        }

        max[0] = nums[q.peekFirst()];

        for (int i = k; i < n; i++) {
            while (!q.isEmpty() && nums[i] >= nums[q.peekLast()]) {
                q.pollLast();
            }
            q.offerLast(i);
            while (q.peekFirst() < i - k + 1) {
                q.pollFirst();
            }
            max[i - k + 1] = nums[q.peekFirst()]; 
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        T_239_MaxSlideWindow maxSlideWindow = new T_239_MaxSlideWindow();
        int[] max = maxSlideWindow.maxSlidingWindow_2(nums, k);
        for (int m : max) {
            System.out.print(m + " ");
        }
        System.out.println();
    }
}
