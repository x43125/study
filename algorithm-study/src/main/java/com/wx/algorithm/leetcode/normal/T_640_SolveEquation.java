package com.wx.algorithm.leetcode.normal;

/**
 * @Descrption: 输入: equation = "x+5-3+x=6+x-2"
 * 输出: "x=2"
 * @Author: x43125
 * @Date: 22/05/08
 */
public class T_640_SolveEquation {
    public static void main(String[] args) {
        String equation = "x=2x+1";
        String s = solveEquation(equation);
        System.out.println(s);
    }

    public static String solveEquation(String equation) {
        String[] split = equation.split("=");
        String left = split[0];
        String right = split[1];

        Temp leftT = calc(left);
        Temp rightT = calc(right);

        int suf = leftT.suf - rightT.suf;
        int num = rightT.num - leftT.num;
        if (suf == 0) {
            if (num != 0) {
                return "No solution";
            }
            return "Infinite solutions";
        } else {
            return "x=" + (num / suf);
        }
    }

    private static Temp calc(String equation) {
        StringBuilder s = new StringBuilder();
        int start = 0;
        char lastOperator = '+';

        if ("-+".contains(String.valueOf(equation.charAt(0)))) {
            lastOperator = equation.charAt(0);
            start = 1;
        }

        Temp temp = new Temp();
        for (int i = start; i <= equation.length(); i++) {
            if (i != equation.length() && !"-+".contains(String.valueOf(equation.charAt(i)))) {
                s.append(equation.charAt(i));
            } else {
                String num = s.toString();
                if (num.contains("x")) {
                    int suf;
                    if ("x".equals(num)) {
                        suf = 1;
                    } else {
                        suf = Integer.parseInt(num.substring(0, num.length() - 1));
                    }
                    if (lastOperator == '+') {
                        temp.suf += suf;
                    } else {
                        temp.suf -= suf;
                    }
                } else {
                    int no = Integer.parseInt(num);
                    if (lastOperator == '+') {
                        temp.num += no;
                    } else {
                        temp.num -= no;
                    }
                }
                if (i != equation.length()) {
                    lastOperator = equation.charAt(i);
                }
                s = new StringBuilder();
            }
        }
        return temp;
    }

    static class Temp {
        int suf;
        int num;
    }

}
