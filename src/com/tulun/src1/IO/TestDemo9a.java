package com.tulun.src1.IO;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TestDemo9a {
    //计算二进制中1的个数
    public static int countBits(int num){
        int count = 0;
        while(num != 0){
            count++;
            //去掉最后一位的1
            num &= (num-1);
        }
        return count;
    }

    //调整奇数在前偶数在后

    /**
     * 解法一：
     * 不考虑时间复杂度，最简单的思路就是从头到位扫描这个数组，每碰到一个偶数，拿出
     * 这个数字，并且将这个数字后面的所有数字往前挪动一位，挪完之后在数组的末尾有一
     * 个空位，这时再将偶数放入这个空位，由于每碰到一个偶数就需要移动O(n)个数字，因此
     * 总的时间复杂度O(n^2)
     *
     * 解法二：
     * 这道题目要求把奇数放在数组的前半部分，偶数放在数组的后半部分，因此所有的奇数应
     * 该位于偶数的前面。也就是说，我们在扫描这个数组的时候，如果发现有偶数出现在奇数
     * 的前面，则交换它们的顺序。可以维护两个引用，第一个引用初始化时只想数组的第一个
     * 数字，它只向后移动；第二个引用初始化时指向数组的最后一个数字，它只向前移动。在
     * 两个引用相遇之前，第一个引用总是位于第二个引用的前面，如果第一个引用指向的数字
     * 是偶数，并且第二个引用指向的数字是奇数，则交换这两个数
     */
    public static void adjust(int[] array){
        if(array == null && array.length == 0){
            return;
        }

        int start = 0;//指向偶数所在位置
        int end = array.length-1;//指向奇数所在位置

        while(start < end){
            //向后移动start引用，直到它指向偶数，找偶数的过程
            while(start < end && (array[start] % 2) != 0){
                start++;
            }
            //向前移动end引用，直到它指向奇数，找奇数的过程
            while(start < end && (array[end] % 2) == 0){
                end--;
            }
            //执行到这里说明start指向当前第一个偶数，end指向当前序列最后一个奇数
            if(start < end){
                int tmp = array[start];
                array[start] = array[end];
                array[end] = tmp;
            }
        }
    }

    //替换空格为%20

    /**
     * 解法一：
     * 最直观的做法就是从头到尾扫描字符串，每次碰到空格字符的时候进行替换，由于是把
     * 1个字符替换成3个字符，我们必须要把空格后面的字符都后移2个格子，否则就有两个
     * 字符被覆盖，通过这种方式我们发现，在替换空格的过程当中，很多字符都需要移动很
     * 多次，对于每个空格字符，需要移动后面O(n)个字符，因此对于含有O(n)个空格字符
     * 的字符串而言，总的时间效率是O(n^2)
     * 解法二：
     * 减少字符的移动次数，将从前向后替换换为从后向前替换
     */
    public static String replaceSpace1(String strs){
        //参数的合法性
        if(strs == null || strs.length() == 0){
            return null;
        }

        //将当前字符串转换为一个字符数组
        char[] chars = strs.toCharArray();

        int count = 0; //表示空格的数量
        for(int i=0; i<chars.length; i++){
            if(chars[i] == ' '){
                count++;
            }
        }
        //创建一个新的数组
        char[] newChars = new char[chars.length + 2*count];
        int index = 0;

        //时间复杂度O(n) 空间复杂度O（n）
        for(int i=0; i<chars.length; i++){
            //判断当前字符是否是空格字符，如果是空格字符，替换当前的空格字符为%20
            if(chars[i] == ' '){
                //移动空格后面的字符
                newChars[index++] = '%';
                newChars[index++] = '2';
                newChars[index++] = '0';
            }else{
                newChars[index++] = chars[i];
            }
        }
        return new String(newChars);

    }
    //String是不可变的，转为StringBuilder或者StringBuffer直接替换
    public static String replaceSpace2(String strs){
        if(strs == null || strs.length() == 0){
            return null;
        }

        StringBuilder strb = new StringBuilder(strs);
        for(int i=0; i<strb.length(); i++){
            char current = strb.charAt(i);
            //遇到一个空格，则直接调用StringBuilder中replace方法，
            //替换i和i+1之间的字符串为%20，注意这里包含i不包含i+1
            if(current == ' '){
                strb.replace(i, i+1, "%20");
            }
        }

        return strb.toString();
    }

    public static String replaceSpace3(String strs){
        if(strs == null || strs.length() == 0){
            return null;
        }

        //计算空格的数目,可以得到替换后字符串的总长度
        //即为原来长度 + 2*空格数目
        int count = 0;
        for(int i=0; i<strs.length(); i++){
            if(strs.charAt(i) == ' '){
                count++;
            }
        }

        int indexOfOrigin = strs.length()-1;
        int indexOfNew = strs.length() + 2*count - 1;

        StringBuilder strBuilder = new StringBuilder(strs);
        for(int i=0; i<2*count; i++){
            strBuilder.append(" ");
        }

        while(indexOfOrigin >= 0 && indexOfNew > indexOfOrigin){
            if (strBuilder.charAt(indexOfOrigin) == ' ') {
                //是空格，则在indexOfNew之前插入三个字符，即%20
                strBuilder.setCharAt(indexOfNew--, '%');
                strBuilder.setCharAt(indexOfNew--, '2');
                strBuilder.setCharAt(indexOfNew--, '0');
            }else{
                //反之直接将indexOfOrigin位置的数据放入indexOfNew位置
                strBuilder.setCharAt(indexOfNew--, strBuilder.charAt(indexOfOrigin));
            }
            indexOfOrigin--;
        }
        return strBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(countBits(-1));
        System.out.println(countBits(0));
        System.out.println(countBits(1));
        System.out.println(countBits(11));

        int[] array = {1, 0, 4, 3, 5, 8, 9, 13, 10, 7};
        adjust(array);
        System.out.println(Arrays.toString(array));

        String strs = "We are  family !  ";
        System.out.println(replaceSpace1(strs));
        System.out.println(replaceSpace2(strs));
        System.out.println(replaceSpace3(strs));
    }
}
