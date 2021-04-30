package com.tulun.src1.List;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> arrayList=new ArrayList();
        ArrayList<String> arrayList1=new ArrayList();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        arrayList.add("e");
        arrayList.add("f");
        arrayList.add("g");

//        arrayList1.addAll(arrayList);

//        arrayList.removeAll(arrayList1);
//        arrayList.set(2,"q");
//        arrayList.remove(2);
//        arrayList.remove("a");

        for(int i=0;i<arrayList.size();i++){
            System.out.print(arrayList.get(i));
       }
        System.out.println();
        System.out.println("===========================================");

        for (String o:arrayList) {
            System.out.print (o);

        }
        System.out.println();
        System.out.println("===========================================");

        Iterator<String> iterator=arrayList.iterator();
        while (iterator.hasNext()){
            String next =iterator.next();
            System.out.print(next);
        }
        System.out.println();
        System.out.println("===========================================");

        ListIterator<String> listIterator=arrayList.listIterator();
        while(listIterator.hasNext()){
            String next =listIterator.next();
            System.out.print(next);
        }
        System.out.println();
        System.out.println("===========================================");

        ListIterator<String> listIterator1=arrayList.listIterator(5);
        while (listIterator1.hasPrevious()){
            System.out.print(listIterator1.previous());
        }



    }
}
