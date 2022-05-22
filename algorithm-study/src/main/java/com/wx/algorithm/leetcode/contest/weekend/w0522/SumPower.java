package com.wx.algorithm.leetcode.contest.weekend.w0522;

import java.util.LinkedList;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/22
 */
public class SumPower {
    static int res;
    static LinkedList<Integer> track;
    static int mod = 1000000000 + 7;

    public static int totalStrength(int[] strength) {
        res = 0;
        track = new LinkedList<>();
        boolean[] used = new boolean[strength.length];
        backTrack(strength, track, used, 0);
        return res;
    }

    private static void backTrack(int[] strength, LinkedList<Integer> track, boolean[] used, int start) {
        res += power(track);

        for (int i = start; i < strength.length; i++) {
            if (used[i]) {
                continue;
            }

            track.add(strength[i]);
            used[i] = true;
            backTrack(strength, track, used, i + 1);
            used[i] = false;
            track.removeLast();
        }
    }

    private static int power(LinkedList<Integer> track) {
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (Integer integer : track) {
            sum += integer;
            min = Math.min(min, integer);
        }

        return (min * sum) % mod;
    }

    public static void main(String[] args) {
        int[] strength = {5,4,6};
        System.out.println(totalStrength(strength));
    }
}
