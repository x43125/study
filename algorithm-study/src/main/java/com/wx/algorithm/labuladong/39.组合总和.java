/*
 * @lc app=leetcode.cn id=39 lang=java
 * @lcpr version=30403
 *
 * [39] 组合总和
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    List<List<Integer>> ans;
    List<Integer> list;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        list = new ArrayList<>();
        // 按顺序排序
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return ans;
    }

    private void dfs(int[] candidates, int target, int i) {
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        if (target < 0 || i == candidates.length) {
            return;
        }

        list.add(candidates[i]);
        dfs(candidates, target - candidates[i], i);
        list.remove(list.size() - 1);
        dfs(candidates, target, i + 1);
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [2,3,6,7]\n7\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2,3,5]\n8\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2]\n1\n
 * // @lcpr case=end
 * 
 */
