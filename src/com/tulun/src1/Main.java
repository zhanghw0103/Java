package com.tulun.src1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string1=scanner.toString();
        String string2=scanner.toString();
        char[]chars1=string1.toCharArray();
        char[]chars2=string2.toCharArray();
        int[] index=new int[20];
        for (int i=0;i<chars1.length;i++){
            for (int n = 0; n < chars2.length; n++) {
                if (chars1[i]==chars2[n]){
                    index[0]=i;
                    while (true){
                        if (chars1[++i]!=chars2[++n]){
                            index[1]=i;
                        }
                    }

                }
            }
        }


    }
}
