package com.wx.algorithm.leetcode.codetop;

import java.util.Stack;

public class T_394_Decode {
    public String decodeString(String s) {
        Stack<String> cStack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ']') {
                // 该退栈了
                StringBuilder sb = new StringBuilder();
                boolean findNumber = false;
                StringBuilder numSb = new StringBuilder();
                while (!cStack.isEmpty()) {
                    String popc = cStack.pop();
                    if (findNumber) {
                        String peek = cStack.peek();
                        if (peek.length() == 1) {
                            char peekC = peek.toCharArray()[0];
                            if (peekC >= '0' && peekC <= '9') {
                                numSb.append(cStack.pop());
                            }
                        } else {
                            // 已经找到了所有数字
                            int num = Integer.parseInt(numSb.toString());
                            String str = sb.toString();
                            cStack.add(this.buildStr(str, num));
                            break;
                        }
                    } else {
                        if ("[".equals(popc)) {
                            findNumber = true;
                            continue;
                        } else {
                            sb.append(popc);
                        }
                    }
                }
            } else {
                // 不管是啥，都往里塞，直到遇到']'
                cStack.add(c + "");
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!cStack.isEmpty()) {
            sb.append(cStack.remove(0));
        }
        return sb.toString();
    }

    private String buildStr(String str, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<num; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "abc2[e]";
        T_394_Decode decode = new T_394_Decode();
        String decodeString = decode.decodeString(s);
        System.out.println(decodeString);

    }
}
