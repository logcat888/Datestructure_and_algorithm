package com.atguigu.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author chenhuiup
 * @create 2020-08-19 21:27
 */
public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //1.因为对str进行操作不方便，现将中缀表达式转化为list集合，方便遍历
        String expression = "5+3*9-8+(25+3)*5";
        List<String> toInfixExpression = toInfixExpression(expression);//[5, +, 3, *, 9, -, 8, +, (, 25, +, 3, ), *, 5]
        System.out.println(toInfixExpression);
        //2.将中缀表达式的List转化为后缀表达式对应的List
        List<String> tototot = parseSuffixExpressionList(toInfixExpression);
        System.out.println("后缀表达式：" + tototot);

        //3.根据后缀表达式求值计算
        System.out.println(calculate(tototot));
        /*
        //先定义一个逆波兰表达式
        //（3 + 4 ）* 5 - 6 => 3 4 + 5 * 6 -
        //说明为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路
        //1. 先将3 4 + 5 * 6 - 方法ArrayList中
        //2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("表达式是：" + list);
        int calculate = calculate(list);
        System.out.println("计算结果是：" + calculate);
        */


    }

    //讲一个逆波兰表达式，依次将数据和运算符 放到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割放到ArrayList
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack();
        for (String s : list) {
            if (s.matches("\\d+")) {
                stack.push(s);
            } else if (s.equals("+")) {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 + num2;
                stack.push(res + "");
            } else if (s.equals("-")) {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 - num2;
                stack.push(res + "");
            } else if (s.equals("*")) {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 * num2;
                stack.push(res + "");
            } else if (s.equals("/")) {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 / num2;
                stack.push(res + "");
            } else {
                throw new RuntimeException("操作符有误");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //将中缀表达式转化为ArrayList保存 5+3*9-8+(25+3)*5
    public static List<String> toInfixExpression(String expression) {
        ArrayList<String> list = new ArrayList<>();
        int index = 0;//字符串索引
        char ch;
        String value = "";//用于拼接
        do {
            ch = expression.substring(index, index + 1).charAt(0);
            if (ch < 48 || ch > 57) {//如果是符号就直接加入到list
                list.add(ch + "");
                index++;
            } else {
                while (ch >= 48 && ch <= 57) {
                    value += ch;
                    index++;
                    if (index == expression.length()) {
                        break;
                    }
                    ch = expression.substring(index, index + 1).charAt(0);
                }
                list.add(value);
                value = "";
            }
        } while (index < expression.length());
        return list;
    }

    //将中缀表达式的List转化为后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> InfixExpression) {
        Stack<String> allStack = new Stack<>();//存放数字及符号以及最终的后缀表达式
        Stack<String> operStack = new Stack<>();//存放操作符，并适时取出放入allStack
        char ch = ' ';//用于取出字符串的第一个字符，目的是判断是数字还是操作符
        ArrayList<String> list = new ArrayList<>();//用于接收后缀表达式集合
        for (String str : InfixExpression) {
            ch = str.charAt(0);
            if (ch >= 48 && ch <= 57) {//如果是数字直接入allStack
                allStack.push(str);
            } else {//判断操作符的优先级及是否是左右括号
                if (str.equals("(")) {//如果是左括号直接入栈
                    operStack.push(str);
                } else if (str.equals(")")) {//如果是右括号，取出operStack中所有的符号，直到遇到左括号，并放入allStack，但是括号不放入其中
                    while (!operStack.peek().equals("(")) {//直到弹出左括号，停止循环
//                    while (pop != "(") {//直到弹出左括号，停止循环
                        allStack.push(operStack.pop());
                    }
                    operStack.pop();//消除左括号
                } else {//判断操作符的优先级
//                    if (operStack.isEmpty()) {//如果栈空，直接入栈
//                        operStack.push(str);
//                    } else {//判断当前符号与符号栈栈顶的符号的优先级，如果当前符号的优先级高入operStack，否则弹出栈顶进入allStack，再次进行判断是否大于栈顶优先级
//                        operToStack(str,operStack,allStack);
//                    }
                    while (!operStack.isEmpty() && operPriority(operStack.peek()) >= operPriority(str)){
                        allStack.push(operStack.pop());
                    }
                    operStack.push(str);
                }
            }
        }
        //将operStack中的符号转移到allStack中，此时operStack中肯定没有括号
            while(!operStack.empty()){//operStack栈中有符号才进行转移操作
                allStack.push(operStack.pop());
            }
        while (!allStack.empty()){//将allStack栈中的元素取出放入list集合中
            list.add(allStack.pop());
        }
        Collections.reverse(list);//反转list数组
        return list;
    }

    //符号的优先级，*+返回1，-+返回0
    public static int operPriority(String oper) {
        if (oper == "/" || oper == "*") {
            return 1;
        }
        return 0;
    }

    //用于将符号压栈。判断优先级
    public static void operToStack(String oper,Stack<String> operStack,Stack<String> allStack){
        if (operStack.isEmpty() || operStack.peek() == "("){//如果栈空直接入栈
            operStack.push(oper);
            return;
        }
        if (operPriority(oper) > operPriority(operStack.peek())) {//直到当前运算符入栈，结束方法
            operStack.push(oper);
            return;
        } else {
            allStack.push(operStack.pop());
            operToStack(oper,operStack,allStack);
        }
    }
}
