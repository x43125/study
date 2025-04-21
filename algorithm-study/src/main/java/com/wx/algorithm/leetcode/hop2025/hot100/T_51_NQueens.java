package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.List;

public class T_51_NQueens {
    static boolean[][] used;
    static List<List<String>> result;
    static int cnt;

    public List<List<String>> solveNQueens(int n) {
        // 每次防止棋子的时候，需要考虑当前点位是否在此前的所有棋子的正下列、左斜列、右斜列上，如果在就不能放，继续下一个
        // 根据i,j可以算出当前点是否在左右斜列上
        used = new boolean[n][n];
        result = new ArrayList<>();
        cnt = n;
        dfs(0, new ArrayList<String>(), used);
        return result;
    }

    private void dfs(int i, List<String> list, boolean[][] used) {
        if (i == cnt) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int k = 0; k < cnt; k++) {
            if (inLine(i, k, used)) {
                continue;
            }
            list.add(buildStr(k, cnt));
            used[i][k] = true;
            dfs(i+1, list, used);
            used[i][k] = false;
            list.remove(list.size()-1);
        }
    }

    private boolean inLine(int i, int j, boolean[][] used) {
        for (int m = 0; m < used.length; m++) {
            for (int n = 0; n < used.length; n++) {
                if (used[m][n]) {
                    if (n == j || (i - m == j - n) || (i - m == n - j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String buildStr(int k, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i != k) {
                sb.append('.');
            } else {
                sb.append('Q');
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 5;
        T_51_NQueens nQueens = new T_51_NQueens();
        List<List<String>> queens = nQueens.solveNQueens(n);
        queens.forEach(list -> {
            list.forEach(System.out::println);
            System.out.println();
            System.out.println();
        });
    }
}
