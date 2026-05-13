/*
 * @lc app=leetcode.cn id=17 lang=java
 * @lcpr version=30403
 *
 * [17] 电话号码的字母组合
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    char[][] arr = new char[][] { { 'a', 'b', 'c' }, { 'd', 'e', 'f' },
            { 'g', 'h', 'i' }, { 'j', 'k', 'l' }, { 'm', 'n', 'o' },
            { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };
    List<String> ans;

    public List<String> letterCombinations(String digits) {
        ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(digits, sb, 0);
        return ans;
    }

    private void dfs(String digits, StringBuilder sb, int i) {
        if (i >= digits.length()) {
            ans.add(sb.toString());
            return;
        }

        int index = digits.charAt(i)-'2';
        for (int j = 0; j < arr[index].length; j++) {
            sb.append(arr[index][j]);
            dfs(digits, sb, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // "23"\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "2"\n
 * // @lcpr case=end
 * 
 */
