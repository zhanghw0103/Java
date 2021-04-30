package com.tulun.src1.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreate {
    //通过一个集合创建stream
    public static void test1() {
        List <Student> students =StudentData.getStudents();
        //第一种：返回一个顺序流
        Stream  <Student> stream = students.stream();
        //第二种：返回一个并行流
        Stream<Student> stream1 = students.parallelStream();
    }

    //通过一个数组创建stream
    public static void test2() {
        //获取一个整形stream
        int[]arr = {1, 34, 2, 54, 56, 34};
        IntStream stream = Arrays.stream(arr);
    }

    //通过Stream.of
    public static void test3() {
        Stream <String> stringStream = Stream.of("1", "4", "34", "23");
        Stream <Student> studentStream = Stream.of(
                new Student(1, "小白", 23, 89.5),
                new Student(2, "小蔡", 22, 90.9)
        );
    }

    //创建无限流
    public static void test4() {
        //每隔5个数取一个，从0开始，此时就会无限循环
        Stream<Integer> iterate = Stream.iterate(0,t -> t + 5);
        //取出一个随机数
        Stream<Double> generate = Stream.generate(Math::random);
    }
}
