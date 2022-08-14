package com.wx.algorithm.leetcode.hot100;

/**
 * @Descrption: Given a string s, find the length of the longest substring without repeating characters.
 * @Author: x43125
 * @Date: 22/08/14
 */
public class Hot_03_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "pwwskew";
        Hot_03_LongestSubstringWithoutRepeatingCharacters repeatingChar = new Hot_03_LongestSubstringWithoutRepeatingCharacters();
        int i = repeatingChar.lengthOfLongestSubstring(s);
        System.out.println("i = " + i);
    }

    /**
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        StringBuilder tmpStr = new StringBuilder();
        int max = Integer.MIN_VALUE;
        for (char aChar : chars) {
            int index = tmpStr.toString().indexOf(aChar);
            if (index != -1) {
                max = Math.max(max, tmpStr.length());
                tmpStr = new StringBuilder(tmpStr.substring(index + 1));
            }
            tmpStr.append(aChar);
        }

        max = Math.max(max, tmpStr.length());
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
