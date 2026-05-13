/*
 * @lc app=leetcode.cn id=131 lang=java
 * @lcpr version=30403
 *
 * [131] 分割回文串
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    int[][] f;
    List<List<String>> ans;
    List<String> list;
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new int[n][n];
        ans = new ArrayList<>();
        list = new ArrayList<>();
        dfs(s, 0);
        return ans;
    }

    private void dfs(String s, int i) {
        if (i == n) {
            ans.add(new ArrayList<>(list));
            return;
        }

        // 如果是需要范围的，就可以for循环
        // 如果只是判断当前节点是否需要选，或是否需要变化，则不需要for，把for循环放到递归里
        for (int j = i; j < n; j++) {
            if (isPalindrome(s, i, j) == 1) {
                // 从i-j是回文，剩下的列表里组回文串
                list.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                // 当前j的后续可能的回文都已经搞完了，现在再将当前回文串删除掉
                // 把j挪到下一个位置再判断
                list.remove(list.size() - 1);
            }
        }
    }

    private int isPalindrome(String s, int i, int j) {
        if (f[i][j] != 0) {
            return f[i][j];
        }
        if (i >= j) {
            // 递归终点
            f[i][j] = 1;
        } else if (s.charAt(i) == s.charAt(j)) {
            // 判断是否是回文
            f[i][j] = isPalindrome(s, i+1, j-1);
        } else {
            // 不是回文
            f[i][j] = -1;
        }

        return f[i][j];
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // "aab"\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // "a"\n
 * // @lcpr case=end
 * 
 */
