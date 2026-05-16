/*
 * @lc app=leetcode.cn id=46 lang=java
 * @lcpr version=30403
 *
 * [46] 全排列
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<Integer> list;
    List<List<Integer>> ans;
    int n;

    public List<List<Integer>> permute(int[] nums) {
        list = new ArrayList<>();
        ans = new ArrayList<>();
        n = nums.length;
        boolean[] choosed = new boolean[n];

        dfs(nums, choosed);
        return ans;
    }

    /**
     * 
     * @param nums
     * @param choosed
     */
    private void dfs(int[] nums, boolean[] choosed) {
        if (list.size() == n) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int j = 0; j < n; j++) {
            if (choosed[j]) {
                continue;
            }
            list.add(nums[j]);
            choosed[j] = true;
            dfs(nums, choosed);
            list.remove(list.size() - 1);
            choosed[j] = false;
        }
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,3]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0,1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1]\n
 * // @lcpr case=end
 * 
 */
