package com.wx.algorithm.leetcode.normal;

/**
 * @author wangxiang
 * @date 2022/8/23 10:37
 * @description
 */
public class T_1455_PrefixWord {
    public static void main(String[] args) {
        String sentence = "i am tired", searchWord = "you";
        T_1455_PrefixWord prefixWord = new T_1455_PrefixWord();
        int prefixOfWord = prefixWord.isPrefixOfWord(sentence, searchWord);
        System.out.println("prefixOfWord = " + prefixOfWord);
    }

    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) {
                return i+1;
            }
        }

        return -1;
    }
}
