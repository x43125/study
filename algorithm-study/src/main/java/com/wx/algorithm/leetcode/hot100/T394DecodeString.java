package com.wx.algorithm.leetcode.hot100;

/**
 * @author Shawn
 * @date 2024/3/21 23:02
 * @description
 */
public class T394DecodeString {
    public static void main(String[] args) {
        T394DecodeString decodeString = new T394DecodeString();
        String s = decodeString.decodeString("abc3[cd]xyz");
        System.out.println("abccdcdcdxyz".equals(s));
        System.out.println("s = " + s);
    }

    public String decodeString(String s) {
        // 1.判断数字
        // 2.判断括号
        // 3.判断字符串
        // 4.组合字符串
        // 5.递归

        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            if (s.charAt(i) == ']') {
                return sb.toString();
            } else if (s.charAt(i) < '9' && s.charAt(i) > '0') {
                StringBuilder tmp = new StringBuilder();
                while (s.charAt(i) != '[') {
                    tmp.append(s.charAt(i));
                    i++;
                }
                i++;
                int number = Integer.parseInt(tmp.toString());
                sb.append(buildString(number, decodeString(s.substring(i))));
                i++;
            } else {
                sb.append(s.charAt(i));
                i++;
            }
        }

        return sb.toString();
    }

    private String buildString(int number, String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number; i++) {
            sb.append(string);
        }
        return sb.toString();
    }
}
