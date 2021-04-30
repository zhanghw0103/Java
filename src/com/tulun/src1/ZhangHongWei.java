package com.tulun.src1;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class ZhangHongWei {

    public static int[] converse(int[] arr) {
        int[] arr1 = new int[arr.length];
        int n = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = arr[n];
            n--;

        }
        return arr1;

    }

    public static int count(String str, char oldchar) {
        int count = 0;

        char[] och = str.toCharArray();
        for (int i = 0; i < och.length; i++) {
            if (och[i] == oldchar) {
                count++;
            }
        }
        return count;

    }

    public static String replace(String str, char oldchar) {
        char newchar = new java.util.Scanner(System.in).next().charAt(0);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = 1;
        int count = count(str, oldchar);
        char[] ch = str.toCharArray();
        int i = ch.length - 1;
        char[] arr = Arrays.copyOf(ch, ch.length + count);
        int j = arr.length - 1;
        while (i >= 0) {
            while (i >= 0 && arr[i] != oldchar) {
                arr[j--] = arr[i--];
            }
            if (i >= 0) {
                for (m = 1; m <= n; m++) {
                    arr[j] = newchar;
                    j--;
                }
                i--;
            }
        }
        return String.valueOf(arr);
    }

    public static void del(String num) {
        char[] och = num.toCharArray();
        char n = '1';
        char[] arr1 = new char[3];
        for (int i = 0; i <= och.length; i++) {
//            int numss=arr1[i]-'0';
//            System.out.println(numss);

            if (och[i] == n) {
                arr1 = Arrays.copyOfRange(och, i, i + 3);
                break;
            }
        }

        int arr[] = new int[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(String.valueOf(arr1[i]));
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void change(String str) {
        char[] arr = str.toCharArray();
        int n = 0;
        int m=0;
        char[] arr1 = new char[arr.length];
        Scanner scanner=new Scanner(System.in);
        int num =scanner.nextInt();

        for(int i=num;i>0;i--){
            arr1[arr.length-i]=arr[m++];
        }

        for (int i=num; i < arr.length - 1; i++) {
            arr1[n] = arr[i];
            n++;
        }
        System.out.println(Arrays.toString(arr1));

    }

    public static void BaoShu(int personNumber, int number) {

        int[] persons = new int[personNumber];
        for (int i = 0; i < personNumber; i++) {
            persons[i] = i + 1;
        }
        int index = 0;
        int dunNumbers = 0;
        int duns = 0;
        while (duns != personNumber - 1) {
            if (persons[index % persons.length] == 0) {
                dunNumbers++;
                index++;
            } else {
                if ((index + 1 - dunNumbers) % number == 0) {
                    persons[index % persons.length] = 0;
                    duns++;
                    index++;
                } else {
                    index++;
                }
            }
        }
        System.out.println(Arrays.toString(persons));
    }

    public static void getone() {
        System.out.println("请输入数字");
        Scanner scanner = new Scanner(System.in);
        Integer num = scanner.nextInt();
        int count = 0;
        int one = 0;
//        for(Integer i =1;i<num;i++){
//            String string=i.toString();
//            int[]arr=new int[string.length()];
//            for(int n=0;n<string.length();n++){
//                Character character = string.charAt(n);
//                arr[n]=Integer.parseInt(character.toString());
//            }
//            for(int m=0;m<arr.length;m++){
//                if(arr[m]==1){
//                    count++;
//                }
//            }
//        }
        for (int i = 1; i <= num; i++) {
            int m = i;
            while (m != 0) {
                m = m / 10;
                count++;
            }

            int k = i;
            for (int n = 1; n <= count; n++) {

                if (k % 10 == 1) {
                    one++;
                }
                k = k / 10;
            }
        }
            System.out.println(one);

    }

    public static void quan(int[][]arr) {
        int flag = 0;
        if (arr.length % 2 != 0) {
            flag = 1;
        }
        for (int circle = 0; circle < arr.length / 2 + flag; circle++) {

            for (int column = circle; column < arr[0].length - circle; column++) {
                System.out.print(arr[circle][column] + " ");
            }

            for (int row = 1 + circle; row < arr.length - circle; row++) {
                System.out.print(arr[row][arr[0].length - 1 - circle] + " ");
            }

            for (int column = arr[0].length - 2 - circle; column >= circle; column--) {
                System.out.print(arr[arr.length - 1 - circle][column] + " ");
            }

            for (int row = arr.length - 2 - circle; row >= 1 + circle; row--) {
                System.out.print(arr[row][circle] + " ");
            }
        }
    }

    public static boolean puke(int[] a) {
        Arrays.sort(a);
        int Zero=0;int Max=a[a.length-1],min,distance;
        for(int i=0;i<a.length;i++)
        {
            if(a[i]==0)
                Zero++;
        }
        min = a[Zero];
        distance = Max-min;
        if(Zero==0)
        {
            if(distance==4)
                return true;
            else
                return false;
        }
        else if(Zero==1)
        {
            if(distance==4||distance==3)
                return true;
            else
                return false;
        }
        else if(Zero==2)
        {
            if(distance==4||distance==3||distance==2)
                return true;
            else
                return false;
        }
        return false;
    }

    public static void searchchar(char[]arr){
        int count=0;
        char[] brr=Arrays.copyOf(arr,arr.length);
        for(int i=0;i<arr.length;i++){
            for(int n=0;n<arr.length;n++){
                if(brr[i]==arr[n]){
                    count++;
                }
            }
            if(count==1){
                System.out.println(brr[i]);
                count=0;
            }
            else{
                count=0;
            }
        }



    }

    public static int NumberOf1(int n) {
        int count = 0;
        while (n!=0){
            if (n % 2 == 1){
                count++;
            }
           n /= 2;
        }
        return count;
    }

    public static int Fibonacci(int number){
        if(number==1||number==2){
            return 1;
        }
        return Fibonacci(number-1)+Fibonacci(number-2);
    }

    public static int ErfenChazhao(int arr[],int value,int left,int right){
        if(left<=right) {
            int middle = (right + left) / 2;
            if (arr[middle] == value) {
                return middle+1;
            } else if (value > middle) {
                return ErfenChazhao(arr, value, middle, right);
            } else {
                return ErfenChazhao(arr, value, left, middle);
            }
        } else {
            return -1;

            }
        }







    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {


//        char oldchar = new java.util.Scanner(System.in).next().charAt(0);
//        String str = " i am a  student ";
//        str = replace(str,oldchar);
//        System.out.println(str);

//        String num = "-+-+-+123";
//        del(num);
//         String str = "abcdef";
//         change(str);
//         System.out.println(change(str));
//       count1(10, 3);
//        getone();
//        int[][] arr = new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
//        quan(arr);
//        int[] a={10,5,0,6,8};
//        System.out.println(puke(a));
//        char []arr={'a','b','c','c','d'};
//        searchchar(arr);
//        int n=9 ;
//        System.out.println(NumberOf1(n));


//        Class c=FanShe.class;
//        Constructor constructor=c.getConstructor(String.class, int.class);
//        Object o=constructor.newInstance("zs",10);
//        Method method1=c.getMethod("getAge");
//        Method method2=c.getMethod("getName");
//        Object result1=method1.invoke(o);
//        Object result2=method2.invoke(o);
//        System.out.println(result1+ " "+result2);

//        System.out.println(Fibonacci(5));

//        int arr[]={1,3,5,7,9,10,15,23,45};
//        System.out.println(ErfenChazhao(arr,23,0,arr.length-1));










    }
}
