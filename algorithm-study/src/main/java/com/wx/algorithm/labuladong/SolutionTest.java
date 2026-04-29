package com.wx.algorithm.labuladong;

public class SolutionTest {
    public int solution(int[] nums) {
        // 转化成判断链表有环，很神奇
        // 1,3,4,2,5,2
        // 先用快慢指针，找到快慢指针下标相同的点
        // 然后再将慢指针挪到开头，快指针从当前往后走，各移动一位，
        // 期间比较两个指针是否有值相同，而下标不同的情况
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        SolutionTest solution = new SolutionTest();
        int ans = solution
                .solution(new int[] { 2,1,2 });
        System.out.println(ans);
    }
}
