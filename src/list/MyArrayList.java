package list;

import java.util.Arrays;

public class MyArrayList<T extends Comparable<T>> implements List<T> {
    private T[] element;
    private int size;
    private static final int DEFAULT_CAPACITY=10;

    public MyArrayList() {
        element = (T[]) new Comparable[DEFAULT_CAPACITY];
    }
    public MyArrayList(int Capacity ){
        element = (T[])new Comparable[Capacity];
    }

    @Override
    public void addhead(T value) {
        if(size==element.length){
            element= Arrays.copyOf(element,element.length+(element.length>>1));
        }
        for(int i=size-1;i>=0;i--){
            element[i+1]=element[i];
        }
        element[0]=value;
        size++;

    }

    @Override
    public void addlast(T value) {
        if(size==element.length){
            element= Arrays.copyOf(element,element.length+(element.length>>1));
        }
        element[size++]=value;

    }

    @Override
    public void deletehead() {
        for(int i=0;i<size-1;i++){
            element[i]=element[i+1];
        }
        element[size-1]=null;
        size--;

    }

    @Override
    public void deletelast() {
        element[size-1]=null;
        size--;

    }

    @Override
    public void deleteValue(T value) {
        for(int i=0;i<size;i++){
            if(element[i]==value){
                for(int n=i;n<size-i;n++){
                    element[n]=element[n+1];
                    size--;
                }
            }
        }


    }

    @Override
    public void change(T value1, T value2) {
        for(int i=0;i<size;i++){
            if (element[i]==value1){
                element[i]=value2;

            }
        }

    }

    @Override
    public boolean contains(T value) {
        for(int i=0;i<size;i++){
            if(element[i].compareTo(value)==0){
                return true;
            }
        }
        return false;
    }


    @Override
    public void size() {
        System.out.println(size);
    }
}


