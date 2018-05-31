package com.howie.story.biz.process;

import com.howie.story.biz.bean.SNode;

import javax.net.ssl.SNIHostName;

/**
 * @Author:xiaohaoyun
 * @Description： 单项链表
 * @Date：created in 下午6:16 2018/5/30
 * @Modified by:xiaohaoyun
 */
public class SLinkList<T> {
    public SNode<T> top;
    public SNode<T> current;
    public int size;

    public SLinkList (SNode<T> top) {
        this.top = top;
        this.current = this.top;
        size = 1;
    }

    public void add (SNode<T> node) {
        if (node == null) {
            System.out.println("~~添加node 信息为空");
            return;
        }
        current.next = node;
        current = current.next;
        size++;
    }

    public SNode<T> getSNode (T data) {
        if (data == null|| size ==0) {
            return null;
        }
        SNode temp = top;
        int index = 0;
        while (index < size) {
            if (temp.data.equals(data)) {
                return temp;
            } else {
                temp = temp.next;
            }
        }

        return null;
    }

    public SNode<T> getSNodeByIndex (int index) {
        if (size == 0) {
            return null;
        }
        if (index >= size) {
            System.out.println("超出链表长度");
            return null;
        }

        SNode temp = top;
        int tempIndex = 0;
        while (tempIndex <= index) {
            temp = temp.next;
        }
        return temp;
    }

    public SNode<T> delete (T data) {
       if (data == null) {
           System.out.println("传入空");
       }
       SNode temp = top;
       int index = 0;
       SNode result = null;
       while (index < size) {
           SNode pre = temp;
           if (temp == null) {
               return null;
           }
           if (!temp.data.equals(data)) {
               temp = temp.next;
               continue;
           }
           result = temp;
           pre.next = temp.next;
           temp = null;
           size--;
       }

        return result;
    }



}
