package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * @author chenhuiup
 * @create 2020-08-13 上午 9:41
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        singleLinkedList.add(node1);
        singleLinkedList.add(node2);
        singleLinkedList.add(node3);
        singleLinkedList.add(node4);
        //测试一下原链表
        System.out.println("*****原链表*******");
        singleLinkedList.list();

        //测试一下反转后的链表
        System.out.println("*****反转后的链表******");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        //测试一下从尾打印链表
        System.out.println("测试一下从尾打印单链表");
        reversePrint(singleLinkedList.getHead());


//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();

//        singleLinkedList.addByOrder(node1);
//        singleLinkedList.addByOrder(node4);
//        singleLinkedList.addByOrder(node3);
//        singleLinkedList.addByOrder(node2);
//        //显示一把
//        singleLinkedList.list();
//        System.out.println("*******************");
//
//        //测试修改节点的代码
//        HeroNode node5 = new HeroNode(2, "小卢", "玉麒麟————");
//        singleLinkedList.update(node5);
//        //显示一把
//        singleLinkedList.list();
//
//        //删除一个节点
//        singleLinkedList.del(1);
//        System.out.println("删除后的链表情况");
//        singleLinkedList.list();
//        System.out.println(getLength(singleLinkedList.getHead()));
//
//        //测试一下看看是否得到了倒数第K个节点
//        HeroNode res = findLastIndexNode(singleLinkedList.getHead(),1);
//        System.out.println("res="+res);
//
//        System.out.println("***********");

    }


    //从尾到头打印单链表
    //方式二：可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            System.out.println("空链表无法打印");
            return;//空链表，不能打印
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;//cur后移，这样就可以压入下一个节点
        }
        //出栈打印，pop()
        while (stack.size() > 0) {
            System.out.println(stack.pop());//stack的特点是先进后出
        }
    }

    //方式一：自己编写 *** 会破坏原链表，但是反转打印，不建议
//    public static void reverseShow(HeroNode head) {
//        //如果当前链表为空，或者只有一个节点，无需反转，
//        if (head.next == null || head.next.next == null) {
//            System.out.println(head.next);
//            return;
//        }
//
//        //反转链表
//        HeroNode cur = head.next;
//        HeroNode next = null;
//        HeroNode newHeadNode = new HeroNode(0,"","");
//        while (cur != null){
//            next =cur.next;
//            cur.next = newHeadNode.next;
//            newHeadNode.next =cur;
//            cur =next;
//        }
//        HeroNode temp =  newHeadNode.next;
//        //遍历反转后的链表，打印
//        while (temp != null){
//            System.out.println(temp);
//            temp = temp.next;
//        }
//    }

    //单链表的反转
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助的指针，帮助我们变了原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
        //动脑筋
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前段
            reverseHead.next = cur;//将cur连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next指向reverseHead.next ，实现单链表的反转
        head.next = reverseHead.next;
    }

    //****自己编写***

//    public static SingleLinkedList reverse(HeroNode head){
//        if (head.next == null){
//            return null;
//        }
//        //获得链表的长度
//        int size = getLength(head);
//        //定义一个空链表接收
//        SingleLinkedList temp = new SingleLinkedList();
//        for (int i = 1; i <= size; i++) {
//            //倒序获得链表元素
//            HeroNode lastIndexNode = findLastIndexNode(head, i);
//            temp.add(lastIndexNode);
//        }
//        return temp;
//    }

    //查找单链表中的倒数第K个节点
    //思路
    //1. 编写一个方法，接收head节点，同时接收一个index
    //2. index表示是倒数第index个节点
    //3. 先把链表从头到尾遍历，得到链表的总长度getlength
    //4. 得到size后，我们从链表的第一个开始遍历(size - index)，就可以得到
    //5. 如果找到了，则返回该节点，否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，则返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一次遍历得到链表的长度（节点个数）
        int size = getLength(head);
        //第二次遍历 size - index 位置，就是我们倒数的第K个节点
        //先做一个index
        if (index <= 0 || index > size) {
            return null;
        }
        //定义给辅助变量，for循环定位到倒数的index
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;

    }

    //方法：获取到单链表的节点的个数（如果是带头节点的链表，需求不统计头节点）
    public static int getLength(HeroNode head) {
        if (head.next == null) {//空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量，这里我们没有统计头结点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;//遍历
        }
        return length;
    }
}

//定义SinglelinkedList管理我们的英雄
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单项链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新节点

    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next新节点
        temp.next = heroNode;
    }

    //第二中方式再添加英雄时，根据排名将英雄插入到指定位置（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助变量(指针)temp，来帮助找到添加的位置
        //因为单链表，因为我们找的temp，是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号依然存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表

        }

        //判断flag 的值
        if (flag) {//不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n", heroNode.no);
        } else {
            //插入都链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
//        HeroNode temp = head;
//        while (true){
//            if (temp.next == null){
//                temp.next = heroNode;
//                break;
//            }
//            int res = Integer.compare(temp.next.no, heroNode.no);
//            if (res >0){//插入的英雄编号小
//                heroNode.next = temp.next;
//                temp.next = heroNode;
//                break;
//            }else if (res == 0){
//                throw new RuntimeException("该元素已存在，无法添加");
//            }else {//插入的英雄编号大，指针后移
//                temp = temp.next;
//            }
//        }
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    //说明
    //1.根据newHeroNode 的no来修改即可
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {//没有找到
            System.out.printf("没有找到编号%d 的节点，不能修改\n", newHeroNode.no);
        }

        //找到需要修改的节点，根据no编号
        //自己编写
//        HeroNode temp = head;
//        while (true){
//            if (temp.next == null){
//                System.out.println("该元素不存在，无法修改");
//                return;
//            }
//            if (temp.next.no == newHeroNode.no){
//                newHeroNode.next = temp.next.next;
//                temp.next = newHeroNode
//            }
//            temp = temp.next;//指针下移
//        }
    }

    //删除节点
    //思路
    //1.head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2.说明我们在比较时，是temp.next.no和需要删除的节点的no比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {//找到元素
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag) {//找到
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }

        //自己编写*********
//        while (true){
//            if (temp.next == null){
//                System.out.println("未找到元素");
//                break;
//            }
//            if (temp.next.no == no){//找到元素
//                temp.next = temp.next.next;
//                break;
//            }
//        temp = temp.next;
//        }
    }

    //显示链表[遍历]
    public void list() {
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //方法：获取到单链表的节点的个数（如果是带头节点的链表，需求不统计头节点）

    public static int getLength(HeroNode head) {
        if (head.next == null) {//空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量，这里我们没有统计头结点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;//遍历
        }
        return length;
    }
    //自己编写*************
//    public int getLength(){
//        int num = 0;
//        HeroNode temp = head;
//        while (true){
//            if (temp.next == null){
//                break;
//            }
//            num++;
//            temp = temp.next;
//        }
//        return num;
//    }
}

//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点
    //构造器

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}