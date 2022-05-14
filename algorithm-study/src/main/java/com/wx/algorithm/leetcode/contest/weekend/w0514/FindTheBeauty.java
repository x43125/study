package com.wx.algorithm.leetcode.contest.weekend.w0514;

import java.util.Set;
import java.util.TreeSet;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/14
 */
public class FindTheBeauty {

    public static int divisorSubstrings(int num, int k) {
        String numStr = String.valueOf(num);
        if (numStr.length() < k) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i <= numStr.length() - k; i++) {
            int now = Integer.parseInt(numStr.substring(i, i + k));
            if (now == 0) {
                continue;
            }
            if (num % now == 0) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int num = 240, k = 4;
        int i = divisorSubstrings(num, k);
        System.out.println(i);
    }
}
