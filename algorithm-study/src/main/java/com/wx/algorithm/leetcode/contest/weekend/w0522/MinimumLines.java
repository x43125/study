package com.wx.algorithm.leetcode.contest.weekend.w0522;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/22
 */
public class MinimumLines {
    public static int minimumLines(int[][] stockPrices) {
        if (stockPrices.length == 1 || stockPrices.length == 2) {
            return 1;
        }

        Arrays.sort(stockPrices, Comparator.comparingInt(o -> o[0]));
        int[] direction = {stockPrices[1][0] - stockPrices[0][0], stockPrices[1][1] - stockPrices[0][1]};

        int res = 1;
        for (int i = 2; i < stockPrices.length; i++) {
            int[] nowDirection = {stockPrices[i][0] - stockPrices[i - 1][0], stockPrices[i][1] - stockPrices[i - 1][1]};

            if (nowDirection[1] * direction[0] == nowDirection[0] * direction[1]) {
                continue;
            }

            res++;
            direction = nowDirection;
        }

        return res;
    }

    public static void main(String[] args) {
//        int[][] stockPrices = {{3, 4}, {1, 2}, {7, 8}, {2, 3}};
//        int[][] stockPrices = {{3, 4}};
//        int[][] stockPrices = {{1, 1}, {499999999, 2}, {999999998, 3}, {1000000000, 5}};
//        int[][] stockPrices = {{1, 7}, {2, 6}, {3, 5}, {4, 4}, {5, 4}, {6, 3}, {7, 2}, {8, 1000}, {123,78912381}};
        int[][] stockPrices = {{1, 7}, {2, 6}, {3, 5}};
        System.out.println(minimumLines(stockPrices));

//        System.out.println(2 % (-111213123));
//        System.out.println(2 % 111213123);
//
//
//        System.out.println(-111213123 % 2);
//
//        System.out.println(111213123 % 2);
//
//        System.out.println(2 / (-111213123));
//        System.out.println(-111213123 / 2);
//
//        System.out.println(2 / 111213123);
//        System.out.println(111213123 / 2);
    }

}
