package com.atguigu.sparsearray;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author chenhuiup
 * @create 2020-07-30 下午 8:40
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("转化稀疏数组");
        //1.获取原数组元素的个数
        int count = 0;//计数器
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                }
            }
        }
        //2.创建稀疏数组,并在行首记录原数组的容量信息
        int[][] chessArr2 = new int[count + 1][3];
        chessArr2[0][0] = 11;
        chessArr2[0][1] = 11;
        chessArr2[0][2] = count;
        //3.遍历原数组，为稀疏数组赋值
        count = 1;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    chessArr2[count][0] = i;
                    chessArr2[count][1] = j;
                    chessArr2[count++][2] = chessArr1[i][j];

                }
            }
        }
        //4.打印稀疏数组
        for (int[] row : chessArr2) {
            System.out.printf("%d\t%d\t%d\n", row[0], row[1], row[2]);
        }

        System.out.println("稀疏数组转化为原数组");
        //1.还原原数组的容量
        int[][] chessArr3 = new int[chessArr2[0][0]][chessArr2[0][1]];
        //2.为原数组赋值
        for (int i = 1; i < chessArr2.length; i++) {
            chessArr3[chessArr2[i][0]][chessArr2[i][1]] = chessArr2[i][2];
        }
        //3.输出还原后的原数组
        for (int[] row : chessArr3) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //IO存储，持久化数据
        FileOutputStream fos = null;
        try {
            //1.造流，造文件
            fos = new FileOutputStream("H:\\SparseArray.txt");
            //2.操作数据
            for (int[] row : chessArr2) {
                for (int data: row){
                    fos.write((byte)data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.关闭资源
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //IO读取
        FileInputStream fis = null;
        try {
            //1.造文件，造流
            fis = new FileInputStream("H:\\SparseArray.txt");
            //2.操作数据
            int len;
            byte[] buff = new byte[100];
            byte[] buffs = new byte[100];
            while ((len = fis.read(buff)) > 0){
                buffs = buff;
            }
            //2.1还原为稀疏数组
            //确定稀疏数组容量
            int[][] chessArr4 = new int[buffs[2] + 1][3];
            //为稀释数组赋值
            count = 0;//计数器
            for (int i = 0; i < chessArr4.length; i++) {
                chessArr4[i][0] = buffs[count++];
                chessArr4[i][1] = buffs[count++];
                chessArr4[i][2] = buffs[count++];
            }
            //遍历稀释数组
            System.out.println("IO读取后的稀疏数组");
            for (int[] row : chessArr4) {
                System.out.printf("%d\t%d\t%d\n",row[0],row[1],row[2]);
            }
            //2.2 稀疏数组还原为原数组
            int[][] chessArr5 = new int[chessArr4[0][0]][chessArr4[0][1]];
            for (int i = 1; i < chessArr4.length; i++) {
                chessArr5[chessArr4[i][0]][chessArr4[i][1]] = chessArr4[i][2];
            }
            //2.3遍历还原后的原数组
            System.out.println("IO读取后的原数组");
            for (int[] row : chessArr5) {
                for (int data: row) {
                    System.out.printf("%d\t",data);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.关闭资源
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
