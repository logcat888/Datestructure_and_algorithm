package com.atguigualgorithm.kruskal;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-10-12 22:20
 */
/*
克鲁斯卡尔（kruskal）解决公交站问题：也是贪心算法的体现，每次只求最优解
1.将所有边进行排序，选择最小的边加入图，且不能构成回路，直至所有的边不能加入，即完成最小生成树的构建。

 */
public class KruskalCase {

    private static int edgeNum; // 边的个数
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵
    //使用 INF这个常量表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        //创建KruskalCase 对象实例
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);

        //输出构建的
        kruskalCase.print();
        System.out.println(Arrays.toString(kruskalCase.getEdges()));
        EData[] edges = kruskalCase.getEdges();
        kruskalCase.sortEdges(edges);
        System.out.println( Arrays.toString(edges));

        System.out.println("====================");
        //使用克鲁斯科尔算法
        kruskalCase.kruskal();

        System.out.println(edgeNum); //24


    }

    public KruskalCase(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertexs.length;

        //初始化顶点,复制拷贝的方式
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化边，使用的是复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                if (matrix[i][j] != INF && i != j) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal(){
        int index = 0; //表示最后结果数组的索引
        int[] ends = new int[edgeNum]; //用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] res = new EData[edgeNum];

        //获取图中 所有的边的集合，一共有12条变
        EData[] edges = getEdges();

        //按照边的权值大小进行排序（从小到大）
        sortEdges(edges);

        //遍历edges数组，将边添加到最小生成树中，判断是否准备加入的边形成了回路，如果没有就加入res，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点（起点）
            int p1 = getPosition(edges[i].start); //p1 = 4
            //获取到第i条边的第2个顶点
            int p2 = getPosition(edges[i].end); // p2 = 5

            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1); //m = 4
            //获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2); // n = 5

            //是否构成回路
            if (m != n ){ //没有构成回路
                ends[m] = n; //设置m 在“已有最小生成树”中的终点<E,F> [0,0,0,0,5,0,0,0,0,0,0,0,]，将E的终点更新为F
//                ends[n] = n; //不需要设置F的终点为5，因为可以通过genEnd方法返回终点下标
                res[index++] = edges[i]; //有一条边加入到res数组
            }
        }

        //统计并打印"最小生成树"，输出res
        System.out.println("最小生成树为:" );
        for (EData re : res) {
            if (re != null){
                System.out.println(re);
            }
        }
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为： \n");
        for (int[] line : matrix) {
            for (int link : line) {
                System.out.printf("%15d", link); //%15d表示占15位
            }
            System.out.println();
        }
    }

    /**
     * 功能：对边进行排序处理
     *
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) { //交换
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * @param ch  顶点的值，比如 ‘A’ ，‘B’
     * @return 返回ch顶点对应的下标，如果找不到，返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 功能：获取图中边，放到EData[] 数组找那个，后面我们需要遍历该数组
     * 是通过matrix 邻接矩阵来获取
     *  EData[] 形式[['A','B',12],['A','F',8],.......]
     * @return
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                if (matrix[i][j] != INF && i != j){
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能：获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends  数组就是记录了各个顶点对应的终点是哪个，ends 数组是在遍历过程中逐步形成
     * @param i  表示传入的顶点对应的下标
     * @return 返回的就是 下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}


//创建一个类EData，它的对象实例就表示一条边
class EData {
    char start; // 边的一个点
    char end; //边的另外一个点
    int weight; //边的权值

    //构造器
    public EData(char start, char end, int weigt) {
        this.start = start;
        this.end = end;
        this.weight = weigt;
    }

    // 重写toString，便于输出边信息
    @Override
    public String toString() {
        return "EData [<" + start + ", " + end + ">=" + weight + ']';
    }
}