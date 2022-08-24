package com.wx.algorithm.leetcode.normal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxiang
 * @date 2022/8/24 15:00
 * @description
 */
public class T_1460_OneArr2Another {
    public static void main(String[] args) {
        int[] target = {1, 2, 3, 4}, arr = {2, 4, 1, 5};
        T_1460_OneArr2Another oneArr2Another = new T_1460_OneArr2Another();
        boolean b = oneArr2Another.canBeEqual(target, arr);
        System.out.println("b = " + b);
    }

    public boolean canBeEqual(int[] target, int[] arr) {
        if (target.length != arr.length) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int j : target) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }

        for (int j : arr) {
            if (!map.containsKey(j)) {
                return false;
            }
            if (map.get(j) == 1) {
                map.remove(j);
            } else {
                map.put(j, map.get(j) - 1);
            }
        }
        return map.size() == 0;
    }
}
