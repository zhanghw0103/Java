package com.tulun.src1;



import java.util.LinkedList;

public class Solution {

    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null && list2 != null) {
            return list2;
        } else if (list1 != null & list2 == null) {
            return list1;
        } else if (list1 == null && list2 == null) {
            return null;
        }
        ListNode x = null;//x是当前节点
        ListNode y = null;//y是x另一条链的节点
        ListNode temp = new ListNode(-1);
        ListNode first = null;
        if (list1.val <= list2.val) {
            x = list1;
            first= x;
            y = list2;
        } else {
            x = list2;
            first = x;
            y = list1;
        }
        while (x .next!= null) {
            if (y.val <= x.next.val) {
               temp = x;
                x.next=y;
                x = y;
                y = temp.next;
            } else {
                x = x.next;
            }
        }
        x.next = y;
        return first;
    }

    public static void main(String[] args) {
        ListNode l1 =new  ListNode(1);
        ListNode l3 =new  ListNode(3);
        ListNode l5 =new  ListNode(5);
        ListNode l2 =new  ListNode(2);
        ListNode l4 =new  ListNode(4);
        ListNode l6 =new  ListNode(6);
        l1.next = l3;
        l3.next = l5;
        l2.next = l4;
        l4.next = l6;
        Solution solution = new Solution();
        ListNode merge = solution.Merge(l1, l2);
        System.out.println(merge.val);

    }

}
