package com.wx.algorithm.labuladong.rollback;

import java.util.LinkedList;
import java.util.List;

/**
 * @Descrption: 'Q' indicate a queen and '.' indicate an empty space
 * @Author: x43125
 * @Date: 22/05/19
 */
public class N_Queens {
    private static final List<List<String>> RES_LIST = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        LinkedList<String> paths = new LinkedList<>();
        traverse(n, paths);
        return RES_LIST;
    }

    private void traverse(int n, LinkedList<String> paths) {
        if (n == paths.size()) {
            LinkedList<String> temp = new LinkedList<>(paths);
            RES_LIST.add(temp);
            return;
        }

        // 当前行的每一列都可以作为皇后的放置位置
        for (int i = 0; i < n; i++) {
            // 判断当前坐标是否合理，如果不合理则跳到下一个
            if (!validCoordinate(i, paths)) {
                continue;
            }
            // 如果合理，则此行就用这个，将此点加进这次的通关路径种去
            paths.add(makePath(i, n));
            // 然后到下一行
            traverse(n, paths);
            // 找完了回来，则需要回溯，
            paths.removeLast();
        }
    }

    private String makePath(int i, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int j = 0; j < n; j++) {
            sb.append(j == i ? "Q" : ".");
        }
        return sb.toString();
    }

    private boolean validCoordinate(int i, LinkedList<String> paths) {
        int length = paths.size();
        for (int x = 0; x < length; x++) {
            int y = paths.get(x).indexOf("Q");
            int xAbs = Math.abs(length - x);
            int yAbs = Math.abs(y - i);
            if (xAbs == yAbs || y == i) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        int n = 5;
        N_Queens queens = new N_Queens();
        List<List<String>> lists = queens.solveNQueens(n);
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.println(s + " ");
            }
            System.out.println();
        }
    }
}
