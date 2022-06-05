package com.wx.algorithm.leetcode.contest.weekend.w0605;

/**
 * @Descrption: todo 这题仔细点是完全可以写出来的，时间不够了
 * @Author: x43125
 * @Date: 22/06/05
 */
public class TextEditor {
    private String text;
    private final String cursor = "|";
    private int cursorIndex = 0;
    int length;

    public TextEditor() {
        text = "";
        length = 0;
    }

    public void addText(String text) {
        this.text = this.text.substring(0, cursorIndex) + text + this.text.substring(cursorIndex);
        cursorIndex += text.length();
        length += text.length();
    }

    public int deleteText(int k) {
        if (cursorIndex >= k) {
            this.text = this.text.substring(0, cursorIndex - k) + this.text.substring(cursorIndex);
            cursorIndex -= k;
            return k;
        } else {
            int temp = cursorIndex;
            this.text = this.text.substring(cursorIndex);
            cursorIndex = 0;
            return temp;
        }
    }

    public String cursorLeft(int k) {
        if (cursorIndex >= k) {
            cursorIndex -= k;
            if (cursorIndex >= 10) {
                return text.substring(cursorIndex - 10, cursorIndex);
            } else {
                return text.substring(0, cursorIndex);
            }
        } else {
            cursorIndex = 0;
            return "";
        }
    }

    public String cursorRight(int k) {
        if (cursorIndex + k >= length) {
            cursorIndex = length;
            if (cursorIndex >= 10) {
                return text.substring(cursorIndex - 10, cursorIndex);
            } else {
                return text.substring(0, cursorIndex);
            }
        } else {
            cursorIndex += k;
            if (cursorIndex >= 10) {
                return text.substring(cursorIndex - 10, cursorIndex);
            } else {
                return text.substring(0, cursorIndex);
            }
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        textEditor.addText("leetcode");
        int deleteText = textEditor.deleteText(4);
        System.out.println("deleteText = " + deleteText);
        textEditor.addText("practice");

    }
}
