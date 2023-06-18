package com.wx.algorithm.leetcode.normal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-09-13 10:28:11
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-09-13 10:54:40
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\T670MaximumSwap.java
 * @Description: 
 */
/*
 * @lc app=leetcode.cn id=670 lang=java
 *
 * [670] 最大交换
 */

// @lc code=start
class T670MaximumSwap {
    public int maximumSwap(int num) {
        String numV = String.valueOf(num);
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        char[] cArray = numV.toCharArray();
        int[] res = new int[numV.length()];

        for (int i = 0; i < cArray.length; i++) {
            int cInt = Integer.parseInt(String.valueOf(cArray[i]));
            map.put(cInt, i);
            list.add(cInt);
            res[i] = cInt;
        }

        Collections.sort(list, (k1, k2) -> k2 - k1);
        for (int i = 0; i < cArray.length; i++) {
            int targetV = list.get(i);
            int sourceV = Integer.parseInt(String.valueOf(cArray[i]));
            if (targetV != sourceV) {
                int targetIndex = map.get(targetV);
                
                int temp = res[i];
                res[i] = res[targetIndex];
                res[targetIndex] = temp;

                StringBuilder sb = new StringBuilder();
                for (int j : res) {
                    sb.append(j);
                }
                return Integer.parseInt(sb.toString());
            }
        }

        return num;
    }
}
// @lc code=end
