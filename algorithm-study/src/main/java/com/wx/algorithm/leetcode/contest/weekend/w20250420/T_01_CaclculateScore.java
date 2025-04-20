package com.wx.algorithm.leetcode.contest.weekend.w20250420;

public class T_01_CaclculateScore {

    boolean[] used;
    public long calculateScore(String[] instructions, int[] values) {
        used = new boolean[values.length];
        int i = 0;
        long score = 0;
        while (i >= 0 && i < values.length && !used[i]) {
            used[i] = true;
            if ("add".equals(instructions[i])) {
                score += values[i];
                i++;
            } else {
                i += values[i];
            }
        }
        return score;
    }

    public static void main(String[] args) {
        // String[] instructions = {"jump","add","add","jump","add","jump"};
        // int[] values = {2,1,3,1,-2,-3};
        String[] instructions = {"add"};
        int[] values = {2};
        T_01_CaclculateScore caclculateScore = new T_01_CaclculateScore();
        long calculateScore = caclculateScore.calculateScore(instructions, values);
        System.out.println(calculateScore);
    }
}
