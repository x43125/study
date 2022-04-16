package com.wx.algorithm.coursera.lesson01;

public class HelloGoodbye {

    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }
        System.out.println("Hello " + args[0] + " and " + args[1] + ".");
        System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
    }

}