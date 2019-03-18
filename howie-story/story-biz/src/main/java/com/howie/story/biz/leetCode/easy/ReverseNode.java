package com.howie.story.biz.leetCode.easy;


import com.howie.story.api.bean.SNode;

/**
 * Created by henry_shawn on 2017/8/23.
 */
public class ReverseNode {
    public static void main(String[] args) {
        SNode<Integer> node1 = new SNode<Integer>(1,null);
        SNode node2 = new SNode(2,node1);
        SNode node3 = new SNode(3,node2);

        print(node3);
        System.out.println();
        reverse(node3);
        print(node1);

    }

    public static void print (SNode node) {
        while (node != null) {
            System.out.print(node.data);
            node = node.next;
        }
    }

    public static void reverse(SNode node) {
        if (node == null) {
            return;
        }
        SNode pre = node;
        SNode cur = node.next;
        SNode temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        node.next = null;
    }


}
