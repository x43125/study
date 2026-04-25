/*
 * @lc app=leetcode.cn id=2187 lang=java
 * @lcpr version=30403
 *
 * [2187] 完成旅途的最少时间
 */

// @lc code=start
class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        // 并发
        // 暴力法：模拟，超时
        // 二分优化
        long dur = time[0];
        long left = 0;
        while (true) {
            long[] trip = curTrips(time, dur);
            if (trip[0] > totalTrips) {
                long[] preTrip = curTrips(time, dur - 1);
                if (preTrip[0] < totalTrips) {
                    return dur;
                } else if (preTrip[0] > totalTrips) {
                    dur = (dur - left) / 2 + left;
                } else {
                    return dur - 1 - preTrip[1];
                }
            } else if (trip[0] < totalTrips) {
                long[] nextTrip = curTrips(time, dur + 1);
                if (nextTrip[0] < totalTrips) {
                    left = dur;
                    dur = dur * 2;
                } else {
                    return dur + 1;
                }
            } else {
                return dur - trip[1];
            }
        }
    }

    private long[] curTrips(int[] time, long curTime) {
        long trip = 0l, minModNum = Integer.MAX_VALUE;
        for (int i : time) {
            // 每一趟车在 dur时间里能跑多少趟和
            trip += curTime / i;
            minModNum = Math.min(minModNum, curTime % i);
        }
        return new long[]{trip, minModNum};
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,3]\n5\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2]\n1\n
 * // @lcpr case=end
 * 
 */
