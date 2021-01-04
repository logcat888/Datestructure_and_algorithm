package com.leetcode.test02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author chenhuiup
 * @create 2021-01-03 14:44
 */
/*
 a = 1 3 3 2 2 2 1  3
     1  1 3
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = {1,3,3,2,2,2,1,3};
        method(arr);
    }

    public static void method(int[] a){
        Stack<Integer> stack = new Stack<>();
        int flag = Integer.MAX_VALUE;
        for (int i : a) {
            if (stack.isEmpty()){
                stack.push(i);
            }else {
                if (flag == i){
                    if (stack.peek() == i){
                        stack.pop();
                    }
                }else{
                    stack.push(i);
                }
                flag = i;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        Object[] objects = list.toArray();
        for (int i = objects.length - 1; i >= 0 ; i--) {
            System.out.println((Integer) objects[i]);
        }
    }
}
