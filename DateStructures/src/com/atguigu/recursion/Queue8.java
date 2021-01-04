package com.atguigu.recursion;

import javax.swing.*;
import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-08-20 10:55
 */
public class Queue8 {
    //定义一个max表示共有多少个皇后
    static int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如arr = {0,4,7,5,2,6,1,3}
    static int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        check(0);
        System.out.println(count);
        System.out.println("判断次数：" + judgeCount);
    }

    private static void check(int n ){
        if (n == max){//当n等于8时打印
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n，放置到该行第i列，判断是否冲突
            array[n] = i;
            if (judeg(n)){//如果不冲突，接着放n+1个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行array[n] = i，即将第n个皇后，放置在本行的后移位置
        }
    }


    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    /**
     *
     * @param n 表示第n个皇后
     * @return
     */
    private static boolean judeg(int n){
        judgeCount ++;
        //先判断是否在同一列\同一条斜线
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n -i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }

        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private static void print(){
        for (int i = 0;i < array.length;i++){
            System.out.print(array[i] + " ");
        }
        count ++;
        System.out.println();
    }
}
