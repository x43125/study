/*
 * @lc app=leetcode.cn id=78 lang=java
 * @lcpr version=30403
 *
 * [78] 子集
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<Integer>> ans;
    List<Integer> t;

    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        t = new ArrayList<>();
        dfs(0, nums);
        return ans;
    }

    private void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
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
