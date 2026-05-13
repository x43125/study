/*
 * @lc app=leetcode.cn id=78 lang=java
 * @lcpr version=30403
 *
 * [78] 子集
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    List<List<Integer>> ans;

    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(nums, 0, list);
        return ans;
    }

    private void dfs(int[] nums, int i, List<Integer> list) {
        if (i >= nums.length) {
            List<Integer> temp = new ArrayList<>(list);
            ans.add(temp);
            return;
        }

        list.add(nums[i]);
        dfs(nums, i + 1, list);
        list.remove(list.size() - 1);
        dfs(nums, i + 1, list);

    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,3]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0]\n
 * // @lcpr case=end
 * 
 */
