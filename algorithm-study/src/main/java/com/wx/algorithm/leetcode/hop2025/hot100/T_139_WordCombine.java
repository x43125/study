package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.Arrays;
import java.util.List;

public class T_139_WordCombine {

    /**
     * 回溯思路没问题，超时了
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
                sb.delete(sb.length()-word.length(), sb.length());
                continue;
            }

            boolean canCombine = dfs(s, wordDict, sb);
            if (canCombine) {
                return true;
            }
            sb.delete(sb.length()-word.length(), sb.length());
        }

        return false;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        String[] wordDict = {"c", "leet", "code" };
        T_139_WordCombine wordCombine = new T_139_WordCombine();
        boolean wordBreak = wordCombine.wordBreak(s, Arrays.asList(wordDict));
        System.out.println(wordBreak);
    }
}
