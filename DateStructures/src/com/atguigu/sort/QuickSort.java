package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-08-27 23:26
 *
 * QuickSort排序最快
 *
 * 思想：
 * 1.选取中间索引为中轴值，左索引（初始值为最小值）向右遍历寻找大于中轴值的索引，右索引（初始值为最大索引）向左遍历选择小于中轴值的索引，交换。
 * 2.直到left索引>right索引，终止循环
 * 3.递归向左排序，递归向右排序
 *
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {5, -9, 4, -4, 9, 7};
        //测试一下快速排序的速度O(n^2),给80000个数据测试
        //创建一个80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*800000);
        }
        long start = System.currentTimeMillis();
        quickSort(arr,0,arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);//14
//        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left, int right){
        int l = left;//左下标
        int r = right;//右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp;//临时变量，作为交换时使用
        //while 循环的目的是让pivot值小放到左边，比pivot值大放到右边
        while (l < r){
            //在pivot的左边一直找，找到大于等于pivot值，才退出
            while (arr[l] < pivot){
                l ++;
            }
            //在pivot的右边一直找，找到小于等于pivot值，才退出
            while (arr[r] > pivot){
                r--;
            }

            //如果 l >= r 说明pivot的左右两边的值，已经按照左边全部是小于等于pivot的值，右边全部是大于等于pivot的值
            if (l >= r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值 相等 r--，前移
            if (arr[l] == pivot){
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++，前移
            if (arr[r] == pivot){
                l++;
            }
        }

        //如果 l == r ，必须l++,r-- ，否则出现栈溢出
        if (l == r){
            l++;
            r--;
        }
        //向左递归
        if (left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if (right > l){
            quickSort(arr,l,right);
        }
//        if (left < l){
//            quickSort(arr,left,l);
//        }else {
//            return;
//        }
//        if (r < right){
//            quickSort(arr,r,right);
//        }else {
//            return;
//        }

    }
}
