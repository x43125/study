package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T_131_Palindrome {
    boolean[][] palindrome;
    List<List<String>> result;

    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        int n = s.length();
        palindrome = new boolean[n][n];

        // 初始设置所有的点都是回文串
        for (int i = 0; i < palindrome.length; i++) {
            Arrays.fill(palindrome[i], true);
        }
        // 预处理各处的回文串
        // 从末尾开始处理，方便控制j的下标
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 当前i到j是否是回文串，取决于i+1到j-1是否是回文串 及 i,j的字符串是否相同
                palindrome[i][j] = (s.charAt(i) == s.charAt(j)) && palindrome[i + 1][j - 1];
            }
        }

        dfs(s, 0, new ArrayList<String>());

        return result;
    }

    private void dfs(String s, int i, List<String> list) {
        if (i == s.length()) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int j = i; j < palindrome.length; j++) {
            if (palindrome[i][j]) {
                list.add(s.substring(i, j + 1));
                dfs(s, j + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String s = "aabb";
        T_131_Palindrome palindrome = new T_131_Palindrome();
        List<List<String>> partition = palindrome.partition(s);
        partition.forEach(list -> {
            list.forEach(i -> System.out.print(i + " "));
            System.out.println();
        });
    }
}
