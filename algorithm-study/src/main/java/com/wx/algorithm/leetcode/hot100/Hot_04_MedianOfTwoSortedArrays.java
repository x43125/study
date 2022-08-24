package com.wx.algorithm.leetcode.hot100;

/**
 * @Descrption: Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 * @Author: x43125
 * @Date: 22/08/14
 */
public class Hot_04_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        Hot_04_MedianOfTwoSortedArrays median = new Hot_04_MedianOfTwoSortedArrays();
        double medianSortedArrays = median.findMedianSortedArrays(arr1, arr2);
        System.out.println("medianSortedArrays = " + medianSortedArrays);
    }

    /**
     * Input: nums1 = [1,2], nums2 = [3,4]
     * Output: 2.50000
     * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
     *
     * @param nums1
     * @param nums2
     * @return todo 边界 很他吗有代表性
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        int mid = len / 2;

        for (int i = 0; i <= mid; i++) {
            left = right;
            if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])) {
                right = nums1[aStart++];
            } else {
                right = nums2[bStart++];
            }
        }


        return (len & 1) == 0 ? (left + right) / 2.0 : right;
    }
}































