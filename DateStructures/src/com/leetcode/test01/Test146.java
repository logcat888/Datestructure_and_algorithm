package com.leetcode.test01;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author chenhuiup
 * @create 2020-11-21 13:32
 */
/*
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
实现 LRUCache 类：

LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lru-cache
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test146 {
    public static void main(String[] args) {

    }
}

/*
 使用单链表实现思路：
 1.使用HashMap实现key -value的保存，方便快速查找
 2.使用单链表管理key：
    1）插入数据：
    1.1）先判断插入的数据是否已经存在（HashMap判断），如果不存在
    1.1.1）先判断是否达到容量，如果达到容量则删除尾部节点和HashMap中的key，同时在head节点后插入新节点，在HashMap中也插入新节点。
    1.2.1）如果没有达到容量直接在head节点后插入新节点，同时HashMap中也插入新节点。
    1.2）如果存在，直接更新HashMap中的value。
    2）获取数据：
    先用HashMap判断是否存在，如果存则返回，并将单链表中的节点移动到head节点后面。
    3）单链表：
    节点类（value，next），单链表类（head节点、增（只在head节点后添加）、删）


 */

class LRUCache {


    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)){
            // 如果存则返回，并将单链表中的节点移动到head节点后面。
            linkedList.remove(key);

        }
            return 0;
    }

    public void put(int key, int value) {

    }

    // 定义一个LinkedList保存key，作为LRU
    private LinkedList<Integer> linkedList = new LinkedList<Integer>();

    // 定义一个HashMap保存数据
    private HashMap<Integer, Integer> map = new HashMap<>();

    private int capacity;
}