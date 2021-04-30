package com.tulun.src1.ArrayDeque;

import java.util.ArrayDeque;
import java.util.Iterator;

public class ArrayDequeTest1 {
    public static void main(String[] args) {
        ArrayDeque arrayDeque=new ArrayDeque();
        arrayDeque.addFirst(1);
        arrayDeque.offerFirst(2);
        arrayDeque.addLast(3);
        arrayDeque.offerLast(4);
        arrayDeque.addLast(2);

        Iterator iterator=arrayDeque.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("==========");
//        arrayDeque.removeFirstOccurrence(2);
//        arrayDeque.removeLastOccurrence(2);

        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.pollLast();
        arrayDeque.pollFirst();
        System.out.println(arrayDeque.pollFirst());
        Iterator iterator1=arrayDeque.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
        System.out.println("==========");
//        arrayDeque.getFirst();

    }
}
