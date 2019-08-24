package com.howie.story.biz.leetCode.easy;

import com.howie.story.api.bean.SLinkList;
import com.howie.story.api.bean.SNode;

/**
 * Created by henry_shawn on 2017/4/15.
 */
public class MergeTwoSortedLists_21 {
    /**
     * Merge two sorted linked lists and return it as a new list. The new list should be made by
     * splicing together the nodes of the first two lists.
     */

    public static void main(String[] args) {
        SNode<Integer> top = new SNode<Integer>(1,null);
        SLinkList<Integer> linkList1 = new SLinkList<Integer>(top);

        linkList1.add(new SNode<Integer>(3));
        linkList1.add(new SNode<Integer>(5));
        linkList1.add(new SNode<Integer>(7));
        linkList1.add(new SNode<Integer>(9));
        linkList1.add(new SNode<Integer>(11));

        SNode<Integer> top2 = new SNode<Integer>(1,null);
        SLinkList<Integer> linkList2 = new SLinkList<Integer>(top2);
        linkList2.add(new SNode<Integer>(2,null));
        linkList2.add(new SNode<Integer>(4));
        linkList2.add(new SNode<Integer>(5));
        linkList2.add(new SNode<Integer>(9));
        linkList2.add(new SNode<Integer>(10));
        linkList2.add(new SNode<Integer>(13));

        SNode node = merge(linkList1.top,linkList2.top);
        while (node != null){
            node = node.next;
        }



    }

    public static SNode merge(SNode<Integer> top1,SNode<Integer> top2){

        if (top1 == null) {
            return top1;
        }
        if (top2 == null) {
            return top2;
        }
        SNode<Integer> mergeNode;
        if (top1.data.compareTo(top2.data) > 0) {
            mergeNode = top2;
            mergeNode.next = merge(top1,mergeNode.next);
        }else {
            mergeNode = top1;
            mergeNode.next = merge(mergeNode.next,top2);

        }
        return mergeNode;
    }
}
