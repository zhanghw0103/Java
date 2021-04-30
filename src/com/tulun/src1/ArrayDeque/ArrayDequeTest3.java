package com.tulun.src1.ArrayDeque;

import java.util.ArrayDeque;
import java.util.Iterator;

public class ArrayDequeTest3 {
    public static void main(String[] args) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(1);
        arrayDeque.push(2);
        arrayDeque.push(3);
        arrayDeque.push(4);
        arrayDeque.pop();
        System.out.println(arrayDeque.size());
        System.out.println(arrayDeque.isEmpty());
        Iterator iterator = arrayDeque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("==========");
        Iterator iterator1 = arrayDeque.descendingIterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
        System.out.println(arrayDeque.contains(1));
        arrayDeque.toArray();
        arrayDeque.clear();


    }
}
