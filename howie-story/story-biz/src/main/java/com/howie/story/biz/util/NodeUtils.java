package com.howie.story.biz.util;

import com.howie.story.api.bean.SLinkList;
import com.howie.story.api.bean.SNode;

/**
 * @Author:xiaohaoyun
 * @Description： 节点操作工具类
 * @Date：created in 下午7:08 2018/6/4
 * @Modified by:xiaohaoyun
 */
public class NodeUtils {

    /**
     * 单链表反转
     *
     * @param topNode 链表头节点
     */
    public static SNode reverseNode (SNode topNode) {
        if (topNode == null || topNode.next == null) {
            System.out.println("头节点或头节点的下个节点为空");
            return topNode;
        }
        SNode pre = topNode;
        SNode cur = topNode.next;
        SNode temp;
        pre.next = null;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;

    }

    public static void main(String[] args) {
        SLinkList<Integer> sLinkList = new SLinkList<Integer>(new SNode<Integer>(1,null));
        sLinkList.add(new SNode<Integer>(2,null));
        sLinkList.add(new SNode<Integer>(3,null));
        sLinkList.printLink();
        SNode<Integer> top = sLinkList.top;
        top = reverseNode(top);
        while (top != null) {
            if (top.next != null) {
                System.out.print(top.data+"-> ");
            } else {
                System.out.println(top.data);
            }
            top =top.next;
        }
    }

    public static SNode<Integer> createSNode (int val) {
        SLinkList<Integer> sLinkList = new SLinkList<Integer>(new SNode<Integer>(val,null));
        while (val >0){
            --val;
            sLinkList.add(new SNode<Integer>(val,null));
        }

        return sLinkList.top;

    }
}
