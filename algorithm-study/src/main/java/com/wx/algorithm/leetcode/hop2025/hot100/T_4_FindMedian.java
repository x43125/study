package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_4_FindMedian {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        int i = 0, j = 0;
        int mid = (l1 + l2) / 2;
        int pre = 0, now = 0;

        while ((i + j) < mid+1 && (i < l1 || j < l2)) {
            pre = now;
            if (i >= l1) {
                now = nums2[j];
                j++;
                continue;
            }
            if (j >= l2) {
                now = nums1[i];
                i++;
                continue;
            }
            if (nums1[i] < nums2[j]) {
                now = nums1[i];
                i++;
            } else {
                now = nums2[j];
                j++;
            }
        }

        return (l1 + l2) % 2 == 0 ? ((now + pre) / 2.0) : now;
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 3, 6 };
        int[] nums2 = { 4, 5, 7};
        T_4_FindMedian findMedian = new T_4_FindMedian();
        double medianSortedArrays = findMedian.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }
}
