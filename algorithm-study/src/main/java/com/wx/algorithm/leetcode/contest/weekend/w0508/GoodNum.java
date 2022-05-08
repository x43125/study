package com.wx.algorithm.leetcode.contest.weekend.w0508;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/08
 */
public class GoodNum {
    public static void main(String[] args) {
        System.out.println(largestGoodInteger("6777133339"));
    }

    public static String largestGoodInteger(String num) {
        int length = num.length();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length - 2; i++) {
            if (num.charAt(i) == num.charAt(i + 1) && num.charAt(i) == num.charAt(i + 2)) {
                int nowMax = Integer.parseInt(num.substring(i, i + 3));
                max = Math.max(max, nowMax);
                i += 1;
            }
        }

        String res = String.valueOf(max);
        if (max == Integer.MIN_VALUE ) {
            res = "";
        } else if (max == 0) {
            res = "000";
        }

        return res;
    }
}
