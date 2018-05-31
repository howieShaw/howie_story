package com.howie.story.biz.bean;

/**
 * @Author:xiaohaoyun
 * @Description： 单节点
 * @Date：created in 下午5:34 2018/5/30
 * @Modified by:xiaohaoyun
 */
public class SNode<T> {
    public T data;
    public SNode<T> next;

    public SNode () {}

    public SNode(T data, SNode next) {
        this.data = data;
        this.next = next;
    }



}
