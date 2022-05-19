package com.wx.algorithm.labuladong.rollback;

import java.util.LinkedList;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/19
 */
public class FullRange_02 {
    public static void main(String[] args) {
        List<List<Integer>> permute = permute(3);
        for (List<Integer> list : permute) {
            for (Integer value : list) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }


    private static final List<List<Integer>> FULL_LIST = new LinkedList<>();

    public static List<List<Integer>> permute(int num) {
        boolean[] used = new boolean[num];
        LinkedList<Integer> list = new LinkedList<>();
        traverse(num, used, list);
        return FULL_LIST;
    }

    private static void traverse(int num, boolean[] used, LinkedList<Integer> list) {
        if (list.size() == num) {
            LinkedList<Integer> temp = new LinkedList<>(list);
            FULL_LIST.add(temp);
            return;
        }

        for (int i = 0; i < num; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            list.add(i);
            traverse(num, used, list);
            used[i] = false;
            list.removeLast();
        }
    }


}
