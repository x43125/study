package com.wx.algorithm.leetcode.hot100;

import java.util.*;

/**
 * @author Shawn
 * @date 2023/7/12 09:58
 * @description
 */
public class T20ValidBrackets {

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character stackC = stack.pop();
                if (map.get(stackC) != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "]";
        T20ValidBrackets validBrackets = new T20ValidBrackets();
        boolean valid = validBrackets.isValid(s);
        System.out.println("valid = " + valid);
    }
}
