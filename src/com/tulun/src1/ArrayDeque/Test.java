package com.tulun.src1.ArrayDeque;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        MyArrayDeque<Integer> myArrayDeque=new MyArrayDeque();
//        myArrayDeque.offerFirst(1);
//        System.out.println(myArrayDeque.pollFirst());
//        System.out.println(myArrayDeque.pollFirst());

        myArrayDeque.offerFirst(1);
        myArrayDeque.offerFirst(2);
        myArrayDeque.offerLast(3);
//        System.out.println(myArrayDeque.pollFirst());
//        System.out.println(myArrayDeque.pollFirst());
//        System.out.println(myArrayDeque.pollFirst());
//        System.out.println(myArrayDeque.pollFirst());
        Iterator<Integer> iterator=myArrayDeque.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
