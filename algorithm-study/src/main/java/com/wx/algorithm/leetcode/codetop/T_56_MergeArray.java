package com.wx.algorithm.leetcode.codetop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T_56_MergeArray {
    public int[][] merge(int[][] intervals) {
        //  按照数组左坐标排序
        // 新建一个二维数组
        // 先将第一个数组插入到二维数组里
        // 然后依次比较后续数组的左坐标和二维数组的前一个数组的右坐标
        // 如果左坐标<=右坐标，则修改目标数组的右坐标为源数组的左坐标
        // 如果左坐标>右坐标，则直接将此数组复制到目标数组里
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        List<Integer[]> result = new ArrayList<>();
        Integer[] first = {intervals[0][0], intervals[0][1]};
        result.add(first);
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= result.get(result.size()-1)[1]) {
                if (interval[1] > result.get(result.size()-1)[1]) {
                    result.get(result.size()-1)[1] = interval[1];
                }
            } else {
                Integer[] cur = {intervals[i][0], intervals[i][1]};
                result.add(cur);
            }
        }
        int[][] ans = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = new int[]{result.get(i)[0], result.get(i)[1]};
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        T_56_MergeArray mergeArray = new T_56_MergeArray();
        int[][] merge = mergeArray.merge(intervals);
        for (int[] arr : merge) {
            for (int item : arr) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

}
