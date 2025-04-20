package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.List;

public class T_22_GenBrackets {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public static void main(String[] args) {
        T_22_GenBrackets genBrackets = new T_22_GenBrackets();
        int n = 3;
        List<String> parenthesis = genBrackets.generateParenthesis(n);
        parenthesis.forEach(System.out::println);
        System.out.println();
    }
}
