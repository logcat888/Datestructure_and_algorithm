package com.atguigu.linkedlist;

/**
 * @author chenhuiup
 * @create 2020-08-13 下午 7:29
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");
        //先创建节点
        HeroNode2 node1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 node2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 node3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 node4 = new HeroNode2(4, "林冲", "豹子头");
        //创建一个双向链表
        DoubleLinkList doubleLinkList = new DoubleLinkList();
//        doubleLinkList.add(node1);
//        doubleLinkList.add(node2);
//        doubleLinkList.add(node3);
//        doubleLinkList.add(node4);

        doubleLinkList.addByOrder(node3);
        doubleLinkList.addByOrder(node2);
        doubleLinkList.addByOrder(node4);
        doubleLinkList.addByOrder(node1);

        doubleLinkList.list();
        //修改
        HeroNode2 node5 = new HeroNode2(4, "赵丽颖", "颖宝");
        doubleLinkList.update(node5);
        doubleLinkList.list();
    }
}


//创建一个双向链表
class DoubleLinkList{
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法
    //显示链表[遍历]
    public void list() {
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode2 temp = head;
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
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //按照编号顺序添加一个节点
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp = head;
        while (true){
            if (temp.next == null){
                temp.next = heroNode;
                heroNode.pre = temp;
                return;
            }
            if (temp.next.no > heroNode.no){
                temp.next.pre = heroNode;
                heroNode.next = temp.next;
                heroNode.pre = temp;
                temp.next = heroNode;
                return;
            }else if (temp.next.no == heroNode.no){
                System.out.println("元素相同，无法添加");
                return;
            }else {
                temp = temp.next;//指针后移
            }
        }
    }

    //修改一个节点的内容,可以看到双向链表的节点内容修改和单向链表一样
    //只是节点类型修改
    //修改节点的信息，根据no编号来修改，即no编号不能改
    //说明
    //1.根据newHeroNode 的no来修改即可
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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
    }

    //从双向链表中删除一个节点
    //思路
    //1.对于双向链表，我们可以直接找到要删除的节点
    //2.找到后，自我删除即可
    public void del(int no) {
        HeroNode2 temp = head.next;//辅助指针
        if (temp == null){
            System.out.println("链表为空，无法删除");
            return;
        }

        boolean flag = false;
        while (true) {
            if (temp == null) {//到达链表的最后
                break;
            }
            if (temp.no == no) {//找到元素
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag) {//找到
            temp.pre.next = temp.next;
            //这里我们的代码有问题？
            //如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }
    }
}

//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点，默认为null
    public HeroNode2 pre;//指向前一个节点，默认为null
    //构造器

    public HeroNode2(int no, String name, String nickname) {
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
