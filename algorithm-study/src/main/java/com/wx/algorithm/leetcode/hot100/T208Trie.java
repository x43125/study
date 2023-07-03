package com.wx.algorithm.leetcode.hot100;

/**
 * @author wangxiang
 * @date 2023/7/3 16:15
 * @description
 */
public class T208Trie {
    static class Trie {

        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;

        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        /**
         * 神奇,一种递归的思想
         *
         * @param word
         * @return
         */
        private Trie searchPrefix(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }

        public static void main(String[] args) {

        }
    }
}
