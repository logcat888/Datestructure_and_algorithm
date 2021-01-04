package com.atguigu.binarysorttree;

/**
 * @author chenhuiup
 * @create 2020-10-07 17:15
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
//        // 测试构建二叉排序树
//        Node binarySortTree = createBinarySortTree(arr);
//        midOrder(binarySortTree);
//
//        //测试向二叉排序树中添加元素
//        add(binarySortTree, 13);
//        // 测试查找元素
//        System.out.println(search(binarySortTree, 13));
//        midOrder(binarySortTree);

        //循环添加节点
        BinarySortTree sortTree = new BinarySortTree();
        for (int i : arr) {
            sortTree.add(new Node(i));
        }

        //中序遍历二叉排序树
        sortTree.infixOrder();
        System.out.println();

        //测试删除叶子结点
        sortTree.delNode(2);
        sortTree.delNode(5);
        sortTree.delNode(9);
        sortTree.delNode(12);
        sortTree.delNode(7);
        sortTree.delNode(3);
        sortTree.delNode(10);
        sortTree.delNode(1);
        System.out.println("root=" + sortTree.getRoot());

        sortTree.infixOrder();


    }

//    // 构建二叉排序树
//    public static Node createBinarySortTree(int[] arr) {
//        if (arr.length < 1) {
//            System.out.println("无法创建二叉排序树");
//            return null;
//        }
//        Node root = new Node(arr[0]);
//        for (int i = 1; i < arr.length; i++) {
//            Node n1 = new Node(arr[i]);
//            root.addNode(n1);
//        }
//
//        return root;
//    }
//
//    // 遍历二叉排序树
//    public static void midOrder(Node root) {
//        if (root == null) {
//            System.out.println("根节点为空，无法遍历");
//        } else {
//            root.midOrder();
//        }
//    }
//
//    // 添加元素
//    public static void add(Node root, int elem) {
//        Node n1 = new Node(elem);
//        if (root == null) {
//            root = n1;
//        }
//        root.addNode(n1);
//    }
//
    // 删除元素
//    public static boolean delete(Node root,int elem){
//        Node n1 =  new Node(elem);
//        if (!root.search(n1)){
//            return false;
//        }else {
//            if (root.value == elem && root.right != null && root.right.value != elem){
//                root = null;
//                return
//            }
//        }
//    }
//
//    // 查找元素
//    public static boolean search(Node root, int elem) {
//        Node n1 = new Node(elem);
//        if (root == null) {
//            return false;
//        }
//        return root.search(n1);
//    }
}

//创建二叉排序树
class BinarySortTree {
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
//        // 返回以Node为根节点的二叉排序树的最小节点的值
//        Node minNode = node.minNode();
//        // 找到最小节点的父节点
//        Node parent = node.searchParent(minNode.value);
//        // 删除最小节点
//        parent.left = null;
//        //返回最小值
//        return minNode.value;

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
    }

    // 查找节点
//    public boolean search(Node elem) {
//        boolean res = false;
//        if (elem != null) {
//            if (this.compareTo(elem) > 0) {
//                res = this.left.search(elem);
//            } else if (this.compareTo(elem) == 0) {
//                res = true;
//            } else {
//                res = this.right.search(elem);
//            }
//        }
//        return res;
//    }


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

        // 自己编写的，测试没有问题
//      if (search(value) == null){
//          return null;
//      }else {
//          if (this.value == value){
//              return null;
//          }else if (this.value > value){
//              if (this.left.value == value){
//                  return this;
//              }else {
//                  return this.left.searchParent(value);
//              }
//          }else {
//              if (this.right.value == value){
//                  return this;
//              }else {
//                  return  this.right.searchParent(value);
//              }
//          }
//      }
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
