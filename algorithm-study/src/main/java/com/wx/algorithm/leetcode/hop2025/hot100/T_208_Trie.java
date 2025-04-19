package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_208_Trie {
    private int val;
    private T_208_Trie[] next;

    public T_208_Trie() {
        next = new T_208_Trie[26];
    }

    public void insert(String word) {
        T_208_Trie node = this;
        
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            int index = c - 'a';
            if (node.next[index] == null) {
                node.next[index] = new T_208_Trie();
            }
            node = node.next[index];
        }
        node.val = 1;
    }

    public boolean search(String word) {
        T_208_Trie searchPre = searchPre(word);
        return searchPre != null && searchPre.val == 1;
    }

    public boolean startsWith(String prefix) {
        return searchPre(prefix) != null;
    }

    public T_208_Trie searchPre(String prefix) {
        T_208_Trie node = this;
        // 从this开始，向下查找
        char[] charArray = prefix.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            int index = c - 'a';
            if (node.next[index] == null) {
                return null;
            }
            node = node.next[index];
        }

        return node;
    }

    public static void main(String[] args) {
        T_208_Trie trie = new T_208_Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("beer");
        trie.insert("add");
        trie.insert("jam");
        trie.insert("rental");
        System.out.println(trie.search("apps")); // 返回 True
        System.out.println(trie.search("app")); // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app")); // 返回 True
    }
}
