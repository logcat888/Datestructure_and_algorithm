package com.atguigu.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chenhuiup
 * @create 2020-08-28 22:52
 * //注意：二分查找的前体是，该数组有序
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {5, 8, 9, 11, 11, 11, 11, 11,36,36,36, 42};
        System.out.println(binarySearch(arr, 11, 0, arr.length - 1));
        List<Integer> list1 = binarySearch2(arr, 99, 0, arr.length - 1);
        Collections.sort(list1);
        System.out.println(list1);
    }

    public static int binarySearch(int[] arr, int findValue, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (findValue == arr[mid]) {
            return mid;
        } else if (findValue > arr[mid]) {//向右递归
            return binarySearch(arr, findValue, mid + 1, right);
        } else {
            return binarySearch(arr, findValue, left, mid - 1);
        }
    }

    private static ArrayList<Integer> list = new ArrayList<>();

    //当数组有多个相同值时，返回所有数值
    public static List<Integer> binarySearch2(int[] arr, int findValue, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        if (findValue == arr[mid]) {
            list.add(mid);
            int temp1 = mid -1 ;
            int temp2 = mid + 1;
            while (temp1 >= 0 && findValue == arr[temp1]){
                list.add(temp1);
                temp1 --;
            }
            while (temp2 <= right && findValue == arr[temp2]){
                list.add(temp2);
                temp2++;
            }
        } else if (findValue > arr[mid]) {//向右递归
            binarySearch2(arr, findValue, mid + 1, right) ;
        } else {
            binarySearch2(arr, findValue, left, mid - 1);

        }
        return list;
    }


}
