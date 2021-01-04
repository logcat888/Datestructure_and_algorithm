package com.atguigu.tree;

/**
 * @author chenhuiup
 * @create 2020-09-01 7:32
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();//1,2,4,5,3,6,7
        System.out.println();
        arrBinaryTree.infixOrder();//4,2,5,1,6,3,7
        System.out.println();
        arrBinaryTree.postOrder();//4,5,2,6,7,3,1
    }
}

//编写一个ArrayBinaryTree，实现顺序存储二叉树
class ArrBinaryTree {
    private int[] arr;//存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历

    /**
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        if (index >= arr.length || index < 0) {
            return;
        }
        System.out.printf("%d ", arr[index]);

        //向左递归
        preOrder(2 * index + 1);

        //向右递归
        preOrder(2 * index + 2);

    }

    //重载
    public void infixOrder() {
        this.infixOrder(0);
    }

    //中序遍历
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (index >= arr.length) {
            return;
        }
        //向左递归
        infixOrder(2 * index + 1);
        System.out.print(arr[index] + " ");
        //向右递归
        infixOrder(2 * index + 2);
    }

    //重载
    public void postOrder() {
        this.postOrder(0);
    }

    //中序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (index >= arr.length) {
            return;
        }
        //向左递归
        postOrder(2 * index + 1);
        //向右递归
        postOrder(2 * index + 2);
        System.out.print(arr[index] + " ");
    }

}
