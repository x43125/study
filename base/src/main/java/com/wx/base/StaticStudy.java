package com.wx.base;

/**
 * @author Shawn
 * @date 2023/7/20 20:02
 * @description
 */
public class StaticStudy {
    static int age = 1;
    public static void main(String[] args) {
        age++;
        StaticStudy staticStudy = new StaticStudy();
        System.out.println(staticStudy.age);
        System.out.println(StaticStudy.age);
    }
}
