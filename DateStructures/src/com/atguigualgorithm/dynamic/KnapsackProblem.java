package com.atguigualgorithm.dynamic;

import java.util.ArrayList;

/**
 * @author chenhuiup
 * @create 2020-10-11 15:12
 */
/*
动态规划算法：01背包，完全背包
1.将复杂问题简单化，通过分解后的子问题获取最优解。
2.与分治算法不同的是，动态规划算法分解后的子问题不是相互独立，而是下一阶段的求解基于上一阶段的答案
3.动态规划可以通过填表的方式来逐步推进，得到最优解
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        goodsList.add(new Goods(1, 1500));
        goodsList.add(new Goods(4, 3000));
        goodsList.add(new Goods(3, 2000));
        Knapsack knapsack = new Knapsack(4, goodsList);
        int maxValue = knapsack.maxValue();
        System.out.println(maxValue);


        int[] w = {1, 4, 3}; //物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值
        int m = 4; //背包的容量
        int n = val.length; // 物品的个数

        //创建二维数组
        //v[i][j]  表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //为了记录放入商品的情况，我们定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        String[][] myPath = new String[n + 1][m + 1];

        //初始化第一行和第一列，这里在本程序中，可以不去处理，因为默认就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //根据前面得到公式来动态规划处理
        for (int i = 1; i < v.length; i++) { // 不处理第一行
            for (int j = 1; j < v[i].length; j++) { //不处理第一列
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                    myPath[i][j] = myPath[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j],val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放到背包的情况，我们不能直接的使用上面的公式，需要使用if-else来体现公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                        myPath[i][j] = myPath[i - 1][j - w[i - 1]] + " -> " + i;
                    } else {
                        v[i][j] = v[i - 1][j];
                        myPath[i][j] = myPath[i - 1][j];
                    }

                }
            }
        }

        //输出一下v 看看目前的情况
        for (int[] ints : v) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

        //输出最后我们是放入的哪些商品
        //遍历path，这样输出会把所有的放入情况都得到，其实我们只需要最后的放入
//        for (int[] ints : path) {
//            for (int anInt : ints) {
//                System.out.print(anInt + "\t");
//            }
//            System.out.println();
//        }

        //动脑筋
        int i = path.length - 1; // 行的最大下标
        int j = path[0].length - 1; // 列的最大下标
        while (i > 0){ //从path的最后开始找
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i-1]; //调整背包容量
            }
            i--;
        }


        //我的path路径
        String resPath = myPath[myPath.length - 1][myPath[0].length - 1];
        System.out.println(resPath.substring(7));

    }
}

//定义一个背包类
class Knapsack {
    int capacity; //背包容量
    ArrayList<Goods> goodsList = new ArrayList<Goods>(); // 商品列表,第一个元素为空
    int[][] goodVaule;

    public Knapsack(int capacity, ArrayList<Goods> goodsList) {
        this.capacity = capacity;
        this.goodsList.add(new Goods(0, 0));
        this.goodsList.addAll(goodsList);
        goodVaule = new int[this.goodsList.size()][capacity + 1];
    }

    //计算背包最优解
    public int maxValue() {
        for (int i = 1; i < goodVaule.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                Goods goods = goodsList.get(i);
                if (goods.weight > j) { // 如果加入商品的重量大于背包容量，选择上一件商品的最优解
                    goodVaule[i][j] = goodVaule[i - 1][j];
                } else { //如果加入商品的重量小于背包的容量，判断max(上一件商品的最优解，本件商品的价格 + 剩一件商品在剩余容量时的最优解)
                    goodVaule[i][j] = Math.max(goodVaule[i - 1][j], goods.value + goodVaule[i - 1][j - goods.weight]);
                }
            }
        }
        return goodVaule[goodVaule.length - 1][capacity];
    }


}

//定义一个商品类
class Goods {
    int weight;
    int value;

    public Goods(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}
