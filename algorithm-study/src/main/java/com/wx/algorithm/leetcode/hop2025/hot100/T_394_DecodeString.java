package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.Stack;

public class T_394_DecodeString {
    public String decodeString(String s) {
        Stack<Integer> noStack = new Stack<>();
        Stack<String> wordStack = new Stack<>();
        wordStack.add("");

        StringBuilder noSb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                // 数字
                noSb.append(c);
            } else if (c == '[') {
                // 数字结束，压栈
                noStack.add(Integer.parseInt(noSb.toString()));
                // 重新初始化
                noSb = new StringBuilder();
                // 压栈，准备记录[]里的字符
                wordStack.add("");
            } else if (c >= 'a' && c <= 'z') {
                // 使用栈顶记录当前字符串，
                wordStack.add(wordStack.pop() + c);
            } else if (c == ']') {
                // 一段结束，该计算了
                // 初始为1
                int n = 1;
                // 如果数字栈中有值的话，则退栈
                if (!noStack.isEmpty()) {
                    n = noStack.pop();
                }

                // 拼接全字符串
                String fullWord = buildStr(wordStack.pop(), n);
                // 然后再将全部字符串，拼到现在的栈顶
                wordStack.add(wordStack.pop() + fullWord);
            }
        }

        return wordStack.pop();
    }

    private String buildStr(String word, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(word);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "abc3[cd]xyz", target = "abccdcdcdxyz";
        T_394_DecodeString decodeString = new T_394_DecodeString();
        String word = decodeString.decodeString(s);
        System.out.println(target.equals(word));
        System.out.println(word);
    }
}
