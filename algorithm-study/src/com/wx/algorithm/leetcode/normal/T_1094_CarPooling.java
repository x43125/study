package com.wx.algorithm.leetcode.normal;

import com.wx.algorithm.labuladong.array.differarray.DifferArray;

public class T_1094_CarPooling {

    /**
     * simple solution
     * 
     * @param trips
     * @param capacity
     * @return
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        for (int i = 0; i < trips.length; i++) {
            for (int j = 0; j < trips[i].length; j++) {
                left = Math.min(left, trips[i][j]);
                right = Math.max(right, trips[i][j]);
            }
        }

        int max = Integer.MIN_VALUE;
        int[] carPoolArr = new int[right + 1];
        System.out.println(carPoolArr.length);
        boolean flag = true;
        for (int i = 0; i < trips.length; i++) {
            for (int j = trips[i][1]; j < trips[i][2]; j++) {
                carPoolArr[j] += trips[i][0];
                max = Math.max(max, carPoolArr[j]);
                if (max > capacity) {
                    flag = false;
                    break;
                }
            }
        }

        for (int i : carPoolArr) {
            System.out.print(i + " ");
        }
        System.out.println();
        return flag;
    }

    public static boolean carPoolingWithDiffArr(int[][] trips, int capacity) {
        // 最多有 1001 个车站
        int[] nums = new int[1001];
        // 构造差分解法
        DifferArray df = new DifferArray(nums);
        for (int[] trip : trips) {
            int val = trip[0];
            int i = trip[1];
            int j = trip[2] - 1;
            // 进行区间操作
            df.increment(i, j, val);
        }
        int[] res = df.result();
        // 客车自始至终都不应该超载
        for (int i = 0; i < res.length; i++) {
            if (capacity < res[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] trips = {
                { 94, 780, 863 }, { 40, 573, 889 }, { 48, 125, 620 }, { 73, 150, 447 }, { 6, 116, 786 },
                { 72, 171, 548 }, { 35, 267, 950 }, { 32, 169, 481 }, { 7, 595, 829 }, { 65, 926, 982 },
                { 49, 770, 832 }, { 5, 232, 851 }, { 30, 413, 783 }, { 5, 424, 454 }, { 37, 218, 777 },
                { 22, 143, 952 }, { 23, 402, 672 }, { 65, 755, 847 }, { 20, 193, 939 }, { 100, 586, 759 },
                { 13, 339, 954 }, { 40, 665, 767 }, { 28, 209, 736 }, { 27, 11, 761 }, { 5, 904, 911 },
                { 56, 54, 332 }, { 11, 91, 424 }, { 8, 379, 826 }, { 33, 516, 676 }, { 66, 930, 960 }
        };
        
        boolean canFill = carPooling(trips, 490);
        boolean canFillWithDiff = carPoolingWithDiffArr(trips, 490);
        System.out.println(canFill);
        System.out.println(canFillWithDiff);
    }
}
