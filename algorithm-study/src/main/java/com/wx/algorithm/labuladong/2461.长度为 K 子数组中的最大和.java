/*
 * @lc app=leetcode.cn id=2461 lang=java
 * @lcpr version=30403
 *
 * [2461] 长度为 K 子数组中的最大和
 */

import java.util.HashMap;
import java.util.Map;

// @lc code=start
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        // 窗口向右移动
        // map更新不同的数字个数
        long sum = 0, max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer rv = nums[i];
            sum += rv;
            // 有就+1，无就设默认值
            if (map.containsKey(rv)) {
                map.put(rv, map.get(rv) + 1);
            } else {
                map.put(rv, 1);
            }

            if (i < k-1) {
                continue;
            }
            if (map.size() == k) {
                max = Math.max(max, sum);
            }
            Integer lv = nums[i-k+1];
            sum -= lv;
            if (map.containsKey(lv)) {
                int cnt = map.get(lv);
                if (cnt > 1) {
                    map.put(lv, cnt-1);
                } else {
                    map.remove(lv);
                }
            }
        }

        return max;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,5,4,2,9,9,9]\n3\n
// @lcpr case=end

// @lcpr case=start
// [4,4,4]\n3\n
// @lcpr case=end

 */

