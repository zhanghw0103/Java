package com.tulun.src1.Map;

import java.util.Iterator;



public class Test {
    public static void main(String[] args) {
        MyHashmap<String,Integer> myHashmap = new MyHashmap<>();

        myHashmap.put("tom", 1);
        myHashmap.put(null, 1);
        myHashmap.put(null,2);
        myHashmap.remove(null);
//        System.out.println(myHashmap.get(null));
//        System.out.println(myHashmap.replace("tom",1));
        Iterator<MyHashmap.Entry<String,Integer>> iterator=myHashmap.Iterator();
        while (iterator.hasNext()){
            MyHashmap.Entry<String,Integer> next=iterator.next();
            System.out.println(next.value);
        }








    }
}