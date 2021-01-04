package com.atguigualgorithm.kmp;

/**
 * @author chenhuiup
 * @create 2020-10-11 17:13
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        System.out.println(violenceMatch(str1, str2));
    }

    //暴力匹配算法实现
    public static int violenceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int s1Index;
        int s2Index = 0;
        for (int i = 0; i < s1Len; i++) {
            s1Index = i;
            while (s1Index < s1Len && s2Index < s2Len && s1[s1Index] == s2[s2Index]){
                if (s2Index == s2Len - 1){
                    return s1Index - s2Index;
                }
                s1Index++;
                s2Index++;
            }
            s2Index = 0;
        }
        return -1;

    }
}
