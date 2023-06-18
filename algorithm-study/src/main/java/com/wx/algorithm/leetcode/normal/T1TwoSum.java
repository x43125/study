/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-09-02 14:47:21
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-09-02 14:49:52
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\T1TwoSum.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.wx.algorithm.leetcode.normal;

/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 */

// @lc code=start
class T1TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }

        return res;
    }
}
// @lc code=end
