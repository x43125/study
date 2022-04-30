package com.wx.algorithm.leetcode.contest.weekend.w0430;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/30
 */
public class MinAverageDifference {

    public static void main(String[] args) {
        int[] nums = {2, 5, 3, 9, 5, 3};
//        int[] nums = {0};
//        int[] nums = {0, 0, 0, 0, 0};
//        int[] nums = {4, 2, 0};
//        int[] nums = getNums();
        System.out.println(nums.length);
        System.out.println(minimumAverageDifference(nums));
    }

    private static int[] getNums() {
        String s;
        try (FileReader fr = new FileReader("F:\\study\\algorithm-study\\src\\main\\java\\com\\wx\\algorithm\\leetcode\\contest\\weekend\\w0430\\test.txt");
             BufferedReader br = new BufferedReader(fr)) {
            s = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(s.length());
        String[] split = s.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        return nums;
    }

    public static int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        arr[0] = nums[0];
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i - 1] + nums[i];
        }

        long minDiff = Long.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            long leftAvg = arr[i] / (i + 1);
            long rightAvg = (arr[n - 1] - arr[i]) / (n - i - 1);

            long diff = Math.abs(leftAvg - rightAvg);
            if (diff < minDiff) {
                res = i;
                minDiff = diff;
            }
        }

        long leftAvg = arr[n - 1] / (n);
        long rightAvg = 0;

        long diff = Math.abs(leftAvg - rightAvg);
        if (diff < minDiff) {
            res = n - 1;
        }
        return res;
    }
}
