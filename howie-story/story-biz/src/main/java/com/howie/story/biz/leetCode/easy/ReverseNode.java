//package com.howie.story.biz.leetCode.easy;
//
//import com.howie.main.construstor.Node;
//
///**
// * Created by henry_shawn on 2017/8/23.
// */
//public class ReverseNode {
//    public static void main(String[] args) {
//        Node node1 = new Node(1,null);
//        Node node2 = new Node(2,node1);
//        Node node3 = new Node(3,node2);
//
//        print(node3);
//        System.out.println();
//        reverse(node3);
//        print(node1);
//
//    }
//
//    public static void print (Node node) {
//        while (node != null) {
//            System.out.print(node.data);
//            node = node.next;
//        }
//    }
//
//    public static void reverse(Node node) {
//        if (node == null) {
//            return;
//        }
//        Node pre = node;
//        Node cur = node.next;
//        Node temp;
//        while (cur != null) {
//            temp = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = temp;
//        }
//        node.next = null;
//    }
//
//}
