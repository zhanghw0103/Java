package com.tulun.src1.Map;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(1, "a");
        weakHashMap.put(5, "c");
        weakHashMap.put(3, "b");
        weakHashMap.put(10, "q");
        weakHashMap.remove(10);
        weakHashMap.replace(1, "w");
        weakHashMap.put(new Integer(99),"z");
        Integer integer=new Integer(100);
        String string=new String("r");
        weakHashMap.put(integer,string);
        integer=null;


        System.gc();
        Iterator<Map.Entry<Integer, String>> iterator = weakHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            System.out.println("key  " + next.getKey() + "  value  " + next.getValue());
        }
    }


}
