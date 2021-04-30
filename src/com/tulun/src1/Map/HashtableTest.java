package com.tulun.src1.Map;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class HashtableTest {
    public static void main(String[] args) {
        Hashtable<Integer,String> hashtable=new Hashtable<>();
        hashtable.put(1,"a");
        hashtable.put(5,"c");
        hashtable.put(2,"q");
        hashtable.put(10,"w");
        hashtable.remove(10);
        hashtable.replace(1,"y");

        Enumeration<Integer> keys=hashtable.keys();
        while (keys.hasMoreElements()){
            Integer key=keys.nextElement();
            System.out.println(key+"====="+hashtable.get(key));
        }

        Iterator<Map.Entry<Integer, String>> iterator = hashtable.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            System.out.println("key  " + next.getKey() + "  value  " + next.getValue());
        }

    }
}
