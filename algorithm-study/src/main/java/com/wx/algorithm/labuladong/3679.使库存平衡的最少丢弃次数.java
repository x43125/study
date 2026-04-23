/*
 * @lc app=leetcode.cn id=3679 lang=java
 * @lcpr version=30403
 *
 * [3679] 使库存平衡的最少丢弃次数
 */

// @lc code=start

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        // 需要物品计数 map
        // 需要标记哪天被丢弃了 set存被标记为丢弃的下标
        Map<Integer, Integer> sameTypeCntMap = new HashMap<>();
        Set<Integer> discardIndexSet = new HashSet<>();
        int discardCnt = 0;
        for (int i = 0; i < arrivals.length; i++) {
            int val = arrivals[i];
            if (sameTypeCntMap.containsKey(val)) {
                int cnt = sameTypeCntMap.get(val);
                if (cnt == m) {
                    discardIndexSet.add(i);
                    discardCnt++;
                } else {
                    sameTypeCntMap.put(val, cnt + 1);
                }
            } else {
                sameTypeCntMap.put(val, 1);
            }

            if (i < w - 1) {
                continue;
            }

            if (discardIndexSet.contains(i - w + 1)) {
                discardIndexSet.remove(i - w + 1);
                continue;
            }

            int lVal = arrivals[i - w + 1];
            int lValCnt = sameTypeCntMap.get(lVal);
            if (lValCnt == 1) {
                sameTypeCntMap.remove(lVal);
            } else {
                sameTypeCntMap.put(lVal, lValCnt - 1);
            }
        }

        return discardCnt;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,1,3,1]\n4\n2\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,2,3,3,3,4]\n3\n2\n
 * // @lcpr case=end
 * 
 */
