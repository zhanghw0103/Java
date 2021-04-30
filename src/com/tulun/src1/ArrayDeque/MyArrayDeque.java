package com.tulun.src1.ArrayDeque;

import java.util.Iterator;

public class MyArrayDeque<E> {
    private E element[];
    private int head;
    private int tail;
    private final int INIT_CAPACITY = 16;
    private int size;


    public Iterator<E> iterator(){
        return new Itr();
    }

    private class Itr implements Iterator<E>{
        private int cursor=head;
        @Override
        public boolean hasNext() {
           if (element[cursor]!=null){
               return true;
           }
            return false;
        }

        @Override
        public E next() {
            int index=cursor;
            cursor=(cursor+1)&(element.length-1);
            return (E)element[index];
        }
    }

    public MyArrayDeque() {
        element = (E[]) new Object[INIT_CAPACITY];
        head = 0;
        tail = 0;
    }

    public boolean offerFirst(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (size == 0) {
            element[0] = e;
            size++;
            return true;
        }
        head = (head - 1) & (element.length - 1);
        element[head] = e;
        size++;
        return true;

    }

    public boolean offerLast(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (size == 0) {
            element[0] = e;
            size++;
            return true;
        }
        tail = (tail + 1) & (element.length - 1);
        element[tail] = e;
        size++;
        return true;
    }

    public void grow() {
        if (size == element.length) {
            if((element.length<<1)<0){
                throw new IllegalStateException();
            }
            E newelement[] = (E[]) new Object[element.length<<1];
//            for(int i=0;i<size;i++){
//                newelement[i]=element[head];
//                head=head = (head + 1) & (element.length - 1);
//            }
            int num=element.length-head;
            System.arraycopy(element,head,newelement,0,num);
            System.arraycopy(element,0,newelement,num,head);
            element=newelement;
            head=0;
            tail=size-1;
        }

    }

    public E pollFirst() {
        if (size == 0) {
            return null;
        }
        E result = element[head];
        element[head] = null;
        head = (head + 1) & (element.length - 1);
        size--;
        return result;
    }

    public E pollLast() {
        if (size == 0) {
            return null;
        }
        E result = element[tail];
        element[tail] = null;
        tail = (tail - 1) & (element.length - 1);
        size--;
        return result;
    }


}
