package com.wx.algorithm.Java.sort;

import com.wx.algorithm.Java.sort.util.SortUtil;

/**
 * 冒泡排序
 *
 * @author wangxiang
 * @date 2023/7/6 23:17
 * @description
 */
public class Bubble {

    public static void sort(int[] nums) {
        int n = nums.length;
        boolean flag;
        for (int i = n - 1; i > 0; i--) {
            flag = false;
            for (int j = 0; j < i; j++) {
                System.out.println("1");
                if (nums[j + 1] < nums[j]) {
                    SortUtil.swap(nums, j, j + 1);
                    flag = true;
                }
            }
            // 优化，如果已经有序则无须再继续迭代了
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
//        int[] nums = {4, 3, 8, 2, 5, 1, 0};
        int[] nums = {1,0,2,3,4,5,6,7,8};
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        sort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
