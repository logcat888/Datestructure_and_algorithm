package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-08-20 19:26
 * 插入排序的时间复杂度为，趟次n -1 ，在本电脑的时间450，比选择排序快，但不同的电脑不同，有的电脑选择排序快
 * 1.将数组分为有序表和无序表
 * 2.每次选出无序表的第一个元素，称为待插入元素，保存该待插入元素
 * 3.从右向左依次与有序表元素比较，如果待插元素小于有序表中的元素，将有序表中的元素后移
 * 4.最后将插元素插入到最后一次比较元素的索引的上一个位置
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {5,-9,10,-4,5,7};
        //测试一下选择排序的速度O(n^2),给80000个数据测试
        //创建一个80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);//我的方法1257 ，正确方法 450
//        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        int insertVal;//待插入的值
        int insertIndex;//待插入的索引
        for (int i = 1; i < arr.length; i++) {
           insertVal = arr[i];
           insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }

    //**************自己编写**************
    public static void insertSort0(int[] arr) {
        int temp;
        for (int i = 1; i < arr.length; i++) {
            l:
            for (int j = 0; j < i; j++) {//遍历有序表，寻找比无序表第一个元素大的位置
                if (arr[j] > arr[i]) {//将无序表的第一个元素arr[i]插入索引j之前，其余元素后移
                    for (int k = i; k > j; k--) {
                        temp = arr[k];
                        arr[k] = arr[k - 1];
                        arr[k - 1] = temp;
                    }
                    break l;//移动元素后跳出当前循环
                }
            }
        }
    }
}


