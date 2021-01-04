package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author chenhuiup
 * @create 2020-08-13 上午 8:44
 */
public class CiecleArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        CircleArray queue = new CircleArray(4);//说明设置4，其队列的有效数据个数为3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            System.out.println("s(size):查看队列中的数据总数");
            System.out.println("********************************");

            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g'://取出
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        ;
                    }
                    break;
                case 'h'://查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 't':
                    int res = queue.size();
                    System.out.printf("队列中元素的总个数为%d\n", res);
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出---");
    }
}


class CircleArray {
    private int maxSize;  //表示数组的最大容量
    //front 变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]
    //front的初始化值 = 0
    private int front;  //队列头
    //rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个
    //rear 的初始值 = 0
    private int rear;   //队列尾
    private int[] arr;  //该数组用于存放数据，模拟队列

    //创建队列的构造器
    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[rear] = n;
        //rear后移
        rear = (rear + 1) % maxSize;
    }

    //获取队列中的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        //2.将front后移，考虑取模
        //3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = front; i <= size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示队列的头数据，注意不是取数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("队列为空的，没有数据--");
        }
        return arr[front];
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}
