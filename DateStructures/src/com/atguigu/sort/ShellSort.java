package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-08-20 20:51
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {5, -9, 4, -4, 9, 7};
        //测试一下希尔排序的速度O(n^2),给80000个数据测试
        //创建一个80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*800000);
        }
        long start = System.currentTimeMillis();
        Arrays.sort(arr);//20
//        shellSort(arr);//6360
//        shellSort2(arr);//18
        long end = System.currentTimeMillis();
        System.out.println(end - start);//1
//        System.out.println(Arrays.toString(arr));
    }

    //希尔排序交换法（插入排序的升级，缩小增量法），发现元素小后，就交换，效率低
    //希尔排序时，对有序序列在插入时采用交换法
    public static void shellSort(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共gap组，每组有arr.length / gap个元素，步长为gap)
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }

            }
        }
    }

    //对交换式的希尔排序进行优化->移位法
    public static void shellSort2(int[] arr){

        //增量gap，并逐步缩小增量
        for (int gap = arr.length /2;gap > 0 ;gap /= 2){
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                if (arr[j] < arr[j -gap]){
                    while (j -gap >= 0 && temp < arr[j - gap]){
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }

    //************自己编写********************
    public static void shellSort1(int[] arr) {
        int temp;
        int foot = arr.length / 2;//定义步长
        for (int i = foot; i < arr.length; i++) { //将数据分组
            for (int j = i - foot; j >= 0; j -= foot) {
                if (arr[j] > arr[j + foot]) {
                    temp = arr[j];
                    arr[j] = arr[j + foot];
                    arr[j + foot] = temp;
                }
            }
            foot /= 2;
            if (foot == 0) {
                break;
            }
        }
    }
}
