package com.wx.algorithm.leetcode.hot100;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/7/4 13:52
 * @description
 */
public class T22GenerateParenthesis {

    /**
     * 暴力法，暴力枚举每一种情况
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new LinkedList<>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    private void generateAll(char[] current, int index, List<String> combinations) {
        if (index == current.length) {
            // 如果是符合要求的
            if (valid(current)) {
                combinations.add(new String(current));
            }
        } else {
            current[index] = '(';
            generateAll(current, index + 1, combinations);
            current[index] = ')';
            generateAll(current, index + 1, combinations);
        }
    }

    private boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }

        return balance == 0;
    }

    public static void main(String[] args) {
        T22GenerateParenthesis generateParenthesis = new T22GenerateParenthesis();
//        List<String> strings = generateParenthesis.generateParenthesis(3);
        List<String> strings = generateParenthesis.generateParenthesisOptimize(3);
        strings.forEach(System.out::println);
    }

    public List<String> generateParenthesisOptimize(int n) {
        List<String> combinations = new LinkedList<>();

        generateBrackets(combinations, new StringBuilder(), 0, 0, n);

        return combinations;
    }

    private void generateBrackets(List<String> combinations, StringBuilder cur, int left, int right, int n) {
        // 如果cur的长度==n*2说明在符合条件的基础上，达到了指定数量，可以添加
        if (cur.length() == n * 2) {
            combinations.add(cur.toString());
            return;
        }

        // 如果左括号数小于n，即小于所有括号数的一半，则可以添加左括号
        if (left < n) {
            // 添加左括号
            cur.append('(');
            generateBrackets(combinations, cur, left + 1, right, n);
            // 如果不符合，则回溯删除左括号
            cur.deleteCharAt(cur.length() - 1);
        }

        // 如果有括号小于左括号数，则可以添加右括号数
        if (right < left) {
            cur.append(')');
            generateBrackets(combinations, cur, left, right + 1, n);
            // 如果不符合或已完成，则回溯删除右括号
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
