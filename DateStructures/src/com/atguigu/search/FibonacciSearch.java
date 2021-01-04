package com.atguigu.search;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-08-30 21:21
 *
 * 斐波那契查找法（又称黄金查找法），根据黄金分割点，确定mid值：
 *  1.由于斐波那契数列的前一项比后一项的值，接近于黄金分割点，故可以使用斐波那契数列确定key
 *    f[k] = f[k-1] + f[k-2]  => f[k] - 1  = (f[k-1] - 1 ) + (f[k-2] -1) + 1;
 *    故 mid = low + f[k-1] - 1
 *  2.通过循环，不断的刷新k的值，直到找到值 或者 low > high 跳出循环
 *
 *  注意事项，必须是有序数组
 */
public class FibonacciSearch {
    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {2, 6, 8, 15, 99, 100, 155};
        int i = fibSearch(arr, 100);
        System.out.println(i);
    }

    public static int[] fib() {
        int[] arr = new int[maxSize];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid的值
        int[] fib = fib();//获取斐波那契数列
        //获取斐波那契分割值的下标
        while (high >= fib[k]) {
            k++;
        }
        //因为f[k]值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分使用0填充
        int[] temp = Arrays.copyOf(arr, fib[k]);

        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        //使用while来循环处理，找到key
        while (low <= high) {//只有满足就可以找
            mid = low + fib[k - 1] - 1;
            if (key < temp[mid]) {//向左查找，更新key
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {//向右查找，更新
                low = mid + 1;
                k -= 2;
            } else {//找到
                if (mid >= arr.length) {
                    return arr.length - 1;
                } else {
                    return mid;
                }
            }
        }

        return -1;
    }


}
