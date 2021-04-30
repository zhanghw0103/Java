package com.tulun.src1.ArrayDeque;

import java.util.ArrayDeque;
import java.util.Iterator;

public class ArrayDequeTest2 {
    public static void main(String[] args) {
        ArrayDeque arrayDeque=new ArrayDeque();
        arrayDeque.add(1);
        arrayDeque.add(2);
        arrayDeque.add(3);
        System.out.println(arrayDeque.offer(4));
        Iterator iterator=arrayDeque.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("==========");
        arrayDeque.remove();
        arrayDeque.poll();
        Iterator iterator1=arrayDeque.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
        System.out.println("==========");
        arrayDeque.remove();
        arrayDeque.poll();
        System.out.println(arrayDeque.poll());
//        arrayDeque.element();
        System.out.println(arrayDeque.peek());
    }
}
