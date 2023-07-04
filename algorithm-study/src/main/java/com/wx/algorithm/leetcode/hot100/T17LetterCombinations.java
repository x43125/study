package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/7/4 10:53
 * @description
 */
public class T17LetterCombinations {

    String[] num2String;
    List<String> ans;

    public List<String> letterCombinations(String digits) {
        // 数字到字符的映射
        num2String = new String[10];
        num2String[2] = "abc";
        num2String[3] = "def";
        num2String[4] = "ghi";
        num2String[5] = "jkl";
        num2String[6] = "mno";
        num2String[7] = "pqrs";
        num2String[8] = "tuv";
        num2String[9] = "wxyz";
        ans = new LinkedList<>();
        StringBuilder curr = new StringBuilder();
        traverse(digits, curr, 0);
        return ans;
    }

    private void traverse(String digits, StringBuilder curr, int start) {
        if (start == digits.length()) {
            if (start != 0) {
                ans.add(curr.toString());
            }
            return;
        }
        char numC = digits.toCharArray()[start];
        int index = numC - '0';
        // 当前数字对应的字符串
        String s = num2String[index];
        // 从当前字符串中取一个，添加进curr里
        for (char c1 : s.toCharArray()) {
            curr.append(c1);
            traverse(digits, curr, start + 1);
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public static void main(String[] args) {
        String digits = "239";
//        String digits = "";
        T17LetterCombinations letterCombinations = new T17LetterCombinations();
        List<String> strings = letterCombinations.letterCombinations(digits);
        strings.forEach(System.out::println);
    }
}
