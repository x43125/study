package com.wx.algorithm.leetcode.normal;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/08
 */
public class T_870_AdvantageShuffle {
    public static void main(String[] args) {
        int[] nums1 = {2,7,11,15}, nums2 = {1,10,4,11};
        int[] ints = advantageCount(nums1, nums2);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

    public static int[] advantageCount(int[] nums1, int[] nums2) {
        PriorityQueue<int[]> maxPq = new PriorityQueue<>(
                (int[] p1, int[] p2) -> p2[1] - p1[1]
        );

        for (int i = 0; i < nums1.length; i++) {
            maxPq.offer(new int[]{i, nums2[i]});
        }

        Arrays.sort(nums1);

        int left = 0, right = nums1.length-1;
        int[] res = new int[nums1.length];

        while (!maxPq.isEmpty()) {
            int[] poll = maxPq.poll();
            int i = poll[0], maxV = poll[1];

            if (nums1[right] > maxV) {
                res[i] = nums1[right];
                right--;
            } else {
                res[i] = nums1[left];
                left++;
            }
        }

        return res;
    }
}
