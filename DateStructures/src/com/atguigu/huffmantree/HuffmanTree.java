package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author chenhuiup
 * @create 2020-09-06 22:16
 */
/*
赫夫曼树：
1. 赫夫曼树是带权路径最小的树。叶子结点的值称为权值，所有叶子结点到根节点的路径与权值乘积称为带权路径。
2. 赫夫曼树的构建过程
1）取从小到大排序的数组的前2位，构建成一颗二叉树，根节点为这两个数的和。
2）删除数组中的前两位数，加入根节点，再重新排序。
3）重复1 - 2，直到数组中只剩下一个元素即完成赫夫曼树的构建。
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node node = creatHuffmanTree(arr);
        preOrder(node);
    }

    //创建赫夫曼树的方法

    /**
     * @param arr 需要创建成赫夫曼树的数组
     * @return 返回赫夫曼树的root节点
     */
    public static Node creatHuffmanTree(int[] arr) {
        //1.将数组放到ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        //从小到大排序
        Collections.sort(nodes);
        //我们的处理过程是一个循环的过程
        while (true) {
            if (nodes.size() <= 1) {//当集合中只有一个元素时说明赫夫曼树构建完毕，wpl就是根节点的权值
                return nodes.get(0);
            }
            //取出根节点权值最小的两颗二叉树
            //（1）取出权值最小的节点（二叉树）  最简单的二叉树就是只有一个节点
            Node leftNode = nodes.get(0);
            //（2）取出权值第二小的节点（二叉树）
            Node rightNode = nodes.get(1);
            //（3）构建成一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //（4）从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //（5）将parent加入nodes
            nodes.add(parent);
            Collections.sort(nodes);
        }
    }

    //编写一个前序遍历的方法a
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("是空树");
        }
    }

}

//创建节点类
//为了让Node 对象持续排序Collections集合排序
//让Node 实现Comparable接口
class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(value, o.value);
    }

    //写一个前序遍历的方法，验证赫夫曼树是否构建成功
    public void preOrder() {

        System.out.println(this);
        //向左递归
        if (this.left != null) {
            this.left.preOrder();
        }

        //向右递归
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
