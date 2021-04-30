package com.tulun.src1.PriorityQueue;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class MyPriorityQueue<E> {

    private Object[] queue;
    private static final int DEFAULT_CAPACITY = 11;
    private final Comparator<? super E> comparator;
    private int size;


    public MyPriorityQueue() {
        queue = new Object[DEFAULT_CAPACITY];
        this.comparator = null;
    }

    public MyPriorityQueue(Comparator<? super E> comparator) {
        queue = new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (size == queue.length) {
            grow();
        }
        if (size == 0) {
            queue[0] = e;
            size++;
            return true;
        }
        int index = size;
        siftup(index, e);
        size++;
        return true;
    }

    public void grow() {
        int oldlength = queue.length;
        int newlength;
        if (oldlength < 64) {
            newlength = oldlength + oldlength + 2;
        } else {
            newlength = oldlength + oldlength >> 1;
        }
        queue = Arrays.copyOf(queue, newlength);

    }

    public void siftup(int index, E e) {
        if (comparator != null)
            siftUpUsingComparator(index, e);
        else
            siftUpComparable(index, e);
    }

    public void siftUpUsingComparator(int index, E e) {
        while (index > 0) {
            int parent = (index - 1) >> 1;
            Object value = queue[parent];
            if (comparator.compare(e, (E) value) >= 0) {
                break;
            }
            queue[index] = value;
            index = parent;
        }
        queue[index] = e;
    }

    public void siftUpComparable(int index, E e) {
        Comparable<? super E> key = (Comparable<? super E>) e;
        while (index > 0) {
            int parent = (index - 1) >> 1;
            Object value = queue[parent];
            if (key.compareTo((E) value) >= 0) {
                break;
            }
            queue[index] = value;
            index = parent;
        }
        queue[index] = key;
    }

    public E peek() {
        if (size == 0) {
            return null;
        }
        return (E) queue[0];
    }

    public E element() {
        E x = peek();
        if (x != null) {
            return x;
        } else {
            throw new NoSuchElementException();
        }
    }

    public E poll() {
        if (size == 0) {
            return null;
        }
        E result = (E) queue[0];
        int s = --size;
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0) {
            siftDown(0, x);
        }

        return result;

    }

    public void siftDown(int index, E e) {
        if (comparator != null)
            siftDownUsingComparator(index, e);
        else
            siftDownComparable(index, e);
    }

    private void siftDownComparable(int index, E e) {
        Comparable<? super E> key = (Comparable<? super E>) e;
        int half = size >> 1;
        while (index < half) {
            int child = (index << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size) {
                Object r = queue[right];
                Comparable<? super E> ckey = (Comparable<? super E>) c;
                if (ckey.compareTo((E) r) > 0) {
                    c = r;
                    child = right;
                }
            }
            if (key.compareTo((E) c) < 0) {
                break;
            }
            queue[index] = c;
            index = child;
        }
        queue[index] = e;
    }

    private void siftDownUsingComparator(int index, E e) {
        int half = size >> 1;
        while (index < half) {
            int child = (index << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size) {
                Object r = queue[right];
                if (comparator.compare((E) c, (E) r) > 0) {
                    c = r;
                    child = right;
                }
            }
            if (comparator.compare(e, (E) c) < 0) {
                break;
            }
            queue[index] = c;
            index = child;
        }
        queue[index] = e;
    }

    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1) {
            return false;
        } else {
            int s=--size;
            if (i == s) {
                queue[i] = null;
            } else {
                E moved=(E)queue[s];
                queue[s]=null;
                siftDown(i,moved);
                if (queue[i] == moved) {
                    siftup(i,moved);
                }
            }
            return true;
        }
    }

    private int indexOf(Object o){
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(queue[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}





