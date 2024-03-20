package com.wx.algorithm.leetcode.hot100;

/**
 * @author Shawn
 * @date 2024/3/20 23:00
 * @description
 */
public class T4FindMid {

    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);

        System.out.println("medianSortedArrays = " + medianSortedArrays);
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 中位数：中间的数，排序后最中间的那个数，如果是偶数，就求和再处以2
        // 双指针法
        int i = 0, j = 0;
        int m = nums1.length, n = nums2.length;
        boolean odd = ((m + n) % 2 != 0);
        int last = odd ? ((m + n) / 2) : ((m + n) / 2 - 1);
        // 如果是偶数：有两个中位数
        // 如果是奇数则只有一个中位数
        int count = -1;
        int now = 0;
        while (i < m && j < n) {
            if (count == last) {
                // 如果都还未到边界的话
                if (odd) {
                    return now;
                } else {
                    return (now + Math.min(nums1[i], nums2[j])) / 2.0;
                }
            } else {
                if (nums1[i] < nums2[j]) {
                    now = nums1[i];
                    i++;
                    count++;
                } else {
                    now = nums2[j];
                    j++;
                    count++;
                }
            }
        }

        while (i < m) {
            if (count == last) {
                return odd ? now : (now + nums1[i]) / 2.0;
            }
            now = nums1[i];
            count++;
            i++;
        }

        while (j < n) {
            if (count == last) {
                return odd ? now : (now + nums2[j]) / 2.0;
            }
            now = nums2[j];
            count++;
            j++;
        }

        return now;
    }
}
