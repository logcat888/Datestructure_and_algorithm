package com.atguigu.avl;

/**
 * @author chenhuiup
 * @create 2020-10-08 15:32
 */
/*
平衡二叉树（AVL树）-- 左旋转/右旋转/双旋转
0. 解决二叉树不平衡的问题，即（右子树高度 - 左子树高度）的绝对值大于1，极端情况下会成为单链表
1. 树高度的计算：
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }
2. 双旋转：经过一次单旋转无法成为AVL树，需要左右旋转配合
    1）当二叉树需要进行右旋转时，当前节点的左节点的右子树高度大于其左子树高度，经过右旋转后依然不能成为AVL树。
        解决方法：需要先对当前节点的左子树进行左旋转，再对当前节点进行右旋转。
    2）左旋转中存在的双旋转问题。
3. 左旋转：降低右子树的高度
    右旋转：降低左子树的高度
节点的度：一个节点拥有的子树个数。比如A节点有两棵子树，那么A节点的度为2。
树的度：所有节点中度最大的值
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8}; //左旋转
//        int[] arr = {10,12,8,9,7,6}; //右旋转
        int[] arr = {10,11,7,6,8,9}; //双旋转

        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i : arr) {
            Node node = new Node(i);
            avlTree.add(node);
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println();
        System.out.println("平衡处理");
        System.out.println("树的高度" + avlTree.getRoot().height()); // 4
        System.out.println("树的左子树高度" + avlTree.getRoot().left.height()); // 1
        System.out.println("树的右子树高度" + avlTree.getRoot().right.height()); // 3
        System.out.println("根节点：" +avlTree.getRoot());


    }
}

//创建AVlTree
class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node; //如果root为空则直接让node成为root
        } else {
            root.addNode(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.midOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root != null) {
            return root.searchParent(value);
        } else {
            return null;
        }
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root != null) {
            return root.search(value);
        } else {
            return null;
        }
    }

    // 编写方法：
    // 1. 返回以Node为根节点的二叉排序树的最小节点的值
    // 2. 删除node为根节点的二叉排序树

    /**
     * @param node 传入的节点（当做二叉排序树的根节点）
     * @return 返回以Node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {

        Node target = node;
        // 循环的查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 1. 需要先去找到要删除的节点 targetNode
            Node targetNode = search(value);
            // 如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            // 如果我们发现当前这个二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //去找到targetNode的父节点
            Node parent = searchParent(value);
            //情况一：如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断targetNode是父节点的左子节点，还是右子节点
                if (parent.left != null && value == parent.left.value) {
                    //要删除的叶子结点是父节点的左节点
                    parent.left = null;
                } else if (parent.right != null && value == parent.right.value) {
                    //要删除的叶子结点是父节点的右节点
                    parent.right = null;
                }
                //情况三：删除有两颗子树的节点
            } else if (targetNode.left != null && targetNode.right != null) {
                // 寻找要删除节点的右子树的最小值，让该值替换要删除的节点，同时删除最小节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else { //情况二：删除有一颗子树的节点
                //如果要删除的节点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) { // 当删除的节点为根节点时，让root直接指向要删除节点的左子节点，否则会空指针
                        // 如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else { // targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {  // 当删除的节点为根节点时，让root直接指向要删除节点的左子节点
                        root = targetNode.left;
                    }
                } else {// 要删除的节点有右子节点
                    if (parent != null) {// 当删除的节点为根节点时，让root直接指向要删除节点的右子节点，否则会空指针
                        // 如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else { // targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }

        }
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight(){
        if (this.left == null){
            return 0;
        }
        return this.left.height();
    }

    //返回右子树的高度
    public int rightHeight(){
        if (this.right == null){
            return 0;
        }
        return this.right.height();
    }

    //返回当前节点的高度，以该节点为根节点的树的高度
    public int height() {
        //递归判断取左右子树的最大高度 + 1
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    private void leftRotate(){
        //1.创建新的节点，以当前根节点的值为值
        Node newNode = new Node(value);
        // 2.把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        // 3.把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        // 4.把当前节点的值替换成右子节点的值
        this.value = right.value;
        // 5.把当前节点的左子树设置成新的节点
        this.left = newNode;
        // 6.把当前节点的右子树设置成当前节点右子树的右子树
        this.right = this.right.right;
    }

    //右旋转方法
    private void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        right = newNode;
        left = left.left;
    }


    //中序遍历
    public void midOrder() {
        //向左遍历
        if (this.left != null) {
            this.left.midOrder();
        }
        // 输出当前节点
        System.out.print(this + "\t");
        // 向右遍历
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    // 添加节点,相同的节点放在右边
    public void addNode(Node elem) {
        if (elem == null) {
            return;
        }
        if (this.compareTo(elem) > 0) {
            if (this.left == null) {
                this.left = elem;
            } else {
                this.left.addNode(elem);
            }
        } else {
            if (this.right == null) {
                this.right = elem;
            } else {
                this.right.addNode(elem);
            }
        }

        //当添加完一个节点后，如果：（右子树的高度 - 左子树的高度） > 1，左旋转
        if (rightHeight() - leftHeight() > 1){
            // 如果它的右子树的左子树高度大于它的右子树的右子树高度
            if (right != null && right.leftHeight() > right.rightHeight()){
                // 先对当前节点的右子树进行右旋转
                right.rightRotate();
            }
            // 再对当前节点进行左旋转
            leftRotate();
            return;// 这个条件必须加，可以减少后面代码的影响，不需要执行后面的代码
        }
        //当添加完一个节点后，如果：（左子树的高度 - 右子树的高度） > 1，右旋转
        if (leftHeight() - rightHeight() > 1){
            // 如果它的左子树的右子树高度大于它的左子树的左子树高度
            if (left != null && left.rightHeight() > left.leftHeight()){
                // 先对当前节点的左子树进行左旋转
                left.leftRotate();
            }
            // 再对当前节点进行右旋转
            rightRotate();
        }
    }


    //查找要删除的节点

    /**
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) { //找到就是该节点
            return this;
        } else if (value < this.value) { //如果查找的值小于当前节点，向左子树递归查找
            // 如果左子节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { //如果查找的值不小于当前节点，向左子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点

    /**
     * @param value 要找到的节点的值
     * @return 返回的是要删除的节点的父节点，如果没有就返回null
     */
    public Node searchParent(int value) {
        //如果当前节点就是要删除的节点的父节点，就返回
        if ((this.left != null && this.left.value == value) || this.right != null && this.right.value == value) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (this.value > value && this.left != null) {
                return this.left.searchParent(value); //向左子树递归查找
            } else if (this.value < value && this.right != null) {
                return this.right.searchParent(value); //向右子树递归查找
            } else {
                return null;//没有找到父节点
            }
        }
    }

    // 找到要删除节点的右子树的最小值
    public Node minNode() {
        if (this.left == null) {
            return this;
        } else {
            return this.left.minNode();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
