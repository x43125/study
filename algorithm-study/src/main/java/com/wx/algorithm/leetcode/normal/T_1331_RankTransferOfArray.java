package com.wx.algorithm.leetcode.normal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Descrption: 输入：arr = [40,10,20,30]
 * 输出：[4,1,2,3]
 * @Author: x43125
 * @Date: 22/07/28
 */
public class T_1331_RankTransferOfArray {

    public static int[] arrayRankTransform(int[] arr) {
        int[] sortArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortArr);
        int[] res = new int[arr.length];

        Map<Integer, Integer> index = new HashMap<>();

        for (int j : sortArr) {
            if (!index.containsKey(j)) {
                index.put(j, index.size() + 1);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            res[i] = index.get(arr[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {37, 12, 28, 9, 100, 56, 80, 5, 12};
        int[] ints = arrayRankTransform(arr);

        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
}

// [5,3,4,2,8,6,7,1,3]