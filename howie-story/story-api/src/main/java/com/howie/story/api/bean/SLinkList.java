package com.howie.story.api.bean;

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
        this.current =top;
        size = 1;
    }

    public void add (SNode<T> node) {
        if (node == null) {
            System.out.println("~~添加node 信息为空");
            return;
        }
        if (top == current) {
            top.next = current;
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

    public void printLink () {
        if (top == null || size <=0) {
            return;
        }
        int index = 0;
        SNode temp = top;
        while (temp != null) {
            System.out.println("index : "+index+" , data : "+temp.data);
            temp = temp.next;
            index++;
        }
    }

    public static void main(String[] args) {
        SNode<Integer> sNode = new SNode<Integer>();
        sNode.data = 1;
        SLinkList<Integer> sLinkList = new SLinkList<Integer>(sNode);
        sLinkList.add(new SNode<Integer>(2,null));
        sLinkList.add(new SNode<Integer>(3,null));
        sLinkList.add(new SNode<Integer>(4,null));

        System.out.println("get 4 :"+sLinkList.getSNode(4));

        sLinkList.printLink();
    }

}
