package com.atguigu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author chenhuiup
 * @create 2020-10-08 20:27
 */
/*
图：
1.基本概念：顶点、边（有向或者无向）
2.图的表示方式：邻接矩阵、邻接表
3.图的遍历
    1）深度优先（DFS）：纵向遍历，先遍历初始节点的，再遍历第一个邻接节点，然后以该节点为初始节点，遍历其第一个邻接节点，
        如果已经遍历过则遍历其第二个邻接节点，如果没有则返回上一层，遍历其第二个邻接节点。每次遍历节点后都标记节点已被访问。
    2）广度优先（BFS）：横向遍历，先遍历初始节点的，并加入队列，所有连接节点，即邻接矩阵的一行数据，并加入队列，并标记已访问的节点，
        。遍历完一行后，取出队列的元素，再次遍历一行。通过通过另一个方法，遍历所有的节点，寻找下一个初始节点，进行回溯。
 DFS: 1 -2 - 4 - 8 - 5 - 3 - 6 -7
 BFS：1 - 2 - 3 - 4 - 5 - 6 - 7 - 8
 */
public class Graph {

    private ArrayList<String> vertexList; //存储顶点的集合
    private int[][] edges; //存储图对应的邻接矩阵
    private int numOfEdges; //表示边的数目
    // 定义一个数组boolean[]，记录某个节点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试一把图是否创建ok
//        int n = 5; //节点的个数
        int n = 8; //节点的个数
//        String[] vertexs = {"A", "B", "C", "D", "E"};
        String[] vertexs = {"1", "2", "3", "4", "5","6","7","8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        // A-B  A-C B-C B-D B-E
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);
        // 0-1 0-2 1-3 1-4 3-7 4-7 2-5 2-6 5-6
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        //显示邻接矩阵
        graph.showGraph();
        //测试一把深度优先遍历图
//        System.out.println("深度优先DFS遍历");
//        graph.dfs();
        System.out.println();
        System.out.println("广度优先BFS遍历");
        graph.bfs();

    }

    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);  //ArrayList的初始长度设置为n
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //图中常用的方法
    // 返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i（下标）对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边
    /**
     * @param v1     表示顶点的下标
     * @param v2     表示顶点的下标
     * @param weight 表示v1与v2之间边对应的权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //得到第一个邻接节点的下标w
    /**
     *
     * @param index
     * @return 如果存在就返回对应的对标，否则返回-1
     */
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] >0){
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2 + 1;j < vertexList.size();j++){
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //深度优先（DFS）遍历
    // i 第一次就是0
    private void dfs(boolean[] isVisited,int i) {
        //首先我们访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将节点设置为已经访问
        isVisited[i] = true;
        //查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1){ //说明有
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            // 如果w节点以及被访问过
            w = getNextNeighbor(i,w);
        }
    }

    //对dfs进行重载，遍历我们所有的节点，并进行dfs
    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        //遍历所有的节点，进行dfs[回溯]
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //对一个节点进行广度优先遍历（BFS）的方法
    private void bfs(boolean[] isVisited,int i){
        int u; //表示队列的头节点对应下标
        int w; //邻接节点w
        //队列，记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //访问节点，输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()){
            //取出队列的头结点下标
            u = queue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w != -1){
                //是否访问过
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱节点，找到w后面的下一个邻节点
                w = getNextNeighbor(u,w); //这里就体现出广度优先,将邻接矩阵中一行遍历完，横向
            }
        }
    }

    //遍历所有的节点，都进行广度优先搜索（BFS）
    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
}
