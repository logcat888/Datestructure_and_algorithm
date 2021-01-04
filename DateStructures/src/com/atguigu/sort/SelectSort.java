package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-08-20 17:20
 * 选择排序的时间复杂度O(n^2),趟次n - 1，尽管选择排序和冒泡排序的时间复杂度都是O(n^2)，但是选择排序交换次数少，所以时间花费比冒泡少
 * 1.令起始元素为最小值，记录最小值的索引
 * 2.由左向右遍历数组，不断刷新最小值索引，并记录下来
 * 3.将最小值与起始位置元素交换
 * 4.起始位置加1，重复1,2,3
 *
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {5,-9,10,-4,5,7};
        //测试一下选择排序的速度O(n^2),给80000个数据测试
        //创建一个80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*100);
        }
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);//2361
//        System.out.println(Arrays.toString(arr));
    }

    //无法优化 ，选择排序总趟次等于 arr.length - 1
    public static void selectSort(int[] arr){
        int index;//记录最小值的索引
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            index = i;
            for (int j = i + 1; j < arr.length; j++) {//遍历找到最先值，并将最小值的索引记录下
                if (arr[index] > arr[j]){
                    index = j;
                }
            }
            if (index != i){
                temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
    }

    //优化，当没有选出最小值时，即数组已经有序，选择排序无法优选
//    public static void select(int[] arr){
//        boolean flag = false;
//        int index;//记录最小值的索引
//        int temp;
//        for (int i = 0; i < arr.length - 1; i++) {
//            index = i;
//            for (int j = i; j < arr.length; j++) {//遍历找到最先值，并将最小值的索引记录下
//                if (arr[index] > arr[j]){
//                    flag = true;//记录是否需要移动数据
//                    index = j;
//                }
//            }
//            temp = arr[i];
//            arr[i] = arr[index];
//            arr[index] = temp;
//            if (!flag){//该趟排序没有发现最小值，说明数组已经有序
//                break;
//            }else {
//                flag = false;
//            }
//        }
//    }
}


