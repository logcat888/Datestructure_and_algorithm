package com.atguigu.hashtab;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author chenhuiup
 * @create 2020-08-30 22:51
 */
public class HashTabDemo {
    public static void main(String[] args) {
        //常见哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list:  显示雇员");
            System.out.println("quit:  退出系统");
            System.out.println("find:  查找雇员");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                case "find":
                    System.out.println("请输入员工id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                default:
                    break;
            }
        }

    }
}

//创建HashTab 管理多条链表
class HashTab {
    private EmpLinkList[] empLinkListArray;
    private int size;//表示有多少条链表

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkListArray
        empLinkListArray = new EmpLinkList[size];
        //注意：要分别初始化每个链表，否则数组中的元素为空
        for (int i = 0; i < size; i++) {
            empLinkListArray[i] = new EmpLinkList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id，得到该员工应当添加到哪条链表
        int empLinkListNo = hashFun(emp.id);
        //将emp添加到链表中
        empLinkListArray[empLinkListNo].add(emp);
    }

    //遍历所有的链表，遍历hashtab
    public void list() {
        for (int i = 0; i < empLinkListArray.length; i++) {
            empLinkListArray[i].list(i);
        }
    }

    //根据输入的id，查找雇员
    public void findEmpById(int id){
        //根据员工的id，得到该员工应当添加到哪条链表
        int empLinkListNo = hashFun(id);
        Emp emp = empLinkListArray[empLinkListNo].findEmpById(id);
        if (emp !=null){
            System.out.printf("在第%d条链表中找到雇员id=%d\n",empLinkListNo+1,id);
        }else {
            System.out.println("在哈希表中，没有找到该雇员");
        }
    }

    //编写散列函数，使用一个简单取模法
    public int hashFun(int id) {
        return id % size;
    }
}

//创建EmpLinkList，表示链表
class EmpLinkList {
    //头指针，执行第一个Emp，因此，我们这个链表的head是直接指向第一个Emp
    private Emp head;//默认为null

    //添加雇员到链表
    //说明
    //1.嘉定，当添加雇员时，id是自增的，即id的分配总是从小到大
    // 因此 我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;//后移
        }
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {//说明链表为空
            System.out.println("第" + no + "当前链表为空");
            return;
        }
        Emp curEmp = head;//辅助指针
        while (true) {
            System.out.print("第" + no + "当前链表为空 ");
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;//后移
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到，就返回Emp，如果没有找到，就返回null
    public Emp findEmpById(int id){
        //判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        boolean flag = true;
        while (true){
            if (curEmp.id == id){
                return curEmp;
            }
            if (curEmp.next == null){
                return null;
            }
            curEmp = curEmp.next;
        }
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp() {
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return id == emp.id &&
                Objects.equals(name, emp.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}


