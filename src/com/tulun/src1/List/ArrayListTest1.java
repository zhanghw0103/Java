package com.tulun.src1.List;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
public class ArrayListTest1 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList1=new ArrayList();
        ArrayList<Integer> arrayList2=new ArrayList();
        arrayList1.add(1);arrayList1.add(2);arrayList1.add(3);arrayList1.add(4);arrayList1.add(5);
        arrayList2.add(4);arrayList2.add(5);arrayList2.add(6);arrayList2.add(7);arrayList2.add(8);

//        //交集
//        arrayList1.retainAll(arrayList2);
//        for (Integer o: arrayList1) {
//            System.out.print(o);
//        }


//        //差集
//        arrayList1.removeAll(arrayList2);
//        for (Integer o: arrayList1){
//            System.out.print(o);
//        }

        //不重复并集
        arrayList1.removeAll(arrayList2);
        arrayList1.addAll(arrayList2);
        for (Integer o: arrayList1){
            System.out.print(o);
        }

    }

}
