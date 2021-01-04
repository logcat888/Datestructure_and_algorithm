package com.atguigu.recursion;

/**
 * @author chenhuiup
 * @create 2020-08-20 8:43
 */
public class RecursionTest {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙，上下左右各置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = map[3][2] = 1;
        //输出地图
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("*************************");
        boolean b = setWay2(map, 1, 1);
        if (b) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("没有出口");
        }

    }

    //使用递归回溯来给小球找路
    //说明
    //1.map 表示地图
    //2.i，j表示从地图的那个位置开始出发（1,1）
    //3.如果小球能到map[6][5]位置，则说明通路找到
    //4.约定：当map[i][j] 为0表示该点没有走过当为1表示墙，2表示通路可以走，3表示该点已经走过，但是走不通
    //5. 走迷宫是，需要确定一个策略(方法) 下- 右  - 上 - 左，如果该点走不通，再回溯

    /**
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已经找到ok
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没有走过
                //策略(方法) 下- 右  - 上 - 左
                map[i][j] = 2;//假定改点是可以走通
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;//说明该点走不通，是死路
                    return false;
                }
            } else {//可能是1，2,3
                return false;
            }
            //************************************************************
//            if (map[i][j] == 0) {//如果当前这个点还没有走过
//                //策略(方法) 下- 右  - 上 - 左
//                map[i][j] = 2;//假定改点是可以走通
//                if (setWay(map, i + 1, j)) {//向下走
//                    return true;
//                } else if (setWay(map, i, j + 1)) {//向右走
//                    return true;
//                } else if (setWay(map, i - 1, j)) {//向上走
//                    return true;
//                } else {//向左走
//                    if (setWay(map, i, j - 1)) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }
//            } else if (map[i][j] == 1) {//表示为墙
//                return false;
//            } else {//表示已经走过，但是走不通
//                return false;
//            }
        }
    }

    //修改找路策略，改成上左下右
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已经找到ok
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没有走过
                map[i][j] = 2;//假定改点是可以走通
                if (setWay2(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay2(map, i, j - 1)) {//向左走
                    return true;
                } else if (setWay2(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    return true;
                } else {
                    map[i][j] = 3;//说明该点走不通，是死路
                    return false;
                }
            } else {//可能是1，2,3
                return false;
            }
        }
    }
}