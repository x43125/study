package com.wx.algorithm.leetcode.codetop;

public class T_43_Multiply {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int len1 = num1.length(), len2 = num2.length();
        int[] number = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                number[i + j + 1] += n1 * n2;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = len1 + len2 - 1; i >= 1; i--) {
            number[i - 1] += number[i] / 10;
            number[i] = number[i] % 10;
            sb.append(number[i]);
        }

        if (number[0] != 0) {
            sb.append(number[0]);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "0";
        String num2 = "000";
        T_43_Multiply multiply = new T_43_Multiply();
        String multiply2 = multiply.multiply(num1, num2);
        System.out.println(multiply2);
    }
}
