package com.wx.algorithm.leetcode.contest.weekend.w0515;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/15
 */
public class AndCalc {
    public static void combinations(String suffix, String prefix) {
        System.out.println(suffix);
        for (int i = 0; i < prefix.length(); i++)
            combinations(suffix + prefix.charAt(i), prefix.substring(i + 1));
    }


    public void generate(String input) {
//        cur = new StringBuilder();
        for (int length = 1; length <= input.length(); length++)
            for (int pos = 0; pos + length <= input.length(); pos++)
                next(pos, length);
    }


    int[] candidates;
    int result = 0;

    List<Integer> curList = new ArrayList<>();

    private void next(int pos, int reminder) {

        curList.add(pos);

        if (reminder == 0) {
//            System.out.println(curList.toString());

            int res = candidates[curList.get(0)];
            for (int i = 1; i < curList.size(); i++) {
                int index = curList.get(i);
                res &= candidates[index];
            }
            if (res > 0) {
                System.out.println("res = " + res);
                System.out.println(curList.toString());
                result = curList.size();
                return;
            }
        } else {
            for (int i = pos + 1; i + reminder - 1 < candidates.length; i++) {
                next(i, reminder - 1);
                if (result != 0) {
                    return;
                }
            }
        }
        curList.remove(curList.size() - 1);
    }

    public int largestCombination(int[] candidates) {
        this.candidates = candidates;
        int res = 0;
        for (int length = candidates.length-1; length >= 0; length--) {
            for (int pos = 0; pos + length < candidates.length; pos++) {
                next(pos, length);
                if (result != 0) {
                    return result;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
//        combinations("", "12345");
//        int[] candidates = {8, 8};
//        int[] candidates = {16,17,71,62,12,24,14};
//        int[] candidates = {16};
//        int[] candidates = {1, 5, 3};
        int[] candidates = {16, 16, 48, 71, 62, 12, 24, 14, 17, 18, 19, 20, 10000};
        AndCalc andCalc = new AndCalc();
        int i = andCalc.largestCombination(candidates);
        System.out.println("====================");
        System.out.println(i);
        System.out.println("====================");
        int i1 = largestCombination_2(candidates);
        System.out.println(i1);

//        System.out.println(500000 & 1);

//        System.out.println(1 << 4);
//        System.out.println(1 << 3);
//        System.out.println(1 << 2);
//        System.out.println(1 << 1);
//        System.out.println(1 << 0);
//        System.out.println(0 << 0);
//        System.out.println(0 << 1);
//        System.out.println(0 << 2);
//        System.out.println(0 << 3);
//        System.out.println("=====================");
//        System.out.println(5 >> 4);
//        System.out.println(5 >> 3);
//        System.out.println(5 >> 2);
//        System.out.println(5 >> 1);
//        System.out.println(1 >> 0);
//        System.out.println(0 >> 0);
//        System.out.println(0 >> 1);
//        System.out.println(0 >> 2);
//        System.out.println(0 >> 3);
    }

    public static int largestCombination_2(int[] candidates) {
        int max = 0;
        for (int i = 0; i < 24; i++) {
            int count = 0;
            for (int candidate : candidates) {
                max = Math.max(max, count += (candidate & 1 << i) > 0 ? 1 : 0);
            }
        }
        return max;
    }
}
