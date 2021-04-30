package com.tulun.src1.Stream;

import java.util.ArrayList;
import java.util.List;

public class StudentData {
    public static List<Student> getStudents(){
        ArrayList<Student> students = new ArrayList <>();
        students.add(new Student(1,"小白",23,89.5));
        students.add(new Student(2,"小蔡",22,90.9));
        students.add(new Student(3,"小惠",22,87.1));
        students.add(new Student(4,"小韩",25, 89.6));
        return students;
    }

}
