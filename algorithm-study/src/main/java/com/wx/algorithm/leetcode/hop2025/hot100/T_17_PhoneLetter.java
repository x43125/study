package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.List;

public class T_17_PhoneLetter {

    static char[][] letter = { {}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' },
            { 'g', 'h', 'i' }, { 'j', 'k', 'l' }, { 'm', 'n', 'o' },
            { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

    static List<String> result;

    public List<String> letterCombinations(String digits) {
        result = new ArrayList<>();
        if ("".equals(digits)) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        dfs(digits, 0, sb);
        return result;
    }

    private void dfs(String digits, int index, StringBuilder sb) {
        if (index >= digits.length()) {
            result.add(sb.toString());
            return;
        }

        char charAt = digits.charAt(index);
        int i = charAt - '0';
        char[] cs = letter[i];

        for (char c : cs) {
            sb.append(c);
            dfs(digits, index + 1, sb);
            sb.delete(sb.length() - 1, sb.length());
        }
    }

    public static void main(String[] args) {
        String digits = "";
        T_17_PhoneLetter phoneLetter = new T_17_PhoneLetter();
        List<String> letterCombinations = phoneLetter.letterCombinations(digits);
        for (String word : letterCombinations) {
            System.out.println(word);
        }
    }
}
