package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_70_ClimbStairs {

    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int first = 1, sec = 2;
        int now = 0;
        for (int i = 3; i <= n; i++) {
            now = first + sec;
            first = sec;
            sec = now;
        }

        return now;
    }

    public static void main(String[] args) {
        int n = 3;
        T_70_ClimbStairs climbStairs = new T_70_ClimbStairs();
        int count = climbStairs.climbStairs(n);
        System.out.println(count);
    }
}
