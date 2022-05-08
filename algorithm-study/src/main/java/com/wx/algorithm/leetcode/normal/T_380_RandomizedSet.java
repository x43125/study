package com.wx.algorithm.leetcode.normal;

import java.util.*;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/08
 */
public class T_380_RandomizedSet {
    private final List<Integer> list;
    private final Map<Integer, Integer> map;
    private final Random random;


    public T_380_RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }

        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        Integer index = map.get(val);
        list.set(index, list.get(list.size() - 1));
        map.put(list.get(list.size() - 1), index);
        list.remove(list.size() - 1);
        System.out.println(list.size());
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }


    public static void main(String[] args) {
        T_380_RandomizedSet randomizedSet = new T_380_RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.insert(2);
        randomizedSet.insert(3);
        randomizedSet.insert(1);
        int random = randomizedSet.getRandom();
        System.out.println(random);

        boolean remove = randomizedSet.remove(2);
        System.out.println(remove);
    }
}
