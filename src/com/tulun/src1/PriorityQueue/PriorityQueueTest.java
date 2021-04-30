package com.tulun.src1.PriorityQueue;

import java.util.*;

public class PriorityQueueTest {


    public static void main(String[] args) {
//        PriorityQueue<Integer> priorityQueue=new PriorityQueue();
//        priorityQueue.offer(1);
//        priorityQueue.offer(50);
//        priorityQueue.offer(20);
//        priorityQueue.offer(30);
//        System.out.println(priorityQueue.poll());
//        System.out.println(priorityQueue.poll());
//        System.out.println(priorityQueue.poll());
//        System.out.println(priorityQueue.poll());
//        priorityQueue.add(100);
//        System.out.println(priorityQueue.poll());
//        priorityQueue.offer(66);
//        System.out.println(priorityQueue.peek());
//        System.out.println(priorityQueue.poll());
//        priorityQueue.offer(44);
////        priorityQueue.element();
//        priorityQueue.offer(456);
//        priorityQueue.offer(12);
//        Iterator<Integer> iterator=priorityQueue.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        People people1=new People("tom",1);
        People people2=new People("jan",3);
        People people3=new People("asdfa",2);
        People people4=new People("asd",2);

        Com com=new Com();
        PriorityQueue<People> priorityQueue1=new PriorityQueue(com);
        priorityQueue1.offer(people1);
        priorityQueue1.offer(people2);
        priorityQueue1.offer(people3);
        priorityQueue1.offer(people4);
        System.out.println(priorityQueue1.poll().getId());
        System.out.println(priorityQueue1.poll().getId());
        System.out.println(priorityQueue1.poll().getId());
        System.out.println(priorityQueue1.poll().getId());
//        Iterator<People> iterator=priorityQueue1.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().getId());
//        }
    }

 static class Com implements Comparator<People>{
    @Override
    public int compare(People o1, People o2) {
        if (o1.getId()>o2.getId()){
            return 1;
        }else if(o1.getId()==o2.getId()){
            return 0;
        }else {
            return -1;
        }
    }
}

}

