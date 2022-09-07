package com.wx.algorithm.leetcode.normal;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/09/07
 */
public class T_1592_RearrangeSpacesBetweenWords {
    public static void main(String[] args) {
        T_1592_RearrangeSpacesBetweenWords rearrangeSpacesBetweenWords = new T_1592_RearrangeSpacesBetweenWords();
        String text = " practice   makes   perfect";
        String res = rearrangeSpacesBetweenWords.reorderSpaces(text);
        System.out.println("res = " + res + "###");
    }

    public String reorderSpaces(String text) {
        int spaceNo = 0;
        List<String> words = new ArrayList<>();
        boolean start = false;
        StringBuilder temp = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (' ' == c) {
                spaceNo++;
                if (start) {
                    words.add(temp.toString());
                    start = false;
                }
            } else {
                if (!start) {
                    temp = new StringBuilder();
                    start = true;
                }
                temp.append(c);
            }
        }
        if (start) {
            words.add(temp.toString());
        }

        int numNo = words.size();
        int wide;
        int space;
        if (numNo == 1) {
            wide = 0;
            space = spaceNo;
        } else {
            wide = spaceNo / (numNo - 1);
            space = spaceNo % (numNo - 1);
        }

        StringBuilder wideStr = new StringBuilder();
        for (int i = 0; i < wide; i++) {
            wideStr.append(" ");
        }
        StringBuilder spaceStr = new StringBuilder();
        for (int i = 0; i < space; i++) {
            spaceStr.append(" ");
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < words.size() - 1; i++) {
            res.append(words.get(i)).append(wideStr);
        }
        res.append(words.get(words.size() - 1)).append(spaceStr);

        return res.toString();
    }
}
