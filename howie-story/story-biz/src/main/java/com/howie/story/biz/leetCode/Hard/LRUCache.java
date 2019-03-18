package com.howie.story.biz.leetCode.Hard;

import java.util.HashMap;
import java.util.Map;

/**
 *  LRU(Least recently used) 最少使用原子，
 *  当内存中达到一定数量定期删除
 */
public class LRUCache {
    private final int capacity;
    private CacheNode head;
    private CacheNode tail;

    private Map<Integer,CacheNode> cacheMap;

    LRUCache(int capacity) {
        this.capacity = capacity;
        Double cap = capacity*0.25+capacity;
        cacheMap = new HashMap<Integer, CacheNode>(cap.intValue());
    }

    public void put(int key,int value) {
        CacheNode node = cacheMap.get(key);
        if (node != null) {
            node.val = value;
            remove(node,false);

        } else {
            node = new CacheNode(key,value);
            if (cacheMap.size() >= capacity) {
                remove(tail,true);
            }
            cacheMap.put(key, node);

        }
        setHead(node);

    }

    public int get(int key) {
        CacheNode node = cacheMap.get(key);
        if (node == null) {
            return -1;
        }
        remove(node,false);
        setHead(node);
        return node.val;
    }

    public void setHead(CacheNode node) {
        if (head == null) {
            head = node;

        } else {
            node.next = head;
            head.pre = node;
        }
        head = node;
        if (tail == null) {
            tail = node;
        }
    }


    public void remove (CacheNode node,boolean cacheFlag) {
        if (node == null) {
            return;
        }

        if (node.pre != null) {
            //删除非头节点
            node.pre.next = node.next;
        } else {
            //删除头节点
            head = node.next;
        }
        if (node.next !=null) {
            node.next.pre = node.pre;
        } else {
            tail = node.pre;
        }
        node.pre = null;
        node.next = null;

        if (cacheFlag) {
            cacheMap.remove(node.key);
        }



    }
    private static class CacheNode {
        int val;
        int key;
        CacheNode pre;
        CacheNode next;

        CacheNode (int key,int val) {
            this.key = key;
            this.val = val;
        }
        CacheNode (int key,int val,CacheNode pre,CacheNode next) {
            this.key = key;
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }
}
