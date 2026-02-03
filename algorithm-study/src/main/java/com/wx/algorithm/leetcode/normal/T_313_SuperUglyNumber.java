package com.wx.algorithm.leetcode.normal;

public class T_313_SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        // dp[i] 表示第 i 个超级丑数
        int[] dp = new int[n];
        dp[0] = 1; // 第一个丑数是 1

        // pointers 数组：为每个质数维护一个指针，指向 dp 数组
        int k = primes.length;
        int[] pointers = new int[k];

        // 为每个质数计算当前候选值
        int[] candidates = new int[k];
        for (int i = 0; i < k; i++) {
            candidates[i] = primes[i] * dp[0]; // 初始候选值
        }

        for (int i = 1; i < n; i++) {
            // 找出最小的候选值作为下一个丑数
            int minVal = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                if (candidates[j] < minVal) {
                    minVal = candidates[j];
                }
            }

            dp[i] = minVal;

            // 更新产生最小值的所有质数的指针
            for (int j = 0; j < k; j++) {
                if (candidates[j] == minVal) {
                    pointers[j]++; // 移动指针
                    // 计算新的候选值
                    candidates[j] = primes[j] * dp[pointers[j]];
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        T_313_SuperUglyNumber superUglyNumber = new T_313_SuperUglyNumber();
        int nthSuperUglyNumber = superUglyNumber.nthSuperUglyNumber(12, new int[]{2,7,13,19});
        System.out.println(nthSuperUglyNumber);
    }
}
