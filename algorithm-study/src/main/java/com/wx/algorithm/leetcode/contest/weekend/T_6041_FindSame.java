package com.wx.algorithm.leetcode.contest.weekend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class T_6041_FindSame {

    public static List<Integer> intersection(int[][] nums) {
        Set<Integer> set = new HashSet<>();

        int row = nums.length;
        int col = nums[0].length;

        for (int i = 0; i < col; i++) {
            set.add(nums[0][i]);
        }

        Set<Integer> temp;
        for (int i = 1; i < row; i++) {
            temp = new HashSet<>();
            for (int j = 0; j < nums[i].length; j++) {
                if (set.contains(nums[i][j])) {
                    temp.add(nums[i][j]);
                }
            }
            set = temp;
        }
        ArrayList<Integer> res = new ArrayList<Integer>(set);
        Collections.sort(res);
        return res;
    }

    public static void main(String[] args) {
        
        int[][] nums = {{3,1,2,4,5},{1,2,3,4},{3,4,5,6}};

        List<Integer> res = intersection(nums);

        res.forEach(System.out::println);
    }
}