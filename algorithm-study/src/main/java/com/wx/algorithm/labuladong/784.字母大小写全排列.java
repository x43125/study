/*
 * @lc app=leetcode.cn id=784 lang=java
 * @lcpr version=30403
 *
 * [784] 字母大小写全排列
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<String> ans;
    StringBuilder sb;

    public List<String> letterCasePermutation(String s) {
        ans = new ArrayList<>();
        sb = new StringBuilder();
        dfs(s, 0);
        return ans;
    }

    private void dfs(String s, int i) {
        if (i == s.length()) {
            ans.add(sb.toString());
            return;
        }

        char c = s.charAt(i);
        // 选中当前
        sb.append(c);
        dfs(s, i + 1);
        if (c < '0' || c > '9') {
            // 如果是字母
            sb.deleteCharAt(sb.length() - 1);
            // 再选中当前的大写或小写
            if (c >= 'a' && c <= 'z') {
                sb.append((char)(c - 'a' + 'A'));
            } else {
                sb.append((char)(c - 'A' + 'a'));
            }
            dfs(s, i + 1);
        }
        sb.deleteCharAt(sb.length() - 1);
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // "a1b2"\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "3z4"\n
 * // @lcpr case=end
 * 
 */
