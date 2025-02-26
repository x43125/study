package com.wx.algorithm.algorithm4;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        scanner.close();

        int result = calculate(expression);
        System.out.println(expression + " = " + result);
    }

    private static int calculate(String expression) {
        Stack<Character> operatorStack = new Stack<>();
        Stack<Integer> operandStack = new Stack<>();
        boolean lastIsNumber = false;
        for (char c : expression.toCharArray()) {
            if (c == '(' || c == ' ') {
                lastIsNumber = false;
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                operatorStack.push(c);
                lastIsNumber = false;
            } else if (c >= '0' && c <= '9') {
                // 当前是数字
                int nowNumber = Integer.parseInt(String.valueOf(c));
                if (lastIsNumber) {
                    // 上一个指针也是数字
                    int lastNumber = operandStack.pop();
                    nowNumber = lastNumber * 10 + nowNumber;
                }
                operandStack.push(nowNumber);
                lastIsNumber = true;
            } else if (c == ')') {
                int second = operandStack.pop();
                int first = operandStack.pop();
                Character operator = operatorStack.pop();
                int result = 0;
                switch (operator) {
                    case '+':
                        result = first + second;
                        break;
                    case '-':
                        result = first - second;
                        break;
                    case '*':
                        result = first * second;
                        break;
                    case '/':
                        result = first / second;
                        break;
                    default:
                        break;
                }
                operandStack.push(result);
            } else {
                System.out.println("非法符号");
                throw new RuntimeException();
            }
        }

        return operandStack.pop();
    }
}
