/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-09-01 11:53:37
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-09-01 13:58:28
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\T_496_NextBigNo.java
 * @Description: 
 *  输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
 *  输出：[-1,3,-1]
 */
package com.wx.algorithm.leetcode.normal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class T_496_NextBigNo {

    /**
     * 暴力法
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);

        for (int i = 0; i<nums1.length; i++) {
            boolean find = false;
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    find = true;
                    continue;
                }

                if (find && nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }

        return res;
    }

    /**
     * 单调栈
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement_2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(nums2.length);
        Stack<Integer> stack = new Stack<>();
        for (int i=nums2.length-1; i>-1; i--) {
            while (!stack.isEmpty()) {
                if (stack.peek() < nums2[i]) {
                    stack.pop();
                } else {
                    map.put(nums2[i], stack.peek());
                    stack.push(nums2[i]);
                    break;
                }
            }
            if (stack.isEmpty()) {
                map.put(nums2[i], -1);
                stack.push(nums2[i]);
            }
        }

        int[] ans = new int[nums1.length];
        for (int i=0; i<nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,1,2}, nums2 = {1,3,4,2};
        T_496_NextBigNo nextBigNo = new T_496_NextBigNo();
        int[] res = nextBigNo.nextGreaterElement(nums1, nums2);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
