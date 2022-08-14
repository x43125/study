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
     * @return todo 边界 恨他吗有代表性
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        boolean singular = false;
        int length = nums1.length + nums2.length;
        if (length % 2 != 0) {
            singular = true;
        }

        int left = length / 2;
        int right = singular ? left : left + 1;

        int i = 0, j = 0;
        double res = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
                if (left == 0) {
                    res += nums1[i - 1];
                }
                if (right == 0) {
                    res += nums1[i - 1];
                    return res / 2;
                }
            } else {
                j++;
                if (left == 0) {
                    res += nums2[j - 1];
                }
                if (right == 0) {
                    res += nums2[j - 1];
                    return res / 2;
                }
            }
            left--;
            right--;
        }

        while (i != nums1.length) {
            i++;
            if (left == 0) {
                res += nums1[i - 1];
            }
            if (right == 0) {
                res += nums1[i - 1];
                return res / 2;
            }
            left--;
            right--;
        }

        while (j != nums2.length) {
            j++;
            if (left == 0) {
                res += nums2[j - 1];
            }
            if (right == 0) {
                res += nums2[j - 1];
                return res / 2;
            }
            left--;
            right--;
        }
        return 0;
    }
}
