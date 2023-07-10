package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 分割成回文串
 *
 * @author wangxiang
 * @date 2023/7/5 10:02
 * @description
 */
public class T131Partition {
//    boolean[][] flag;
//    List<List<String>> ret = new ArrayList<>();
//    List<String> ans = new ArrayList<>();
//    int n;
//
//    public List<List<String>> partition(String s) {
//        n = s.length();
//        flag = new boolean[n][n];
//        for (int i = 0; i < n; ++i) {
//            Arrays.fill(flag[i], true);
//        }
//
//        for (int i = n - 1; i >= 0; --i) {
//            for (int j = i + 1; j < n; ++j) {
//                // 动态规划，判断是否是回文串：即当前最外侧两值是否相等 && 内侧值是否是回文串
//                // flag[i][j] 表示，i~j 的字符串是否是回文串；所以i>j的位置没用，只用右上角的内容
//                // 比如 str="aab"; flag[0][2]: 表示 aab 是否是回文串
//                // flag[1][2]: 表示 ab 是否是回文串
//                flag[i][j] = (s.charAt(i) == s.charAt(j)) && flag[i + 1][j - 1];
//            }
//        }
//
//        dfs(s, 0);
//        return ret;
//    }
//
//    public void dfs(String s, int i) {
//        if (i == n) {
//            ret.add(new ArrayList<>(ans));
//            return;
//        }
//
//        for (int j = i; j < n; ++j) {
//            if (flag[i][j]) {
//                ans.add(s.substring(i, j + 1));
//                dfs(s, j + 1);
//                ans.remove(ans.size() - 1);
//            }
//        }
//    }

    List<List<String>> ans;
    List<String> nowAns;
    boolean[][] palindromic;
    int n;

    public List<List<String>> partition(String s) {
        ans = new ArrayList<>();
        nowAns = new ArrayList<>();
        n = s.length();
        // 先把所有回文串通过动态规划搞出来
        palindromic = new boolean[n][n];
        // 先全部赋true
        for (int i=0; i<n; i++) {
            Arrays.fill(palindromic[i], true);
        }

        for (int i=n-1; i>=0; i--) {
            for (int j=i+1; j<n; j++) {
                palindromic[i][j] = (s.charAt(i) == s.charAt(j)) && palindromic[i+1][j-1];
            }
        }

        dfs(s, 0);
        return ans;
    }

    private void dfs(String s, int i) {
        if (i == n) {
            ans.add(new ArrayList<>(nowAns));
            return;
        }

        for (int j=i; j<n; j++) {
            // 判断从i到末尾的每一个字符串是否是回文串，如果是则加入
            if (palindromic[i][j]) {
                nowAns.add(s.substring(i, j+1));
                dfs(s, j+1);
                nowAns.remove(nowAns.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        String s = "aabaa";
        T131Partition partition = new T131Partition();
        List<List<String>> lists = partition.partition(s);
        lists.forEach(list -> {
            for (String str : list) {
                System.out.print(str + " ");
            }
            System.out.println();
        });
    }
}
