package com.wx.algorithm.leetcode.contest.weekend.w0522;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/22
 */
public class PercentageWord {
    public static void main(String[] args) {
        System.out.println(percentageLetter("foobar", 'r'));
    }

    public static int percentageLetter(String s, char letter) {
        double sum = 0;
        for (char c:s.toCharArray()) {
            if (c == letter) {
                sum++;
            }
        }

        return (int) (sum / s.length() * 100);
    }
}
