package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-08-20 16:13
 * 冒泡排序时间复杂度为O(n^2)
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {1, 11, 12, 15, 16};
        //测试一下冒泡排序的速度O(n^2),给80000个数据测试
        //创建一个80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*100);
        }
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);//8444
        /*
        int temp;
        int count = 0;//统计排序次数
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                count++;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.printf("第%d趟排序为：", i + 1);
            System.out.println(Arrays.toString(arr));
        }
        System.out.printf("优化前排序次数为%d\n", count);
        System.out.println("**************************************************");
        count = 0;
        arr = new int[]{7, 11, 12, 15, 16};
        //冒泡排序优化
        boolean flag = false;//记录单趟排序是否发生元素移动，如果没有发生移动说明，该数组已经有序，无需继续排序下去
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                count++;
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.printf("第%d趟排序为：", i + 1);
            System.out.println(Arrays.toString(arr));
            if (!flag) {//数组有序，结束循环
                break;
            } else {
                flag = false;//重置flag,进行下次判断
            }
        }
        System.out.printf("优化后排序次数为%d\n", count);
*/
    }


    public static void bubbleSort(int[] arr){
        int temp;
        boolean flag = false;//记录单趟排序是否发生元素移动，如果没有发生移动说明，该数组已经有序，无需继续排序下去
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {//数组有序，结束循环
                break;
            } else {
                flag = false;//重置flag,进行下次判断
            }
        }
    }
}
