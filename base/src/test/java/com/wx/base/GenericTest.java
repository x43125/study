package com.wx.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/28
 */
public class GenericTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);

        Integer integer = list.get(0);
        System.out.println(integer);
    }
}
