package com.howie.story.biz.leetCode.easy;

import com.howie.story.api.bean.SNode;
import com.howie.story.biz.leetCode.Base;
import com.howie.story.biz.util.BaseUtil;
import com.howie.story.biz.util.NodeUtils;

import java.util.HashMap;

/**
 * @Author:xiaohaoyun
 * @Description： 将两个链表的值相加成一个新的链表
 * @Date：created in 下午4:20 2018/8/29
 * @Modified by:xiaohaoyun
 */
public class AddTwoNumbers_02 {

    public static void main(String[] args) {
        SNode<Integer> node1 = NodeUtils.createSNode(3);
        SNode<Integer> node2 = NodeUtils.createSNode(5);

        BaseUtil.traversSNode(node1);
        BaseUtil.traversSNode(node2);

        SNode<Integer> node = addTwoNodes(node1,node2);
        BaseUtil.traversSNode(node);

    }

    public static SNode<Integer> addTwoNodes(SNode<Integer> node1,SNode<Integer> node2) {
        SNode<Integer> sNode = null;
        int index1=1,index2 = 1;
        HashMap<Integer,Integer> nodeMap = new HashMap<Integer, Integer>();
        while (node1 != null) {
            nodeMap.put(index1,node1.data);
            node1 = node1.next;
            index1++;
        }

        HashMap<Integer,Integer> nodeMap2 = new HashMap<Integer, Integer>();
        while (node2 !=null) {
            nodeMap2.put(index2,node2.data);
            node2 = node2.next;
            index2++;
        }
        int max = index1 >= index2 ? index1 : index2;
        int index = 1;

        while (index < max) {
            int val1 = 0;
            if (nodeMap.containsKey(index)) {
                val1 = nodeMap.get(index);
            }
            int val2 = 0;
            if (nodeMap2.containsKey(index)) {
                val2 = nodeMap2.get(index);
            }
            int val = val1 + val2;
            if (sNode != null) {
                SNode<Integer> node = new SNode<Integer>();
                node.data = val;
                SNode<Integer> temp = sNode;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = node;
            } else {
                sNode = new SNode<Integer>();
                sNode.data=val;
            }
            index++;

        }

        return sNode;
    }

    public static SNode<Integer> createNode(int val) {
        SNode<Integer> node = new SNode<Integer>();
        int init = val;
        while (val>0) {
            if (init != val) {
                SNode<Integer> sNode = new SNode<Integer>();
                sNode.data = val;
                SNode temp = node;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = sNode;
            } else {
                node = new SNode<Integer>();
                node.data = val;
            }
            val--;
        }
        return node;
    }
}
