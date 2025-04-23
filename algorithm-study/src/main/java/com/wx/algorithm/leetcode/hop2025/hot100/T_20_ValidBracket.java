package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class T_20_ValidBracket {
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.add(c);
            } else {
                Character pop = stack.pop();
                if (map.get(pop) != c) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "([{(})])";
        T_20_ValidBracket validBracket = new T_20_ValidBracket();
        boolean valid = validBracket.isValid(s);
        System.out.println(valid);
    }
}
