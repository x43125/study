package com.wx.algorithm.leetcode.hot100;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @author Shawn
 * @date 2024/3/21 23:02
 * @description
 */
public class T394DecodeString {
    public static void main(String[] args) {
        T394DecodeString decodeString = new T394DecodeString();
        String s = decodeString.decodeString("abc2[ef]xyz");
        System.out.println("abcefefxyz".equals(s));
        System.out.println("s = " + s);
    }

    String src;
    int ptr;

    public String decodeString(String s) {
        src = s;
        ptr = 0;
        return getString();
    }

    public String getString() {
        if (ptr == src.length() || src.charAt(ptr) == ']') {
            return "";
        }

        char cur = src.charAt(ptr);
        int repTime;
        StringBuilder ret = new StringBuilder();

        if (Character.isDigit(cur)) {
            repTime = getDigits();
            // 过滤左括号
            ++ptr;
            // 解析 String
            String str = getString();
            // 过滤右括号
            ++ptr;
            // 构造字符串
            while (repTime-- > 0) {
                ret.append(str);
            }
        } else if (Character.isLetter(cur)) {
            // String -> Char String
            // 解析 Char
            ret = new StringBuilder(String.valueOf(src.charAt(ptr++)));
        }

        return ret + getString();
    }

    public int getDigits() {
        int ret = 0;
        while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
            ret = ret * 10 + src.charAt(ptr++) - '0';
        }
        return ret;
    }

    int count;

    public String decodeString02(String s) {
        LinkedList<String> stk = new LinkedList<>();
        count = 0;

        while (count < s.length()) {
            char cur = s.charAt(count);
            if (Character.isDigit(cur)) {
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                stk.addLast(String.valueOf(s.charAt(count++)));
            } else {
                ++count;
                LinkedList<String> sub = new LinkedList<>();
                while(!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                stk.removeLast();
                int times = Integer.parseInt(stk.removeLast());
                StringBuilder sb = new StringBuilder();
                String o = getString2(sub);
                while (times-- > 0) {
                    sb.append(o);
                }
                stk.addLast(sb.toString());
            }
        }

        return getString2(stk);
    }

    public String getString2(LinkedList<String> stk) {
        StringBuilder sb = new StringBuilder();
        for (String s:stk) {
            sb.append(s);
        }

        return sb.toString();
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }
}
