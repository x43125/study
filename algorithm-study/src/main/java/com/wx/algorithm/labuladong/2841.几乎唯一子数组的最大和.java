/*
 * @lc app=leetcode.cn id=2841 lang=java
 * @lcpr version=30403
 *
 * [2841] 几乎唯一子数组的最大和
 */

// @lc code=start

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public long maxSum(List<Integer> nums, int m, int k) {
        // 窗口向右移动
        // map更新不同的数字个数
        long sum = 0, max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            Integer rv = nums.get(i);
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
            if (map.size() >= m) {
                max = Math.max(max, sum);
            }
            Integer lv = nums.get(i-k+1);
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
// [2,6,7,3,1,7]\n3\n4\n
// @lcpr case=end

// @lcpr case=start
// [5,9,9,2,4,5,4]\n1\n3\n
// @lcpr case=end

// @lcpr case=start
// [1,2,1,2,1,2,1]\n3\n3\n
// @lcpr case=end

 */

