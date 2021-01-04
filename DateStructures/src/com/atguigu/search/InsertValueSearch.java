package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 插值查找算法 ： 要求数组有序
 * 插值索引：int mid = low + (high - low)*(key - arr[low])/(arr[high] - arr[low])  // 自适应写法
 *
 * //注意：
 * 1.key < arr[0] || key > arr[arr.length -1]  必须要，否则会越界！！！
 * 2.对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找，速度较快
 * 3.关键字分布不均匀的情况下，该方法不一定比折半查找要好
 * @author chenhuiup
 * @create 2020-08-30 20:24
 */
public class InsertValueSearch {
    private static int count;
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int i = insertValueSearch2(arr, 3, 0, arr.length - 1);
        System.out.println(i);

//        List<Integer> list = insertValueSearch(arr, 3, 0, arr.length - 1);
//
//        System.out.println(list);
        System.out.println(count);


    }

    //编写插值查找算法
    public static List<Integer> insertValueSearch(int[] arr, int key, int low, int high){
        count++;
        //注意：key < arr[0] || key > arr[arr.length -1]  必须要，否则会越界！！！
        if (low > high || key < arr[0] || key > arr[arr.length -1]){
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        int mid = low + (high - low)*(key - arr[low])/(arr[high] - arr[low]);
        int temp = arr[mid];
        if (key > temp){//向右查找
            return insertValueSearch(arr,key,mid + 1 ,high);
        }else if (key < temp){//向左查找
            return insertValueSearch(arr,key,mid - 1 ,high);
        }else {
            list.add(mid);
            //向左遍历是否有相同的
            int temp1 = mid - 1;
            while (temp1 >= 0){
                if (temp == arr[temp1]){
                    list.add(temp1);
                    temp1--;
                }else {
                    break;
                }
            }
            //向右遍历是否有相同的
            temp1 = mid +1;
            while (temp1 <= high){
                if (temp == arr[temp1]){
                    list.add(temp1);
                    temp1--;
                }else {
                    break;
                }
            }
            return list;
        }
    }

    public static int insertValueSearch2(int[] arr, int key, int low, int high){
        count++;
        if (low > high || key < arr[0] || key > arr[arr.length -1]){
            return -1;
        }
        int mid = low + (high - low)*(key - arr[low])/(arr[high] - arr[low]);
        int temp = arr[mid];
        if (key > temp){//向右查找
            return insertValueSearch2(arr,key,mid + 1 ,high);
        }else if (key < temp){//向左查找
            return insertValueSearch2(arr,key,mid - 1 ,high);
        }else {
            return mid;
        }
    }
}
