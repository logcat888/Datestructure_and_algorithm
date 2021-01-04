package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * @author chenhuiup
 * @create 2020-08-13 下午 5:00
 */
//演示栈Stack的基本使用
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        while (stack.size() > 0){
            System.out.println(stack.pop()); //pop就是将栈顶的数据取出
        }
    }
}
