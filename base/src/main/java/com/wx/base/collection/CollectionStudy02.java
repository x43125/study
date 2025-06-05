package com.wx.base.collection;

import java.util.*;

/**
 * @author Shawn
 * @date 2025/5/25 22:04
 * @description
 */
public class CollectionStudy02 {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);

        List<Integer> list = new ArrayList<>();

        List<Integer> linkedList = new LinkedList<>();

        Map<Integer, String> table = new Hashtable<>();

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);

        Map<Integer, Integer> linkedMap = new LinkedHashMap<>();

        Map<Integer, String> treeMap = new TreeMap<>();
    }
}
