package com.wx.algorithm.leetcode.hot100;

/**
 * @author wangxiang
 * @date 2023/7/4 14:49
 * @description
 */
public class T79SearchWord {
    boolean ans;

    public boolean exist(char[][] board, String word) {
        ans = false;
        int m = board.length, n = board[0].length;

        boolean[][] used = new boolean[m][n];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                traverse(board, word, sb, i, j, used);
                if (ans) {
                    return true;
                }
            }
        }

        return false;
    }

    private void traverse(char[][] board, String word, StringBuilder sb, int i, int j, boolean[][] used) {
        if (ans || word.contentEquals(sb)) {
            ans = true;
            return;
        }
        if (i < 0 || i >= board.length
                || j < 0 || j >= board[0].length
                || used[i][j]
                || board[i][j] != word.charAt(sb.length())) {
            return;
        }

        used[i][j] = true;
        sb.append(board[i][j]);
        traverse(board, word, sb, i - 1, j, used);
        traverse(board, word, sb, i + 1, j, used);
        traverse(board, word, sb, i, j - 1, used);
        traverse(board, word, sb, i, j + 1, used);
        sb.deleteCharAt(sb.length() - 1);
        used[i][j] = false;
    }

    public static void main(String[] args) {
        char[][] board = {{'E', 'A', 'B', 'C'}, {'S', 'S', 'F', 'C'}, {'E', 'A', 'D', 'E'}};
        String word = "ABCCED";
        T79SearchWord searchWord = new T79SearchWord();
        boolean exist = searchWord.exist(board, word);
        System.out.println("exist = " + exist);
    }
}
