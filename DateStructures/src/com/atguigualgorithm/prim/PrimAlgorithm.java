package com.atguigualgorithm.prim;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-10-12 19:46
 */
/*
普利姆算法(prim)实现最小生成树（Minimum Cost Spanning Tree ，MST）问题：解决修路问题
1. 最小生成树：n个顶点，有n-1条边，这n-1条边的权值和最小
2. 初始化：给定一个顶点，找出与其相邻的未访问的顶点最小的边，将其标记为已访问；
3. 重复：对已访问的顶点，找出与他们相邻的未访问的顶点最小的边，将其标记为已访问。
4. 重复第3步，直至所有节点被访问，生成n-1条边。
5. 类似于贪心算法,每次只找最优解。
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建ok
        char[] data = {'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大叔，表示两个点不连通。
        int[][] weight = {{10000,5,7,10000,10000,10000,2},
                        {5,10000,10000,9,10000,10000,3},
                        {7,10000,10000,10000,8,10000,10000},
                        {10000,9,10000,10000,10000,4,10000},
                        {10000,10000,8,10000,10000,5,4},
                        {10000,10000,10000,4,5,10000,6},
                        {2,3,10000,10000,4,6,10000}};

        //创建MGraph对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,verxs,data,weight);

        //输出
        minTree.showGraph(graph);

        //测试普利姆算法，输出最小生成树
        minTree.prim(graph,0);

    }
}

//创建最小生成树 -> 村庄的图
class MinTree{
    //创建图的邻接矩阵
    /**
     *
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verxs,char[] data,int[][] weight){
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 编写prim算法，得到最小生成树
    /**
     *
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成'A' -> 0  'B' -> 1 ......
     */
    public void prim(MGraph graph,int v){
        //visited[] 标记节点（顶点）是否被访问过
        int[] visited = new int[graph.verxs];

        //把当前这个节点标记为已访问
        visited[v] = 1;
        //h1 和 h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        for (int k = 1; k < graph.verxs; k++) { // 因为有graph.verxs个顶点，普利姆算法结束后，有graph.verxs-1边
            int minWeight = 10000; //将minWeight 初始化成一个大数，后面在遍历过程中，会被替换
            //这个是确定每一次生成的子图，和哪个节点的距离最近
            for (int i = 0; i < graph.verxs; i++) { // i节点表示被访问过的节点
                for (int j = 0; j < graph.verxs; j++) { // j节点表示还没有访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        // 替换minWeight（寻找已经访问过的节点和未访问过的节点间的权值最小的边）
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //将当前这个节点标记为已经访问
            visited[h2] = 1;
            System.out.println("边< " + graph.data[h1] + "," + graph.data[h2] + "> 权值为：" + graph.weight[h1][h2]);

        }

    }

}

class MGraph{
    int verxs; // 表示图的节点个数
    char[] data; // 存放节点数据
    int[][] weight; // 存放边，就是我们的邻接矩阵

    public MGraph(int verxs){
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
