package com.atguigualgorithm.floyd;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-10-15 9:00
 */
/*
弗洛伊德算法（Floyd）：解决图中每一个顶点到其他顶点的最短距离，效率偏低，时间复杂度为O(n3)
1.以所有顶点为中间顶点k，遍历其他顶点i经过中间顶点到其他顶点j的距离，根据
    ik + jk < ij，判断是否需要更新dis集合和前驱关系，前驱关系为中间顶点（因为是无向图，中间顶点可以到达顶点或终点）
2. 可以通过前驱关系获取出发顶点到终点的路径。

 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建成功
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = Character.MAX_VALUE;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        //创建Graph对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.show();
        //测试弗洛伊德算法
        System.out.println("弗洛伊德算法：");
        graph.floyd();
        graph.show();

    }
}

//创建图
class Graph {
    private char[] vertex; //存放顶点的数组
    private int[][] dis; //保存，从各个顶点出发到其他顶点的距离，最后的结果，也是保留在该数组
    private int[][] pre; //保存到达目标顶点的前驱顶点

    /**
     * 功能：构造器
     *
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组初始化，注意存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    /**
     * 显示图
     */
    public void show() {
        for (int i = 0; i < dis.length; i++) {
            //现将pre数组输出一行
            System.out.println("=================pre================");
            System.out.print(vertex[i] + " : ");
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            System.out.println("=================dis================");
            //输出dis数组的一行数据
            System.out.print(vertex[i] + " : ");
            for (int j = 0; j < dis.length; j++) {
                System.out.print("(" + vertex[i] + "到" + vertex[j] + "的最短路径是:" + dis[i][j] + ")" + " ");
            }
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd() {
        int len = 0;//变量保存距离
        // 对中间顶点遍历，i就是中间顶点的下标 {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < vertex.length; i++) { //遍历 i 为中间点
            //从j顶点开始出发{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
            for (int j = 0; j < vertex.length; j++) { // j 为出发顶点
                for (int k = 0; k < vertex.length; k++) { // k 为终点
                    len = dis[j][i] + dis[i][k]; //从j顶点出发，经过i中间顶点，到达k顶点的距离
                    int jk = dis[j][k];
                    if (len < jk){
                        dis[j][k] = len; //更新dis矩阵
                        dis[k][j] = len; //更新dis矩阵
                        pre[j][k] = pre[i][k]; //更新前驱关系
//                        pre[j][k] = i; //更新前驱关系 也行
                        pre[k][j] = pre[i][k]; //更新前驱关系
                    }
                }
            }
        }
    }

}
