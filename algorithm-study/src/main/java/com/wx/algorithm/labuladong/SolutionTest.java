package com.wx.algorithm.labuladong;

public class SolutionTest {
    public int climbStairs(int n, int[] costs) {
        int zero = 0;
        int first = costs[0] + 1;
        int second = Math.min(zero + costs[1] + 4, first + costs[1] + 1);
        int third = 0;

        for (int i = 3; i <= n; i++) {
            third = Math.min(zero + costs[i-1]+9, first + costs[i-1]+4);
            third = Math.min(third, second + costs[i-1]+1);
            zero = first;
            first = second;
            second = third;
        }
        return third;
    }

    public static void main(String[] args) {
        SolutionTest solution = new SolutionTest();
        int ans = solution.climbStairs(4, new int[] { 1, 2, 3 , 4});
        System.out.println(ans);
    }
}
