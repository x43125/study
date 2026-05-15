package com.wx.algorithm.labuladong;

public class SolutionTest {
    public int climbStairs(int[] costs) {
        // dp[i] = (dp[i-1] + costs[i-1], dp[i-1] + costs[i-2]);
        int first = 0, second = 0;

        for (int i = 2; i <= costs.length; i++) {
            int three = Math.min(first + costs[i-2], second + costs[i-1]);
            first = second;
            second = three;
        }

        return second;
    }

    public static void main(String[] args) {
        SolutionTest solution = new SolutionTest();
        int ans = solution.climbStairs(new int[] { 2, 100});
        System.out.println(ans);
    }
}
