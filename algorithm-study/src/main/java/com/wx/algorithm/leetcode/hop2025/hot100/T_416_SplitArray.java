package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_416_SplitArray {

    /**
     * dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少。
     * 01背包问题：dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])
     * 完全背包问题：
     * 
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        // 数组的长度
        int n = nums.length;
        // 数组的和，数组中最大的数
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }

        // 如果能找到一组数的和为全部数据的和的一半，那么剩下的数的和也为 和的一半
        // 所以退化成能否找到和为target的一组数
        int target = sum / 2;

        // 如果总和是奇数，则不存在
        // 如果最大的数的值 > 和的一半，则不存在，因为即使只是这个值和其他人单独分开，也依然 >target
        if (sum % 2 != 0 || max > target) {
            return false;
        }

        // dp数组，二维数组，n 从0到n个数中任选； target+1 能组成 0到target+1的最大数据
        int[][] dp = new int[n][target + 1];

        // 初始化dp，当仅选择第0个物品的时候，放到0-target的容量的包里的时候，包里最大的价值都是nums[0]
        for (int j = nums[0]; j <= target; j++) {
            dp[0][j] = nums[0];
        }

        // i==0上面已经初始化，所以从1开始
        for (int i = 1; i < n; i++) {
            // j==0的时候表示容量为0，没有意义
            for (int j = 1; j <= target; j++) {
                // 如果包的容量 小于 当前物品的重量，则dp[i][j] = dp[i-1][j];
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 前一个最大值 与 包减去当前物品重量后的容量的最大值 相比的更大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                }
            }
        }

        // 从0到n任取物品，放到一个容量为target的包里，的价值刚好是target
        return dp[n - 1][target] == target;
    }

    public boolean canPartition_02(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE, sum = 0;
        for (int num : nums) {
            max = Math.max(max, sum);
            sum += num;
        }
        int target = sum / 2;
        if (sum % 2 != 0 || max > target) {
            return false;
        }

        // 从0到i中任意选取，能组成0吗？
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        dp[0][nums[0]] = true;

        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j < target + 1; j++) {
                if (j < num) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                }
            }
        }

        return dp[n - 1][target];
    }

    public boolean canPartition_03(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE, sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        int target = sum / 2;
        if (sum % 2 != 0 || max > target) {
            return false;
        }

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = { 1, 5, 11, 5, 6 };
        T_416_SplitArray splitArray = new T_416_SplitArray();
        boolean canPartition = splitArray.canPartition_03(nums);
        System.out.println(canPartition);
    }
}
