package com.tulun.src1.List;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedlistTest {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.removeFirst();
        linkedList.addFirst(1);
        linkedList.remove(7);
        linkedList.set(0, 999);

        for (int i = 0; i < linkedList.size(); i++) {
            System.out.print(linkedList.get(i) + "  ");
        }
        System.out.println();
        System.out.println("===========================================");

        for (Integer o : linkedList) {
            System.out.print(o + "  ");
        }
        System.out.println();
        System.out.println("===========================================");

        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.print(next + "  ");
        }
        System.out.println();
        System.out.println("===========================================");

        ListIterator<Integer> listIterator=linkedList.listIterator();
        while (listIterator.hasNext()){
            Integer next =listIterator.next();
            System.out.print(next+"  ");
        }
        System.out.println();
        System.out.println("===========================================");

        ListIterator<Integer> listIterator1=linkedList.listIterator(5);
        while (listIterator1.hasPrevious()){
            System.out.print(listIterator1.previous()+"  ");
        }

    }

}
