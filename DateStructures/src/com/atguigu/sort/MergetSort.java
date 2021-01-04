package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-08-28 18:05
 * 归并排序：分治思想,速度和快排差不多
 * 1.归并排序需要一个额外空间
 * 2.有n个数，一共合并n -1 次，如果是冒泡排序O(n ^ 2);
 * 3.分的过程是将数据分到栈中（数组并未改变），方便栈顶时调用合并方法（治）
 *
 * 治的过程：
 * 1.将两个有序表依次填充到temp数组，直到一边填充完毕
 * 2.将剩余一边的数组继续填充到temp数组
 * 3.将temp数组复制到原数组
 */
public class MergetSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);//归并排序需要一个额外空间

        //测试一下快速排序的速度O(n^2),给80000个数据测试
        //创建一个80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        long end = System.currentTimeMillis();
        System.out.println(end - start);//12
//        System.out.println(Arrays.toString(arr));
    }

    //分 + 合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);

        }
    }

    //合并的方法

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i,左边有序序列的初始索引
        int j = mid + 1;//初始化j,右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引

        //第一步：先把左右两边的数组按照规则填充到temp数组，直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素，填充到temp数组
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                i++;
                t++;
            } else {//反之，填充右边
                temp[t] = arr[j];
                j++;
                t++;
            }
        }
        //第二步：把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) {//左边有剩余
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {//右边有剩余
            temp[t] = arr[j];
            t++;
            j++;
        }

        //第三步：将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft++;
        }

    }


}
