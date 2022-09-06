/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-09-06 10:23:33
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-09-06 11:23:01
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\828.统计子串中的唯一字符.java
 * @Description: 
 */
package com.wx.algorithm.leetcode.normal;

import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=828 lang=java
 *
 * [828] 统计子串中的唯一字符
 */

// @lc code=start
class Solution {

    /**
     * 暴力法：超时
     * 
     * @param s
     * @return
     */
    public int uniqueLetterString(String s) {
        int res = s.length();
        // 将所有子字符串的值缓存起来，节约遍历过程
        countMap = new HashMap<>();
        int length = s.length();
        // 遍历 生成所有子字符串
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                String subs = s.substring(i, j+1);
                // 如果不存在则先去计算出他的个数，然后缓存起来
                if (!countMap.containsKey(subs)) {
                    countMap.put(subs, uniqueNumber(subs));
                }
                // 如果已经存在了，则直接 累加
                res += countMap.get(subs);
            }
        }
        return res;
    }

    Map<String, Long> countMap;

    private long uniqueNumber(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            map.put(sc, map.getOrDefault(sc, 0) + 1);
        }

        return map.values().stream().filter(v -> v <= 1).count();
    }
}
// @lc code=end
