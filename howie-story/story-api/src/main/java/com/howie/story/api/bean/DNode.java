package com.howie.story.api.bean;

/**
 * @Author:xiaohaoyun
 * @Description： 双向节点
 * @Date：created in 下午5:37 2018/5/30
 * @Modified by:xiaohaoyun
 */
public class DNode<T> {
    public T data;
    public DNode pre;
    public DNode next;

    public DNode () {}

    public DNode (T data,DNode pre,DNode next) {
        this.data = data;
        this.pre  = pre;
        this.next = next;
    }
}
