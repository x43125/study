package com.wx.algorithm.leetcode.normal;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/26
 */
public class T_37_Sudoku {

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
        T_37_Sudoku sudoku = new T_37_Sudoku();
        sudoku.solveSudoku(board);
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }

    }

    public void solveSudoku(char[][] board) {
        backTrack(board, 0, 0);
    }

    private boolean backTrack(char[][] board, int i, int j) {
        int m = 9, n = 9;
        if (j == n) {
            return backTrack(board, i + 1, 0);
        }
        if (i == m) {
            return true;
        }
        if (board[i][j] != '.') {
            return backTrack(board, i, j + 1);
        }

        for (char k = '1'; k <= '9'; k++) {
//            如果遇到不合法的数字就跳过
            if (!isValid(k, board, i, j)) {
                continue;
            }
            board[i][j] = k;
            if (backTrack(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    private boolean isValid(char k, char[][] board, int i, int j) {
        for (int l = 0; l < 9; l++) {
            if (board[i][l] == k || board[l][j] == k || board[l / 3 + (i / 3) * 3][l % 3 + (j / 3) * 3] == k) {
                return false;
            }
        }
        return true;
    }
}
