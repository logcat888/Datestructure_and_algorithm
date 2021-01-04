package com.atguigualgorithm.kmp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-10-11 19:23
 */
/*
KMP算法总结：
1.先得到子串的部分匹配表
2.使用部分匹配表完成KMP匹配

// 需要处理str1.charAt(i) != str2.charAt(j)，去调整j的大小
            //KMP算法核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] ints = kmpNext("AAAB");
        System.out.println(Arrays.toString(ints));
        System.out.println(kmpSearch(str1, str2, kmpNext(str2)));
    }


    /**
     * //写出我们的kmp搜素算法
     *
     * @param str1 源字符串
     * @param str2 子串
     * @param next next 部分匹配表，是子串对应的部分匹配表
     * @return 如果是-1就是没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 需要处理str1.charAt(i) != str2.charAt(j)，去调整j的大小
            //KMP算法核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }

        }
        return -1;
    }

    //获取到一个字符串（子串）的部分匹配值表,利用了KMP算法的next表，不会重复获取前缀和后缀。
    public static int[] kmpNext(String dest) {
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串是长度为1部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j),我们需要从next[j-1]获取新的j
            //直到我们发现有dest.charAt(i) == dest.charAt(j)成立才退出,相当于回溯
            // 这是kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            //当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是 +1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }


     // 笨方法
//    //获取到一个字符串（子串）的部分匹配值表
//    public static int[] kmpNext(String str){
//        int[] arr = new int[str.length()];
//        for (int i = 1; i <= str.length(); i++) {
//            arr[i-1] = getValue(str.substring(0,i));
//        }
//        return arr;
//    }
//    // 获取单个字符串的部分匹配值
//    public static int getValue(String str){
//        if (str.length() <= 1){
//            return 0;
//        }
//        //获取字符串的前缀
//        ArrayList<String> pre = new ArrayList<>();
//        //获取字符串的后缀
//        ArrayList<String> hou = new ArrayList<>();
//        for (int i = 1; i < str.length(); i++) {
//            pre.add(str.substring(0,i));
//            hou.add(str.substring(i));
//        }
//        for (int i = hou.size() - 1; i >= 0; i--) {
//            String s = hou.get(i);
//            if (pre.contains(s)){
//                return s.length();
//            }
//        }
//        return 0;
//    }
}
