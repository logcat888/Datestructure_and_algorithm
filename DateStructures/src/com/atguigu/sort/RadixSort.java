package com.atguigu.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author chenhuiup
 * @create 2020-08-28 20:07
 * 基数排序：速度比快排还快，空间换时间，占用内存很大，有负数的数组，不用基数排序。
 * 1.基数排序是稳定排序法，不会改变相同元素在元数组中的位置
 * 2.基数排序的排序速度比快排还快，但是需要消耗大量内存，当数据量超过一定程度后会报  堆内存溢出错误
 * 3. 基数排序从个位数开始依次遍历数组，按照数值大小放入0-9个桶中，然后再从桶中取出元素，放回原数组
 * 4. 依次从十位数直至最大数的最高位，重复第三步，完成排序。
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
        // 80000000 * 11 * 4 /1024 /1024 /1024 = 3.3G  八千万个数据需要3.3G内存，可能会报OutOfMemoryError：java heap space
        //测试一下基数排序的速度O(n^2),给80000个数据测试
        //创建一个80000个的随机的数组
        int[] arr = new int[800000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        long start = System.currentTimeMillis();
        radixSort(arr);//27
        long end = System.currentTimeMillis();
        System.out.println(end - start);//12
//        System.out.println(Arrays.toString(arr));

    }

    //基数排序方法
    public static void radixSort(int[] arr) {
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一维数组，大小定为arr.length
        //3.基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际放了多少个数据，我们定义一个一维数组来记录各个桶的每次方法的数据个数
        int[] bucketElementCounts = new int[10];
        
        //得到数组中最大的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >max){
                max = arr[i];
            }
        }
        //获取最大数是几位数
        int maxLength = (max + "").length();

        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / (int)Math.pow(10,i)% 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            //按照这个桶的顺序（一维数组的下表依次取出数据，放入到原来数组）
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0){
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index ++;
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }

    }
}
