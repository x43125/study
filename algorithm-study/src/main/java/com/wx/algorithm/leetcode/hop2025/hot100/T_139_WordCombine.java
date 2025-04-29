package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class T_139_WordCombine {

    /**
     * 回溯思路没问题，超时了
     * 
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 从wordDict中选词出来拼装，如果能拼成s则返回true
        // 回溯
        return dfs(s, wordDict, new StringBuilder());
    }

    private boolean dfs(String s, List<String> wordDict, StringBuilder sb) {
        if (s.equals(sb.toString())) {
            return true;
        }

        for (int j = 0; j < wordDict.size(); j++) {
            String word = wordDict.get(j);
            sb.append(word);
            if (!s.startsWith(sb.toString())) {
                sb.delete(sb.length() - word.length(), sb.length());
                continue;
            }

            boolean canCombine = dfs(s, wordDict, sb);
            if (canCombine) {
                return true;
            }
            sb.delete(sb.length() - word.length(), sb.length());
        }

        return false;
    }

    /**
     * 动态规划
     * 
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak02(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // j如果是可以组成的，wordDict又包含从j到i的字符串，则说明dp[i]是可以组成的
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        String[] wordDict = { "c", "leet", "code" };
        T_139_WordCombine wordCombine = new T_139_WordCombine();
        boolean wordBreak = wordCombine.wordBreak(s, Arrays.asList(wordDict));
        System.out.println(wordBreak);
    }
}
