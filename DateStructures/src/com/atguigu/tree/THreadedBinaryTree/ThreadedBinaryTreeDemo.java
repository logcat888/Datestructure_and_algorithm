package com.atguigu.tree.THreadedBinaryTree;

/**
 * 线索化二叉树
 *
 * @author chenhuiup
 * @create 2020-09-01 20:19
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode node1 = new HeroNode(1, "a1");
        HeroNode node2 = new HeroNode(3, "a1");
        HeroNode node3 = new HeroNode(6, "a1");
        HeroNode node4 = new HeroNode(8, "a1");
        HeroNode node5 = new HeroNode(10, "a1");
        HeroNode node6 = new HeroNode(14, "a1");

        //二叉树，后面递归创建，现在收到创建
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(node1);
        node1.setLeft(node2);
        node2.setLeft(node4);
        node2.setRight(node5);
        node1.setRight(node3);
        node3.setLeft(node6);

        //测试中序线索化
        threadedBinaryTree.threadedNodes();

        //测试：以10号节点测试
        HeroNode left = node5.getLeft();
        //前驱节点
        System.out.println(left);//3
        HeroNode right = node5.getRight();
        //后继节点
        System.out.println(right);//1

        System.out.println("********************");
        threadedBinaryTree.infixOrder();
        System.out.println("*************");
        threadedBinaryTree.threadedList();

    }
}

//定义ThreadedBinaryTree 实现了线索化功能二叉树
class ThreadedBinaryTree {
    private HeroNode root;

    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre 总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public ThreadedBinaryTree() {
    }

    //遍历线索化二叉树的方法
    public void threadedList() {
        HeroNode node = root;
        while (node != null) {
            //获取线索化节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while ( node.getRightType() == 1){
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }

    }

    public void threadedNodes() {
        threadedNodes(root);
    }
    //编写对二叉树进行中序线索化的方法

    /**
     * @param node 就是当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //1. 线索化左子树
        threadedNodes(node.getLeft());

        //2. 线索化当前节点
        //处理前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
        //3. 线索化右子树
        threadedNodes(node.getRight());

    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除
    public void delNode(int no) {
        if (root == null) {
            System.out.println("二叉树为空，无法删除");
        } else if (root.getNo() == no) {
            root = null;
        } else {
            root.delNode(no);
        }
    }
}

//先创建HeroNode
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //说明
    //1. 如果leftType == 0 表示指向的是左子树，如果是 1 则表示指向前驱节点
    //2. 如果RightType == 0 表示指向的是右子树，如果是 1 则表示指向后驱节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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
        if (this.leftType == 0 && this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);//先输出父节点
        //递归向右子树中序遍历
        if (this.rightType == 0 && this.right != null) {
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
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
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
