package com.tulun.src1.List;

import java.util.Iterator;

public class MyLinkedList<T> {
    private Node first;
    private Node last;
    private int size;
    private int cursor;

    private static class Node<T> {
        T item;
        Node pre;
        Node next;

        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

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
            checkindex(cursor);
            int cur = cursor;
            System.out.println(nodeindex(cur).item);
            cursor++;
            return (T) nodeindex(cur).item;
        }

        @Override
        public void remove() {
            if (cursor == 0) {
                MyLinkedList.this.remove(cursor);
                return;
            }
            int cur = cursor - 1;
            checkindex(cur);
            MyLinkedList.this.remove(cur);
            cursor--;

        }
    }


    public MyLinkedList() {
    }

    public Node nodeindex(int index) {
        checkindex(index);
        if (index < (size >> 1)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.pre;
            }
            return x;
        }
    }

    public void checkindex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void add(T item) {
        Node<T> l = last;
        Node<T> newnode = new Node(item, l, null);
        if (l == null) {
            first = newnode;
        } else {
            l.next = newnode;
        }
        last = newnode;
        size++;

    }

    public void add(int index, T item) {
        checkindex(index);
        if (index == size) {
            add(item);
        }
        Node<T> s = nodeindex(index);
        Node<T> newnode = new Node<T>(item, s.pre, s);
        if (s.pre == null) {
            first = newnode;
        } else {
            s.pre.next = newnode;
        }
        s.pre = newnode;
        size++;

    }

    public void remove(int index) {
        checkindex(index);
        Node<T> tNode = nodeindex(index);
        if (size == 1) {
            first = null;
            last = null;
            size--;
        } else if (tNode == first) {
            first = tNode.next;
            first.pre = null;
            size--;
        } else if (tNode == last) {
            last = tNode.pre;
            last.next = null;
            size--;
        } else {
            tNode.pre.next = tNode.next;
            tNode.next.pre = tNode.pre;
            size--;
        }
        tNode.item = null;
        tNode.pre = null;
        tNode.next = null;
    }

    public void set(int index, T newvalue) {
        checkindex(index);
        if (index < (size >> 1)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
                x.item = newvalue;
            }

        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.pre;
                x.item = newvalue;
            }
        }
    }

    public void get(int index) {
        checkindex(index);
        if (index < (size >> 1)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            System.out.println(x.item);
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.pre;
            }
            System.out.println(x.item);
        }


    }
}
