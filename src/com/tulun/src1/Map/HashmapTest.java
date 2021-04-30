package com.tulun.src1.Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashmapTest {
    public static void main(String[] args) {
        HashMap<String,Integer> hashMap=new HashMap<>();
        hashMap.put("a",1);
        hashMap.put("b",2);
        hashMap.put("a",3);
        hashMap.replace("a",4);
//        Iterator<Map.Entry<String,Integer>> iterator=hashMap.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry<String,Integer> next=iterator.next();
//            System.out.println("key  "+next.getKey()+"  value  "+next.getValue());
//        }
//
        Iterator<String> iterator1 = hashMap.keySet().iterator();
        while (iterator1.hasNext()){
            System.out.println("key "+iterator1.next());
        }

        Iterator<Integer> iterator2 = hashMap.values().iterator();
        while (iterator2.hasNext()){
            System.out.println("value "+iterator2.next());
        }
//
//        for (Map.Entry<String,Integer> entry:hashMap.entrySet()) {
//            System.out.println("key  "+entry.getKey()+"  value  "+entry.getValue());
//        }

//        for(String o:hashMap.keySet()){
//            System.out.println("key  "+o);
//        }
//
//        for(Integer o:hashMap.values()){
//            System.out.println("values  "+o);
//        }




    }
}
