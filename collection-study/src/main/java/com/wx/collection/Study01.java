package com.wx.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/02/28
 */
public class Study01 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> queue1 = new PriorityQueue<>();

        Set<Integer> set = new HashSet<>();
        Set<Integer> treeSet = new TreeSet<>();

        Map<String, String> map = new HashMap<>();
        map.put("a", "b");

        Map<String, String> treeMap = new TreeMap<>();
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        Map<String, String> hashTable = new Hashtable<>();
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();


    }
}
