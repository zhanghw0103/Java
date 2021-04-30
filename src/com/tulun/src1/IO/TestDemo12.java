package com.tulun.src1.IO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Java8 新特性
 * Lambda  简洁的代码完成一个功能
 * expresstion = (method signature) -> method action
 * 函数式接口 -》 只有一个方法的接口 函数式接口都能够使用lambda表达式简化代码
 *
 * Stream
 * 流表示包括一系列元素的集合，对于这些不同类型的集合，我们都可以去进行计算
 *
 * 操作步骤：
 * 1）创建一个Stream，从集合数组中都可以获取一个stream
 * 2) 使用stream操作数据，一个操作的中间链，对数据源中的数据进行操作
 * 3）终止stream，一个终止操作，执行中间操作链并返回结果
 *
 * 课堂练习：
 * 中间流
 * 1）通过集合创建Stream
 * filter接受lambda表达式，从流中进行过滤
 * limit 使得元素不超过给定值
 * skip 跳过元素，返回一个扔掉前n个元素的流
 * distinct 筛选  通过流中元素的hashCode和equals去除重复元素
 * 2）过滤所有年龄大于22岁的同学
 * 3）删选出前3条数据
 * 4）跳过前2个元素
 * 5）过滤重复的元素
 * map 接受一个lambda表达式 使得表达式中的逻辑作用于每一个元素
 * flatMap 接受一个函数作业参数，将流中的每个值都换成另外一个流，然后所有的流连接为一个流
 * 6）筛选出所有的年龄，再过滤年龄大于20的
 * sorted - 自然排序
 * sorted - 自定义排序
 * 7）指定Comparable，对流中的对象按照成绩进行排序
 *
 * 终止流
 * 1）匹配和查找
 * allMatch 检查是否匹配所有元素 返回boolean类型
 * anyMathch 检查是否有部分匹配 返回boolean类型
 * noneMatch 检查是否没有匹配 返回boolean类型
 * findFirst 返回流中第一个元素
 * findAny 返回任意一个元素
 * count 返回流中元素总和
 * max 返回流中元素最大值
 * min 返回流中元素最小值
 *
 * 2）计算汇总为一个值
 * reduce 将流中的元素反复结合起来，得到一个值
 *
 * 3）collect使用 将流转换为其他形式
 *
 *
 */

class Student{
    private Integer id;
    private String name;
    private Integer age;
    private Double score;

    public Student(Integer id, String name, Integer age, Double score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
public class TestDemo12 {
    public static List<Student> getStdents(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1,"小明", 23, 89.5));
        students.add(new Student(2,"小花", 22, 99.5));
        students.add(new Student(3,"小蓝", 20, 79.5));
        students.add(new Student(4,"小惠", 19, 90.5));

        return students;
    }
    public static void main(String[] args) {
        //Stream介绍
        List<String> myList = Arrays.asList("a1", "a2", "a3", "a5", "a0", "c5");
        myList
                .stream() //创建流
                .filter(s->s.startsWith("a")) //过滤出以c为前缀的字符串
                .map(String::toUpperCase) //转换为大写
                .sorted() //排序
                .forEach(System.out::println);//for 循环打印
        /**
         * 中间操作：会返回一个流，链接多个中间操作
         *
         * 终端操作：对流操作的一个结束动作，一般返回一个非流的结果或者void类型
         */

        //中间流
        //创建Stream
        //集合创建Stream
        List<Student> students = getStdents();
        //顺序流
        Stream<Student> stream = students.stream();
        //并行流
        Stream<Student> stream1 = students.parallelStream();
        //数组创建流
        int[] array = {1,23, 32, 24};
        IntStream stream2 = Arrays.stream(array);
        //通过Stream.of()
        Stream<String> stream3 = Stream.of("1", "4", "5", "23");
        //Stream.iterate()创建一个无限流
        //从0开始 每隔取一个放到流中
        Stream<Integer> stream4 = Stream.iterate(0, t-> t+5);
        //过滤所有年龄大于22岁的同学
        students.stream().filter(s-> s.getAge() > 22).forEach(System.out::println);
        //筛选出前3条数据
        students.stream().limit(3).forEach(System.out::println);
        //跳过前2个元素
        students.stream().skip(2).forEach(System.out::println);
        //过滤重复的元素
        students.stream().distinct().forEach(System.out::println);
        //筛选出所有的年龄，再过滤年龄大于20的
        Stream<Integer> ageStream = students.stream().map(Student::getAge);
        ageStream.filter(age -> age>20).forEach(System.out::println);
        //指定Comparable，对流中的对象按照成绩进行排序
        students.stream().sorted((s1, s2)->(int)((s1.getScore())-s2.getScore())).forEach(System.out::println);

        //终止流
        boolean allMathch = students.stream().allMatch(student -> student.getAge() > 18);
        System.out.println(students.stream().findFirst());
        System.out.println(students.stream().count());

        Stream<Double> doubleStream = students.stream().map(Student::getScore);
        doubleStream.reduce((double)0, Double::sum);

        students.stream().filter(student -> student.getScore() > 80).collect(Collectors.toList());
        students.stream().filter(student -> student.getScore() > 80).collect(Collectors.toSet());
        //Lambda表达式
//        new Runnable(){
//            @Override
//            public void run() {
//                System.out.println("do something");
//            }
//        };//任务的创建
//
//        //lambda
//        Runnable r=()-> System.out.println("do something");
//
//        //x+y
//        //int sum = (x, y) -> x + y;
//
//        String[] strs = {"jhdsjhf", "skdjfkdjf", "AHJDJHDJH", "JFHJHDJHj"};
//        Arrays.sort(strs, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.toLowerCase().compareTo(o2.toLowerCase());
//            }
//        });
//        Arrays.sort(strs, (o1, o2)->o1.toLowerCase().compareTo(o2.toLowerCase()));
    }
}
