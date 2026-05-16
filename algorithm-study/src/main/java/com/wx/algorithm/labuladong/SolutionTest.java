package com.wx.algorithm.labuladong;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SolutionTest {
    //  回溯：正确但超时，尝试 DP
    int n;
    int target;
    public int combinationSum4(int[] nums, int target) {
        // 更像回溯
        // 当前节点标记是否已被使用，避免重复
        // 从 0 开始，表示可以重复使用
        n = 0;
        this.target = target;
        Arrays.sort(nums);
        dfs(nums, 0);
        return n;
    }
    
    private void dfs(int[] nums, int preSum) {
        if (preSum == target) {
            n++;
            return;
        }
        if (preSum > target) {
            return;
        }
        
        Set<Integer> choosed = new HashSet<>();
        for (int j = 0; j < nums.length; j++) {
            if (choosed.contains(nums[j])) {
                continue;
            }
            choosed.add(nums[j]);
            preSum += nums[j];
            dfs(nums, preSum);
            preSum -= nums[j];
        }
    }

    public static void main(String[] args) {
        SolutionTest solution = new SolutionTest();
        long time = System.currentTimeMillis();
        int ans = solution.combinationSum4(new int[] { 4,2,1}, 32);
        System.out.println(ans);
        System.out.println(System.currentTimeMillis() - time);
    }
}
