package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Shawn
 * @date 2023/7/10 09:53
 * @description
 */
public class T51SolveNQueens {
    List<List<String>> ans;
    List<String> nowAns;

    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        nowAns = new ArrayList<>();
        // 如果当前节点放在某一点，则此行，此列，此斜列无法防止其他皇后
        // 校验：当前行是否有；当前列是否有；当前斜列是否有
        // 如果校验到最后一个格子之后，还有皇后无法放置，则说明此摆法不对，需要回溯重摆
        boolean[][] chessboard = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            // 初始为未放置皇后的空气盘: false表示未放置Q
            Arrays.fill(chessboard[i], false);
        }

        dfs(n, chessboard, 0);
        return ans;
    }

    private void dfs(int n, boolean[][] chessboard, int i) {
        if (i == n) {
            ans.add(new ArrayList<>(nowAns));
            return;
        }

        for (int j = 0; j < n; j++) {
            if (canPut(chessboard, i, j)) {
                String nowAnsStr = makeNowAns(j, n);
                nowAns.add(nowAnsStr);
                chessboard[i][j] = true;
                dfs(n, chessboard, i + 1);
                chessboard[i][j] = false;
                nowAns.remove(nowAns.size() - 1);
            }
        }
    }

    private boolean canPut(boolean[][] chessboard, int i, int j) {
        // 可优化点：此处用map来存，可以不用每次都遍历
        for (int x = 0; x <= i; x++) {
            for (int y = 0; y < chessboard[0].length; y++) {
                if (chessboard[x][y] && (x == i || y == j || (onOblique(x, y, i, j)))) {
                    return false;
                }
            }
        }
        return true;
    }

    private String makeNowAns(int i, int n) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < n; j++) {
            if (j == i) {
                sb.append("Q");
            } else {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private boolean onOblique(int x, int y, int i, int j) {
        return Math.abs(x - i) == Math.abs(y - j);
    }

    public static void main(String[] args) {
        int n = 4;
        T51SolveNQueens solveNQueens = new T51SolveNQueens();
        List<List<String>> ans = solveNQueens.solveNQueens(n);
        for (List<String> strs : ans) {
            for (String str : strs) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
