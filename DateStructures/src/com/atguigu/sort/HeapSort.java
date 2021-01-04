package com.atguigu.sort;

/**
 * @author chenhuiup
 * @create 2020-09-03 22:14
 *
 * 堆排序的时间复杂度为O(nlogn)
 *
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
//        int[] arr = {4, 6, 8, 5, 9};
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));

        //测试一下堆排序的速度O(n^2),给8000000个数据测试
        //创建一个80000个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*80000000);
        }
        long start = System.currentTimeMillis();
//        Arrays.sort(arr);//559
        heapSort(arr);//61
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {
        System.out.println("堆排序！！");
        //先根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //将大顶堆的堆顶元素下 沉到 数组末尾
        int temp = 0;
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    //将一个数组（二叉树），调整成一个大顶堆

    /**
     * 功能：完成将以 i 对应的非叶子节点的树调整成大顶堆
     * 举例  int[] arr = {4,6,8,5,9}; => i = 1 => adjustHeap => 得到{4,9,8,5,6}
     * 如果我们再次调用 adjustHeap  传入的是 i = 0 => 得到{4,9,8,5,6} => {9,6,8,5,4}
     *
     * @param arr    arr 待调整的数组
     * @param i      表示非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整，length 是在逐渐的减少
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        //1. k = i +2 + 1   k是i节点的子节点
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明左子节点的值小于右子节点的值
                k++;//指向右节点
            }
            if (arr[i] < arr[k]) {//如果子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;//i指向k，继续循环比较
            } else {
                break;
            }
        }

        //当for循环结束后，我们已经将以i  为父节点的树的最大值，放在了最顶（局部）
        arr[i] = temp;
    }
}
