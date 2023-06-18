package com.wx.base.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/02/28
 */
public class ComparatorStudy01 {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(4);
        list.add(2);
        list.add(7);
        list.add(1);
        list.add(9);
        list.add(3);
        list.add(5);
        list.add(8);
        list.add(6);

        list.forEach(i -> System.out.print(i + " "));
        System.out.println();
        Collections.reverse(list);
        list.forEach(i -> System.out.print(i + " "));
        System.out.println();

        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println();
        list.forEach(i -> System.out.print(i + " "));

        list.sort(((o1, o2) -> o1.compareTo(o2)));
        System.out.println();
        list.forEach(i -> System.out.print(i + " "));
    }
}
