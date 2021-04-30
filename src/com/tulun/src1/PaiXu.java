package com.tulun.src1;

public class PaiXu {
    public<T extends Comparable> PaiXu(T arr[]) {
          T temp;
        for (int i = 0; i < 10; i++) {
            for (int m = 0; m < 9; m++) {
                if (arr[m + 1].compareTo(arr[m])<0) {
                    temp =arr[m];
                    arr[m] = arr[m + 1];
                    arr[m + 1] = temp;
                }
            }
        }
        for (
                int i = 0;
                i < 10; i++) {
            System.out.println(arr[i]);
        }
    }
}
