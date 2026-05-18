/*
 * @lc app=leetcode.cn id=47 lang=java
 * @lcpr version=30403
 *
 * [47] 全排列 II
 */

// @lc code=start
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    List<List<Integer>> ans;
    List<Integer> list;
    public List<List<Integer>> permuteUnique(int[] nums) {
        ans = new ArrayList<>();
        list = new ArrayList<>();

        // 下一轮选点的时候，判断当前点是否已被之前的节点用过了
        boolean[] used = new boolean[nums.length];
        // 当前轮选点的时候，重复的不再选了
        
        dfs(nums, 0, used);
        return ans;
    }

    private void dfs(int[] nums, int count, boolean[] used) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        if (count == nums.length) {
            return;
        }

        // 只是记录当前节点的已用值的，不用递归到下一节点
        Set<Integer> choosed = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (choosed.contains(nums[i]) || used[i]) {
                continue;
            }

            // 添加即可，不用在回溯的时候删除
            choosed.add(nums[i]);
            list.add(nums[i]);
            used[i] = true;
            dfs(nums, count + 1, used);
            list.remove(list.size()-1);
            used[i] = false;
        }
=======
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        
>>>>>>> 0b84efd (全排列)
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,1,2]\n
// @lcpr case=end

// @lcpr case=start
// [1,2,3]\n
// @lcpr case=end

 */

