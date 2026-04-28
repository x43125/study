
import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=1456 lang=java
 * @lcpr version=30403
 *
 * [1456] 定长子串中元音的最大数目
 */

// @lc code=start
class Solution {
    public int maxVowels(String s, int k) {
        int r = 0, n = s.length(), max = 0, cnt = max;
        Set<Character> metaWords = new HashSet<>();
        metaWords.add('a');
        metaWords.add('e');
        metaWords.add('i');
        metaWords.add('o');
        metaWords.add('u');
        while (r < n) {
            if (r - k >= 0 && metaWords.contains(s.charAt(r - k))) {
                cnt--;
            }
            if (metaWords.contains(s.charAt(r))) {
                cnt++;
            }
            max = Math.max(max, cnt);
            if (max == k) {
                return k;
            }
            r++;
        }
        return max;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // "abciiidef"\n3\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "aeiou"\n2\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "leetcode"\n3\n
 * // @lcpr case=end
 * 
 */
