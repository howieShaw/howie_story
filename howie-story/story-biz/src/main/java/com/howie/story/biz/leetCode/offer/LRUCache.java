package com.howie.story.biz.leetCode.offer;

import java.util.HashMap;
import java.util.Map;


/**
 *  实现一个 Lru 缓存，当缓存数据达到 N 之后需要淘汰掉最近最少使用的数据。
 * m 小时之内没有被访问的数据也需要淘汰掉。
 */
public class LRUCache<K,V> {

    private final int capacity;
    private CacheNode<K,V> head;
    private CacheNode<K,V> tail;

    private Map<K,CacheNode<K,V> > cacheMap;

    LRUCache(int capacity) {
        this.capacity = capacity;
        Double cap = capacity*0.25+capacity;
        cacheMap = new HashMap<K, CacheNode<K,V>>(cap.intValue());
    }

    public void put(K key,V value) {
        CacheNode<K,V> node = cacheMap.get(key);
        if (node != null) {
            node.val = value;
            remove(node,false);

        } else {
            node = new CacheNode<K, V>(key,value);
            if (cacheMap.size() >= capacity) {
                remove(tail,true);
            }
            cacheMap.put(key,node);
        }
        setHead(node);

    }

    public V get(K key) {
        CacheNode<K,V> node = cacheMap.get(key);
        if (node == null) {
            return null;
        }
        remove(node,false);
        setHead(node);
        return node.val;
    }

    public void setHead(CacheNode<K,V> node) {
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


    public void remove (CacheNode<K,V> node,boolean cacheFlag) {
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

        if (cacheFlag) {
            cacheMap.remove(node.key);
        }
        node.pre = null;
        node.next = null;



    }
    private static class CacheNode<K,V> {
        V val;
        K key;
        CacheNode<K,V> pre;
        CacheNode<K,V> next;

        CacheNode (K key,V val) {
            this.key = key;
            this.val = val;
        }
        CacheNode (K key,V val,CacheNode<K,V> pre,CacheNode<K,V> next) {
            this.key = key;
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }
 }
