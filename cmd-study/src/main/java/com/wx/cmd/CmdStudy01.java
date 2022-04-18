package com.wx.cmd;

import java.util.Scanner;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/02/28
 */
public class CmdStudy01 {

    public static void main(String[] args) {
        System.out.println("Hello, Welcome to my System!");

        try (Scanner scanner = new Scanner(System.in);) {
            boolean goNextLine = true;
            System.out.print("x> ");
            while (goNextLine) {
                String nextLine = scanner.nextLine();
                goNextLine = switchCase(nextLine);
                System.out.print("x> ");
            }
            System.out.println("Good Bye!");
        } catch (Exception e) {
            System.out.println("execute error: " + e.getMessage());
        }
    }

    private static boolean switchCase(String cmd) {
        if (cmd == null || "".equals(cmd))
            return true;
        switch (cmd) {
            case "hello":
                System.out.println("hello world");
                return true;
            case "exit":
            case "quit":
                return false;
            default:
                return true;
        }
    }
}
