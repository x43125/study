package com.wx.cmd;

import java.util.Properties;
import java.util.Scanner;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/02/28
 */
public class CmdStudy01 {

    private Properties properties;

    public static void main(String[] args) {
        System.out.println("Hello, Welcome to my System!");
        CmdStudy01 cmdStudy01 = new CmdStudy01();
        cmdStudy01.run();
    }

    private void run() {

        /** TODO: add a init function
         * - init the return word √
         * - init the tips word
         */
        InitCmd initCmd = new InitCmd();
        properties = initCmd.getReturnProperties();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean goNextLine = true;
            System.out.print("x> ");
            while (goNextLine) {
                // TODO: add the function about keyboard shortcut key e.g.: ctrl
                String nextLine = scanner.nextLine();
                goNextLine = sendCmd(nextLine);
                System.out.print("x> ");
            }

            System.out.println("Good Bye!");
        } catch (Exception e) {
            System.out.println("execute error: " + e.getMessage());
        }
    }

    /**
     * handle cmd, if cmd need be sent to the server then send it, if not handle it directly
     *
     * @param cmd
     * @return
     */
    private boolean sendCmd(String cmd) {
        String trimCmd = cmd.trim();
        if ("".equals(cmd)) {
            return true;
        }

        if ("quit".equals(trimCmd) || "exit".equals(trimCmd)) {
            return false;
        }

        String executeResult = executeCmdAndReturn(trimCmd);
        return handleExecuteResult(executeResult);
    }

    /**
     * handle the result returned from execute cmd
     *
     * @param cmd
     * @return
     */
    private boolean handleExecuteResult(String cmd) {
        if (!"".equals(cmd)) {
            System.out.println(cmd);
        }
        return true;
    }

    /**
     * handle cmd localized, if the cmd need to be sent to the server, the server will implement this function
     *
     * @param cmd
     * @return
     */
    private String executeCmdAndReturn(String cmd) {
        if (properties.containsKey(cmd)) {
            return properties.getProperty(cmd);
        }
        return "";
    }
}
