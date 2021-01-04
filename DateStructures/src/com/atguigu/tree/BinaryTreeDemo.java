package com.atguigu.tree;

/**
 * @author chenhuiup
 * @create 2020-08-31 20:24
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");


        //先手动创建二叉树，后面使用递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setBoot(root);

        //测试遍历
//        System.out.println("前序遍历");//1,2,3,5,4
//        binaryTree.preOrder();
//
//        System.out.println("中序遍历");//2,1,5,3,4
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历");//2,5,4,3,1
//        binaryTree.postOrder();

        //测试查找，查找次数，各个方式都不一样，若查找1
//        System.out.println("前序查找");
//        HeroNode node = binaryTree.preOrderSearch(5);//4次找到
//        System.out.println(node);
//
//        System.out.println("中序查找");
//        HeroNode node1 = binaryTree.infixOrderSearch(5);//3次找到
//        System.out.println(node1);
//
//        System.out.println("后序查找");
//        HeroNode node6 = binaryTree.postOrderSearch(5);//2次找到
//        System.out.println(node6);
//
        //测试删除
        binaryTree.delNode(3);
        binaryTree.preOrder();

    }
}

//定义BinaryTree二叉树
class BinaryTree {
    private HeroNode boot;

    public void setBoot(HeroNode boot) {
        this.boot = boot;
    }

    public BinaryTree() {

    }

    //前序遍历
    public void preOrder() {
        if (this.boot != null) {
            this.boot.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.boot != null) {
            this.boot.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.boot != null) {
            this.boot.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (this.boot != null) {
            return this.boot.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (this.boot != null) {
            return this.boot.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (this.boot != null) {
            return this.boot.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除
    public void delNode(int no){
        if (boot == null){
            System.out.println("二叉树为空，无法删除");
        }else if (boot.getNo() == no){
            boot = null;
        }else {
            boot.delNode(no);
        }
    }
}

//先创建HeroNode
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //编写中序遍历的方法
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);//先输出父节点
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //编写后序遍历的方法
    public void postOrder() {
        //递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }

        //递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);//先输出父节点
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序查找");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        //向左递归,前序遍历查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        //判断左递归是否找到，找到则返回，否则向右递归
        if (resNode != null) {
            return resNode;
        }

        //向右递归，前序遍历查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        //判断右递归是否找到，找到则返回
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //先向左递归
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        //判断左递归是否找到
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找");
        if (this.no == no) {
            return this;
        }
        //向右递归查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        //先向左递归
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        //判断左递归是否找到
        if (resNode != null) {
            return resNode;
        }

        //向右递归查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        //判断向右递归是否找到
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    //递归删除节点
    //1.如果删除的节点是叶子结点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no) {
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no){
            this.right =null;
            return;
        }
        if (this.left != null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }
        //------自己编写的-------------
//        boolean flag = false;
//        if (this.left != null) {
//            if (this.left.no == no) {
//                flag = true;
//            }else {
//                this.left.delNode(no);
//            }
//        }
//        if (flag){
//            this.left = null;
//            return;
//        }else {
//            if (this.right != null) {
//                if (this.right.no == no) {
//                    flag = true;
//                }else {
//                    this.right.delNode(no);
//                }
//            }
//        }
//        if (flag){
//            this.right = null;
//        }
    }
}
