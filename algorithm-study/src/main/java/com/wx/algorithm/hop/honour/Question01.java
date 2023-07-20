package com.wx.algorithm.hop.honour;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author Shawn
 * @date 2023/7/20 20:10
 * @description
 */
public class Question01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next(), 16);
        int len = n;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            String tmp = sc.next();
            if ("A".equals(tmp)) {
                sb.append("12 34 ");
                len++;
            } else if ("B".equals(tmp)) {
                sb.append("AB CD ");
                len++;
            } else {
                sb.append(tmp).append(" ");
            }
        }
        System.out.println(Integer.toHexString(len).toUpperCase() + " " + sb.toString().trim());
        sc.close();
    }

    public static String transfer(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }

        String[] split = str.split("\\s+");
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            sb.append(" ");
            count++;
            if (Objects.equals(split[i], "A")) {
                count++;
                sb.append("12 34");
            } else if (Objects.equals(split[i], "B")) {
                count++;
                sb.append("AB CD");
            } else {
                sb.append(split[i]);
            }
        }

        return String.valueOf(count) + sb;
    }
}
