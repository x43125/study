package com.wx.algorithm.leetcode.hop2025;

import java.util.ArrayList;
import java.util.List;

public class T_118_YangHuiTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        first.add(1);
        list.add(first);
        for (int i = 1; i < numRows; i++) {
            List<Integer> level = new ArrayList<>();
            List<Integer> preLevel = list.get(i-1);
            for (int j = 0; j <= i; j++) {
                level.add((j==0 ? 0 : preLevel.get(j-1)) + (j==i ? 0 : preLevel.get(j)));
            }
            list.add(level);
        }

        return list;
    }

    public static void main(String[] args) {
        int n = 5;
        T_118_YangHuiTriangle yangHuiTriangle = new T_118_YangHuiTriangle();
        List<List<Integer>> list = yangHuiTriangle.generate(n);
        list.forEach(l -> {
            for (int i : l) {
                System.out.print(i + " ");
            }
            System.out.println();
        });
    }
}
