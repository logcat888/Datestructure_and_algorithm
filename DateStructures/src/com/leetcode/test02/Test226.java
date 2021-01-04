package com.leetcode.test02;


import java.util.ArrayList;

/**
 * @author chenhuiup
 * @create 2020-11-17 13:22
 */
/*
翻转一棵二叉树。
 */
public class Test226 {
    public static void main(String[] args) {

    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.left);
        TreeNode left = invertTree(root.right);

        root.right = right;
        root.left = left;
        return root;

    }

//    public TreeNode invertTree(TreeNode root) {
//        if (root != null){
//            preOrder(root);
//            for (TreeNode node : list) {
//                reverseInsert(node,root);
//            }
//        }
//        return root;
//    }
//
//    /**
//     * 如果待插入节点大于叶子结点则放在右边，否则放在左边
//     * @param node 待插入节点
//     * @param root 树中已经存在的节点
//     */
//    public void reverseInsert(TreeNode node , TreeNode root ){
//        if (root.val < node.val && root.left == null){
//            root.left = node;
//            return;
//        }
//        if (root.val > node.val && root.right == null){
//            root.right = node;
//            return;
//        }
//        if (root.val < node.val){
//            reverseInsert(node,root.left);
//        }else if (root.val > node.val){
//            reverseInsert(node,root.right);
//        }
//    }
//
//    ArrayList<TreeNode> list = new ArrayList<>();
//    /**
//     * 树的前序遍历
//     */
//    public void preOrder(TreeNode node){
//        // 添加父节点
//        list.add(node);
//        //向左遍历
//        if (node.left != null){
//           preOrder( node.left);
//        }
//        //向右遍历
//        if (node.right != null){
//            preOrder(node.right);
//        }
//    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}