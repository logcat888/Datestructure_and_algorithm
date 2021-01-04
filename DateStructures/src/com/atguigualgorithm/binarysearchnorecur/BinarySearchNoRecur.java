package com.atguigualgorithm.binarysearchnorecur;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-10-11 10:28
 */
/*
二分查找的非递归实现

 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 6, 7, 8, 9};
        int res = binarySearch(arr, 4);
        System.out.println(res);
    }

    //二分查找的非递归实现

    /**
     * @param arr    待查找的数组,arr是升序排序
     * @param target 需要查找的数
     * @return 返回对应的下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) { //说明继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) { //向左查找
                right = mid - 1;
            } else { //向右查找
                left = mid + 1;
            }
        }
        return -1;


//        if (arr == null) {
//            return -1;
//        }
//        int index = arr.length / 2;
//        while (index < arr.length) {
//            if (index == 0 || index == arr.length - 1) {
//                if (arr[index] == target) {
//                    return index;
//                } else {
//                    return -1;
//                }
//            }
//            if (arr[index] == target) {
//                return index;
//            } else if (arr[index] > target) { //向左查找
//                index = index / 2;
//            } else {
//                index = (index + 1 + arr.length) / 2;
//            }
//        }
//        return -1;
    }
}
