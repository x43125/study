package com.wx.algorithm.leetcode.contest.weekend.w0515;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/15
 */
public class MaxFloor {
    public static int maxConsecutive(int bottom, int top, int[] special) {
        int max = 0;
        Arrays.sort(special);

        List<Integer> list = new ArrayList<>();
        list.add(bottom - 1);
        for (int i : special) {
            list.add(i);
        }
        list.add(top + 1);

        int last = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            max = Math.max(max, list.get(i) - last - 1);
            last = list.get(i);
        }

        return max;
    }

    public static void main(String[] args) {
        int bottom = 3, top = 7;
        int[] special = {3,5};
        System.out.println(maxConsecutive(bottom, top, special));
    }
}
