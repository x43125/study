package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_279_NumSquares {
    public int numSquares(int n) {
        // f[n] = 1 + min(从1到根号(n-1))中f(n - j)
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, f[i-j*j]);
            }
            f[i] = 1 + min;
        }

        return f[n];
    }

    public static void main(String[] args) {
        int n = 9;
        T_279_NumSquares numSquares = new T_279_NumSquares();
        int num = numSquares.numSquares(n);
        System.out.println(num);
    }
}
