//package com.howie.story.biz.leetCode.easy;
//
//import com.howie.main.construstor.Node;
//import com.howie.main.construstor.SingleLinkList;
//
///**
// * Created by henry_shawn on 2017/4/15.
// */
//public class MergeTwoSortedLists_21 {
//    /**
//     * Merge two sorted linked lists and return it as a new list. The new list should be made by
//     * splicing together the nodes of the first two lists.
//     */
//
//    public static void main(String[] args) {
//        SingleLinkList<Integer> linkList1 = new SingleLinkList<>();
//        linkList1.addTopNode(1);
//        linkList1.addTailNode(3);
//        linkList1.addTailNode(5);
//        linkList1.addTailNode(7);
//        linkList1.addTailNode(9);
//        linkList1.addTailNode(11);
//
//        SingleLinkList<Integer> linkList2 = new SingleLinkList<>();
//        linkList2.addTopNode(0);
//        linkList2.addTailNode(2);
//        linkList2.addTailNode(4);
//        linkList2.addTailNode(6);
//        linkList2.addTailNode(8);
//        linkList2.addTailNode(10);
//
//        Node node = merge(linkList1.getTop(),linkList2.getTop());
//        while (node != null){
//            node.display();
//            node = node.next;
//        }
//
//
//
//    }
//
//    public static Node merge(Node<Integer> top1,Node<Integer> top2){
//
//        if (top1 == null) {
//            return top1;
//        }
//        if (top2 == null) {
//            return top2;
//        }
//        Node<Integer> mergeNode;
//        if (top1.data.compareTo(top2.data) > 0) {
//            mergeNode = top2;
//            mergeNode.next = merge(top1,mergeNode.next);
//        }else {
//            mergeNode = top1;
//            mergeNode.next = merge(mergeNode.next,top2);
//
//        }
//        return mergeNode;
//    }
//}
