package com.wx.algorithm.labuladong.rollback;

import java.util.LinkedList;
import java.util.List;

/**
 * @Descrption: 'Q' indicate a queen and '.' indicate an empty space
 * @Author: x43125
 * @Date: 22/05/19
 */
public class N_Queens_02 {
    private static List<List<String>> RES_LIST;

    public List<List<String>> solveNQueens(int n) {
        RES_LIST = new LinkedList<>();
        LinkedList<Integer> paths = new LinkedList<>();
        traverse(n, paths);
        return RES_LIST;
    }

    private void traverse(int n, LinkedList<Integer> paths) {
        if (n == paths.size()) {
            LinkedList<String> temp = makePaths(paths, n);
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
            paths.add(i);
            // 然后到下一行
            traverse(n, paths);
            // 找完了回来，则需要回溯，
            paths.removeLast();
        }
    }

    private LinkedList<String> makePaths(LinkedList<Integer> paths, int n) {
        LinkedList<String> thisRes = new LinkedList<>();
        for (Integer path : paths) {
            thisRes.add(makePath(path, n));
        }
        return thisRes;
    }

    private String makePath(int i, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int j = 0; j < n; j++) {
            sb.append(j == i ? "Q" : ".");
        }
        return sb.toString();
    }

    private boolean validCoordinate(int i, LinkedList<Integer> paths) {
        int length = paths.size();
        for (int x = 0; x < length; x++) {
            int y = paths.get(x);
            if (y == i) {
                return false;
            }
            int xAbs = Math.abs(length - x);
            int yAbs = Math.abs(y - i);
            if (xAbs == yAbs) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        int n = 4;
        N_Queens_02 queens = new N_Queens_02();
        List<List<String>> lists = queens.solveNQueens(n);
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.println(s + " ");
            }
            System.out.println();
        }
    }
}
