package com.atguigualgorithm.dac;

/**
 * @author chenhuiup
 * @create 2020-10-11 11:19
 */
/*
分治算法（DAC）：（Divide-and-Conquer（P））算法实现汉罗塔移动问题
动态规划算法：01背包，完全背包
1.将复杂问题简单化，通过分解后的子问题获取最优解。
2.与分治算法不同的是，动态规划算法分解后的子问题不是相互独立，而是下一阶段的求解基于上一阶段的答案
3.动态规划可以通过填表的方式来逐步推进，得到最优解
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoitower(3, 'a', 'b', 'c');
    }

    //使用分治算法实现汉罗塔移动

    /**
     * @param num 代表汉罗塔的层数
     * @param a   代表柱子a
     * @param b   代表柱子b
     * @param c   代表柱子c
     */
    public static void hanoitower(int num, char a, char b, char c) {
        if (num <= 0) {
            System.out.println("没有盘无法移动");
        }
        if (num == 1) { //只有一个盘，将盘从柱子a移动到柱子c
            System.out.println("第1个盘从 " + a + " -> " + c);
        } else {
            //如果我们有n >= 2情况，我们总是可以看做是两个盘。最下面一个盘，上面所有的盘为另一个盘
            // 1. 先把最上面的所有盘a -> b，移动过程中会使用到c
            hanoitower(num - 1, a, c, b);
            // 2. 把最下边的盘 a -> c
            System.out.println("第" + num + "个盘从 " + a + " -> "  + c);
            // 3. 把B塔的所有盘从b移动到c，移动过程中会使用到a
            hanoitower(num - 1, b, a, c);
        }
    }

}
