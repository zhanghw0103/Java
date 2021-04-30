package com.tulun.src1.Map;

import java.security.Key;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("a", 9);
        treeMap.put("b", 5);
        treeMap.put("d", 7);
        treeMap.put("c", 22);
//        Map.Entry<String,Integer> entry=treeMap.lowerEntry("c");
//        String key=treeMap.lowerKey("c");
//        System.out.println(entry.getKey()+"==="+entry.getValue());
//        System.out.println(key);

//        Map.Entry<String, Integer> entry = treeMap.floorEntry("c");
//        String key = treeMap.floorKey("c");
//        System.out.println(entry.getKey() + "===" + entry.getValue());
//        System.out.println(key);

//        Map.Entry<String, Integer> entry = treeMap.ceilingEntry("c");
//        String key = treeMap.ceilingKey("c");
//        System.out.println(entry.getKey() + "===" + entry.getValue());
//        System.out.println(key);

//        Map.Entry<String, Integer> entry = treeMap.higherEntry("c");
//        String key = treeMap.higherKey("c");
//        System.out.println(entry.getKey() + "===" + entry.getValue());
//        System.out.println(key);

        Map.Entry<String, Integer> entryfirst = treeMap.firstEntry();
        Map.Entry<String, Integer> entrylast = treeMap.lastEntry();
        System.out.println(entryfirst.getKey() + "===" + entryfirst.getValue());
        System.out.println(entrylast.getKey() + "===" + entrylast.getValue());

        treeMap.pollFirstEntry();
        treeMap.pollLastEntry();

    }
}
