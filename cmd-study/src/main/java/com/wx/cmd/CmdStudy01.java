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

        /** TODO: add a init function 
         * - init the return word
         * - init the tips word
         */

        try (Scanner scanner = new Scanner(System.in);) {
            boolean goNextLine = true;
            System.out.print("x> ");
            while (goNextLine) {
                // TODO: add the function about keyboard shortcut key e.g.: ctrl
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
        String trimCmd = cmd.trim();

        if (cmd == null || "".equals(cmd))
            return true;
        switch (trimCmd) {
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
