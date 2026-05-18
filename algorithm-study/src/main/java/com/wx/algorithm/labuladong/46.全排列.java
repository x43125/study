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
<<<<<<< HEAD
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
=======

    List<Integer> nums;
    List<List<Integer>> res;

    public List<List<Integer>> permute(int[] nums) {
        this.res = new ArrayList<>();
        this.nums = new ArrayList<>();
        for (int num : nums) {
            this.nums.add(num);
        }
        dfs(0);
        return res;
    }

    private void swap(int a, int b) {
        int tmp = nums.get(a);
        nums.set(a, nums.get(b));
        nums.set(b, tmp);
    }

    private void dfs(int index) {
        if (index == nums.size() - 1) {
            res.add(new ArrayList<>(nums)); // 添加排列方案
            return;
        }
        for (int i = index; i < nums.size(); i++) {
            // 交换，将 nums[i] 固定在第 x 位
            // 每次将当前数据，移动到本次下标位置
            swap(i, index); 
            // 开启固定第 x + 1 位元素
            dfs(index + 1); 
            // 恢复交换
            swap(i, index); 
>>>>>>> 0b84efd (全排列)
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
