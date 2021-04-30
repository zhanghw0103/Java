package com.tulun.src1;

import java.util.Arrays;

public class QuickSort {
    private static <T extends Comparable>int partition(T[] arr,int low,int high){
        if(arr==null||arr.length==1){
            return -1;
        }
        T temp =arr[low];
        while(low<high){
            while (arr[high].compareTo(temp)>0&&low<high){
                high--;
            }
            if(low==high){
                break;
            }
            arr[low]=arr[high];
            while (arr[low].compareTo(temp)<=0&&low<high){
                low++;
            }
            if (low==high){
                break;
            }
            arr[high]=arr[low];
        }
        arr[low]=temp;
        return low;
    }
    private static <T extends Comparable>void QuickSort(T[] arr,int low,int high){
        int index=partition(arr,low,high);
        if(index<0){
            return;
        }
        if(index-low>1){
            QuickSort(arr,low,index-1);
        }
        if(high-index>1){
            QuickSort(arr,index+1,high);
        }

    }
    public static <T extends Comparable> void quickSort (T[] arr){
        if(arr==null){
            return;
        }
        QuickSort(arr,0,arr.length-1);
    }

    public static void main(String[] args) {
        Integer[]arr={3,3,3,1,1,3,9};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

