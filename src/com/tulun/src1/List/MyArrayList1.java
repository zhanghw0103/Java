package com.tulun.src1.List;

import java.util.Arrays;
import java.util.Iterator;


public class MyArrayList1<T> {
    private Object[] elementdata;
    private  int size;
    private static final int DEFAULT_SIZE = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private  int cursor;

    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {

        @Override
        public boolean hasNext() {
            if (cursor != size) {
                return true;
            } else {
                return false;
            }
        }


        @Override
        public T next() {
            rangeCheck(cursor);
            System.out.println(elementdata[cursor]);
            cursor++;
            return (T) elementdata[cursor];
        }

        @Override
        public void remove() {
            if(cursor==0){
                MyArrayList1.this.remove(cursor);
                return;
            }
            int cur=cursor-1;
            rangeCheck(cur);
            MyArrayList1.this.remove(cur);
            cursor--;
        }
    }

    public MyArrayList1() {
        elementdata = EMPTY_ELEMENTDATA;
    }

    public void EnsureCapacityInternal() {
        if (size == elementdata.length) {
            grow();
        }
    }

    public void grow() {
        if (size == 0) {
            elementdata = Arrays.copyOf(elementdata, DEFAULT_SIZE);

        } else {
            elementdata = Arrays.copyOf(elementdata, elementdata.length + (elementdata.length >> 1));
        }
    }

    public void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean add(T value) {
        EnsureCapacityInternal();
        elementdata[size] = value;
        size++;
        return true;
    }

    public boolean add(int index, T value) {
        rangeCheck(index);
        EnsureCapacityInternal();
        System.arraycopy(elementdata, index, elementdata, index + 1, size - index);
        elementdata[index] = value;
        size++;
//        for (int i = size - 1; i >= index; i--) {
//            elementdata[i + 1] = elementdata[i];
//            elementdata[index]=value;
//        }
        return true;
    }

    public boolean remove(int index) {
        rangeCheck(index);
        System.arraycopy(elementdata, index + 1, elementdata, index, size - index - 1);
        elementdata[--size] = null;
        return true;
    }

    public T get(int index) {
        rangeCheck(index);
        return (T) elementdata[index];
    }

    public void set(int index, T value) {
        rangeCheck(index);
        elementdata[index] = value;
    }

    public void show() {
        System.out.println(elementdata.length);
        System.out.println(size);
    }


//    public void main(String[] args) {
//        MyArrayList1<Integer> myArrayList1=new MyArrayList1<>();
//        myArrayList1.add(1);
//        myArrayList1.add(1);
//        myArrayList1.add(1);
//        myArrayList1.add(1);
//        myArrayList1.add(1);
//        myArrayList1.add(1);
//        myArrayList1.add(1);
//        myArrayList1.add(1);
//        myArrayList1.add(1);
//        myArrayList1.add(1);
//        myArrayList1.add(1);
//        myArrayList1.add(1);
////        myArrayList1.add(0,100);
////        myArrayList1.remove(0);
////        for(int i=0;i<size;i++){
////            System.out.println(myArrayList1.get(i));
////        }
////        System.out.println("===============================");
////        System.out.println(myArrayList1.get(0));
////        myArrayList1.show();
//        Itr itr=new Itr();
//        itr.hasNext();
//    }
}
