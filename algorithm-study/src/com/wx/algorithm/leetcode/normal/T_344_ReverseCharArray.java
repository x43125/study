package com.wx.algorithm.leetcode.normal;

public class T_344_ReverseCharArray {
    public static void reverseString(char[] s) {
        int left = 0;
        int right = s.length-1;

        while (left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }

    public static void main(String[] args) {
        String str = "helloworld";
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            System.out.print(c);
        }
        System.out.println();
        reverseString(charArray);
        for (char c : charArray) {
            System.out.print(c);
        }
    }
    
}
