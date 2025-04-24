package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.Stack;

public class T_739_DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                ans[stack.peek()] = i - stack.pop();
            }
            stack.add(i);
        }
        ans[n - 1] = 0;
        return ans;
    }

    public static void main(String[] args) {
        int[] temperature = { 60,50,40,30 };
        T_739_DailyTemperatures dailyTemperatures = new T_739_DailyTemperatures();
        int[] temperatures = dailyTemperatures.dailyTemperatures(temperature);
        for (int t : temperatures) {
            System.out.print(t + " ");
        }
        System.out.println();
    }
}
