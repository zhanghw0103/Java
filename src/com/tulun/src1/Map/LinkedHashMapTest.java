package com.tulun.src1.Map;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, "a");
        linkedHashMap.put(3, "b");
        linkedHashMap.put(2, "c");
        linkedHashMap.remove(3);
        linkedHashMap.replace(1,"t");

        Iterator<Map.Entry<Integer,String> >iterator = linkedHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer,String> next = iterator.next();
            System.out.println("key  " + next.getKey() + "  value  " + next.getValue());
        }


    }
}
