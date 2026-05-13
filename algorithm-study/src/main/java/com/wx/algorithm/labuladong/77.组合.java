/*
 * @lc app=leetcode.cn id=77 lang=java
 * @lcpr version=30403
 *
 * [77] 组合
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<Integer>> ans;
    List<Integer> list ;
    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        list = new ArrayList<>();
        dfs(n, k, 1);
        return ans;
    }

    private void dfs(int n, int k, int i) {
        // 必须要有，没有不行
        if (list.size() + (n - i + 1) < k) {
            return;            
        }

        if (list.size() == k) {
            ans.add(new ArrayList<>(list));
            return;
        }

        list.add(i);
        dfs(n, k, i + 1);
        list.remove(list.size() - 1);
        dfs(n, k, i + 1);
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // 4\n2\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // 1\n1\n
 * // @lcpr case=end
 * 
 */
