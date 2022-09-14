import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=1619 lang=java
 *
 * [1619] 删除某些元素后的数组均值
 */

// @lc code=start
class Solution {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        double res = 0;
        int length = arr.length;
        double num = length * 0.05;
        int count = 0;

        for (int i = (int) num; i < length - num; i++) {
            res += arr[i];
            count++;
        }

        return res / count;
    }
}
// @lc code=end
