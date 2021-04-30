package com.tulun.src1.Thread;

import java.util.Scanner;

/**
 * 银行家算法
 */

public class TestDemo11 {
    //可用资源
    private static int[] avaliable = new int[]{3,3,2};
    //每个线程最大资源数
    private static int[][] max = new int[][]{{8,5,3}, {3,2,2}, {9,0,2},{2,2,2}, {4,3,3}};
    //每个线程已分配的资源
    private static int[][] alloction = new int[][]{{0,1,1,}, {2,0,0}, {3,0,2}, {2,1,1}, {0,0,2}};
    //每个线程需要的资源数
    private static int[][] need = new int[][]{{8,4,2}, {1,2,2}, {6,0,0}, {0,1,1}, {4,3,1}};

    public static void showData(){
        System.out.println("线程编号    最大需求     已分配      还需要");
        for(int i=0; i<5; i++){
            System.out.print(i+"        ");
            for(int j=0; j<3; j++){
                System.out.print(max[i][j]+"   ");//i表示线程号 j表示资源数
            }
            for(int j=0; j<3; j++){
                System.out.print(alloction[i][j]+"   ");//i表示线程号 j表示资源数
            }
            for(int j=0; j<3; j++){
                System.out.print(need[i][j]+"   ");//i表示线程号 j表示资源数
            }
            System.out.println();
        }
    }

//    public static boolean allocate(int RequestNum, int Request[]){
//
//    }

    public static void main(String[] args) {
        showData();
        //请求线程资源存放的数组
        int[] request = new int[3];
        int requestNum;
        String source[] = new String[]{"A", "B", "C"};
        Scanner s = new Scanner(System.in);
        String choice = new String();
        while(true){
            System.out.println("请输入要请求的线程编号：");
            requestNum = s.nextInt();
            System.out.println("请输入要请求的资源数目：");
            for(int i=0; i<3; i++){
                System.out.println(source[i]+"资源的数目：");
                request[i] = s.nextInt();
            }
            //分配资源
            //allocate()
            System.out.println("是否再次请求分配(y/n)");
            choice = s.next();
            if(choice.equals("n")){
                break;
            }
        }
    }
}
