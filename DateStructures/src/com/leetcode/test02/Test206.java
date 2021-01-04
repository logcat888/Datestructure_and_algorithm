package com.leetcode.test02;

import java.util.Stack;

/**
 * @author chenhuiup
 * @create 2020-11-18 13:38
 */
/*
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

 */
public class Test206 {
    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        first.next = two;
        two.next = three;
        first.order();
        System.out.println("反转");
        ListNode reverseList = reverseList(first);
        reverseList.order();

    }

    //使用栈反转
    public static ListNode reverseList(ListNode head) {
        Stack<ListNode> nodeStack = new Stack<>();
        nodeStack.push(head);
        if (head != null) {
            ListNode next = head.next;
            while (next != null) {
                nodeStack.push(next);
                next = next.next;
            }
        } else {
            return null;
        }
        ListNode fisrt = nodeStack.pop();
        ListNode cur = fisrt;
        try {
            while (nodeStack.peek() != null) {
                cur.next = nodeStack.pop();
                cur = cur.next;
            }
        } catch (Exception e) {
            System.out.println();
        }
        cur.next = null;
        return fisrt;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    //遍历链表
    public void order(){
        ListNode cur = this;
        System.out.print(this);
        while (cur.next != null){
            System.out.print(cur.next);
            cur = cur.next;
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return val + " -> ";
    }
}
/*
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return next;
 */