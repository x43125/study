package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_79_SearchWord {
    private static boolean exist;
    private static boolean[][] used;

    public boolean exist(char[][] board, String word) {
        used = new boolean[board.length][board[0].length];
        exist = false;
        for (int i = 0; i < board.length && !exist; i++) {
            for (int j = 0; j < board[0].length && !exist; j++) {
                dfs(board, i, j, word, 0, used);
            }
        }

        return exist;
    }

    private void dfs(char[][] board, int i, int j, String word, int index, boolean[][] used) {
        if (exist) {
            return;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (used[i][j]) {
            return;
        }
        if (word.charAt(index) != board[i][j]) {
            return;
        }
        if (index == word.length() - 1) {
            exist = true;
            return;
        }

        used[i][j] = true;
        dfs(board, i, j + 1, word, index + 1, used);
        dfs(board, i + 1, j, word, index + 1, used);
        dfs(board, i, j - 1, word, index + 1, used);
        dfs(board, i - 1, j, word, index + 1, used);
        used[i][j] = false;
    }

    public static void main(String[] args) {
        char[][] board = { { 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a' } };
        String word = "aaaaaaaaaaaaa";
        T_79_SearchWord searchWord = new T_79_SearchWord();
        boolean exist = searchWord.exist(board, word);
        System.out.println(exist);
    }
}
