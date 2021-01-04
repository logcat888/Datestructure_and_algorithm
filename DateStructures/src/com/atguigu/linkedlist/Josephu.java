package com.atguigu.linkedlist;

/**
 * @author chenhuiup
 * @create 2020-08-13 下午 10:13
 */
public class Josephu {
    public static void main(String[] args) {
        //测试一把看看构建环形链表，和遍历是否ok
        CircleSingleLinkedList josephu = new CircleSingleLinkedList();
        josephu.addBoy(25);//加入5个小孩
        josephu.showBoy();

        System.out.println("************");
        //测试小孩出圈是否正确
        josephu.countBoy(1,2,25);
    }

}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表

        for (int i = 1; i <= nums; i++) {
           // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1){
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curBoy指向第一个小孩
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
        //**********自己编写***********
//        for (int i = 1; i <= nums; i++) {
//            //根据编号，创建小孩节点
//            Boy boy = new Boy(i);
//            //如果是第一个小孩
//            if (i == 1) {
//                first = boy;
//                boy.next = first;
//                curBoy = boy;
//                continue;
//            }
//            curBoy.next = boy;
//            boy.next = first;
//            curBoy = boy;
//        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }

        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.next;//curBoy后移
        }
    }
    //*****自己编写**********
//    public void showBoy(){
//        if (first == null){
//            System.out.println("链表为空");
//            return;
//        }
//        Boy curBoy = first;
//        while (true){
//            if (curBoy.next == first){//链表中最后一个元素
//                System.out.println(curBoy);
//                return;
//            }
//            System.out.println(curBoy);
//            curBoy = curBoy.next;
//        }
//    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum,int nums){
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo >nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建要给辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //需求创建一个辅助指针helper，事先应该指向环形链表的最后这个节点
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数钱，先让first和helper移动 k - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数是，让first 和helper指针同时的移动 m - 1 次，然后出圈
        //这里是一个循环槽，直到圈中只有一个节点
        while (true){
            if (helper == first){//说明圈中只有一个节点
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩编号为%d\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号为%d\n",first.getNo());
    }
    //**********自己编写的*****正确
//    public void countBoy(int num){
//        if (first == null){
//            System.out.println("链表为空");
//            return;
//        }
//        Boy head = first;//头指针，指向要删除的节点
//        Boy curBoy = null;//删除指针，指向删除节点的上一个元素
//        Boy temp = first;//临时指针，目的是遍历出first指针的上一个元素，并将该节点赋给curBoy
//        while (true){//将first的上一个节点赋值给curBoy
//            if (temp.next == head){
//                break;
//            }
//            temp = temp.next;
//        }
//        curBoy = temp;
//        //循环遍历删除小孩节点，并输出小孩编号
//        while (true){
//            if (curBoy == head){//链表中只有一个小孩
//                System.out.printf("小孩编号为%d\n",head.getNo());
//                return;
//            }else {
//                for (int i = 1; i < num; i++) {//移动指针
//                    head = head.next;
//                    curBoy = curBoy.next;
//                }
//                //删除小孩节点
//                System.out.printf("小孩编号为%d\n",head.getNo());
//                curBoy.next = head.next;
//                head = curBoy.next;
//            }
//        }
//    }
}

//创建一个Boy类，表示一个节点
class Boy {
    private int no;//编号
    public Boy next; //指向下一个节点，默认为null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
