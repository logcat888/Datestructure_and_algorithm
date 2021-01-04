package com.atguigualgorithm.dijkstra;

import java.util.Arrays;

/**
 * @author chenhuiup
 * @create 2020-10-14 19:50
 */
/*
迪杰斯特拉算法：解决图中一个顶点到其他顶点的最短路径问题。
1. 定义一个集合类，定义三个数组属性
    1）属性：
        & 记录顶点是否被访问；
        & 记录顶点的前驱节点，可以用于记录访问路径
        & 记录出发顶点到各顶点的距离，初始距离为极大值，动态更新最小距离
    2）继续选择并返回新的访问顶点
 2. 核心思想：
    1）更新dis集合：广度优先的思想遍历所有未访问过的节点，根据dis集合中 出发顶点到访问顶点的距离 + 访问顶点到目标顶点的距离是否小于
        出发顶点到目标顶点的距离，判断是否考虑更新dis集合。
    2）新访问节点的获取：判断节点是否被标记访问，循环遍历找到未被访问，且出发顶点到新访问顶点最小距离的顶点，作为新访问顶点，
        在这里也体现了贪心算法，每次只找最优的顶点访问，并标记访问节点。


 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = Character.MAX_VALUE;
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};
        //创建graph对象
        Graph graph = new Graph(vertex, matrix);
        //显示图
        graph.showGrap();
        //测试迪杰斯特拉算法
        graph.dsj(6);
        graph.showDijkstra();

    }
}

class Graph{
    private char[] vertex; //顶点数组
    private int[][] matrix; //邻接矩阵
    private VisitedVertex vv; //已经访问的顶点的集合

    //构造器
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGrap(){
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //迪杰斯特拉算法实现
    /**
     *
     * @param index 表示出发顶点对应的下标
     */
    public void dsj(int index){
        vv = new VisitedVertex(vertex.length, index);
        update(index); //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
        for (int j = 0; j < vertex.length; j++) {
            int i = vv.updateArr(); //选择并返回新的访问顶点
            update(i); //更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    //显示结果
    public void showDijkstra(){
        vv.show();
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index){
        int len = 0;
        //根据遍历我们的邻接矩阵的matrix[index] 行
        for (int j = 0; j < matrix[index].length; j++) {
            //len 含义是：出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
            len = vv.getDis(index) + matrix[index][j];
            if (!vv.in(j) && len < vv.getDis(j)){
                vv.updatePre(j,index); //更新j顶点的前驱为index顶点
                vv.updateDis(j,len); //更新出发顶点到j顶点的距离
            }
        }
    }


}

//已访问顶点集合
class VisitedVertex{
    //记录各个顶点是否访问过， 1表示访问过，0表示未访问，会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;

    /**
     *  构造器
     * @param length 表示顶点的个数
     * @param index 出发顶点对应的下标，比如G顶点，下标就是6
     */
    public VisitedVertex(int length,int index){
        already_arr = new int[length];
        pre_visited = new int[length];
        dis = new int[length];

        //初始化 dis数组
        Arrays.fill(dis,65535);
        already_arr[index] = 1; //设置出发顶点被访问过
        dis[index] = 0; //设置出发顶点的访问距离为0
    }

    /**
     *  功能:判断index顶点是否被访问过
     * @param index
     * @return 如果访问过，就返回true，否则返回false
     */
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    /**
     *  功能：更新出发顶点到index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     *  功能：更新pre这个顶点的前驱顶点为index顶点
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index){
        pre_visited[pre] = index;
    }

    /**
     *  功能：返回出发顶点到index顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     *  功能：继续选择并返回新的访问顶点，比如这里的G 完后，就是A点作为新的访问顶点（注意不是出发顶点）
     * @return
     */
    public int updateArr(){
        int min = 65535,index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min){ //循环遍历找到未被访问，且出发顶点到新访问顶点最小距离的顶点，作为新访问顶点
                min = dis[i];
                index = i;
            }
        }

        //更新index顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果
    //将将三个数组的情况输出
    public void show(){
        System.out.println("\n===========already_arr===========");
        //输出already_arr
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        //输出pre_visited
        System.out.println("\n===========pre_visited============");
        for (int i : pre_visited) {
            System.out.printf(i + " ");
        }
        //输出dis
        System.out.println("\n================dis==================");
        for (int i : dis) {
            System.out.printf(i + " ");
        }

        System.out.println("\n=======================================");
        //为了好看最后的最短距离，我们处理
        char[] vertex = {'A','B','C','D','E','F','G'};
        int count = 0;
        for (int i : dis) {
            if ( i != 65535){
                System.out.print(vertex[count] + "("+ i +") ");
            }else {
                System.out.printf(vertex[count] + "(N) ");
            }
            count++;
        }

    }

}
