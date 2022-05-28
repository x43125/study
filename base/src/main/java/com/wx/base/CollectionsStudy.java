package com.wx.base;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/29
 */
public class CollectionsStudy {
    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<String, String>();

        map.put("hello", "world");

        int[] arr = {1, 2, 3, 4, 5, 6};
//        List list = Arrays.asList(arr);
        // 直接add 会报异常
//        list.add(111);

        List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.<Integer>toList());
        collect.forEach(System.out::println);
    }
}
