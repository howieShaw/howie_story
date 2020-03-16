package com.howie.story.biz.leetCode.offer;

import com.howie.story.api.bean.TreeNode;

/**
 * 输出一棵二叉树的镜像，将树的左右结点进行交换。
 */
public class MirrorRecursively<T> {

    public void mirrorRecursively (TreeNode<T> treeNode) {

        if (treeNode == null) return;

        if (treeNode.getLeft() == null && treeNode.getRight() == null) return;

        //将左结点存入临时结点
        TreeNode<T> tempLeft = treeNode.getLeft();
        //交换左右结点
        treeNode.setLeft(treeNode.getRight());
        treeNode.setRight(tempLeft);

        if (treeNode.getLeft() != null) {
            mirrorRecursively(treeNode.getLeft());
        }

        if (treeNode.getRight() !=null) {
            mirrorRecursively(treeNode.getRight());
        }

    }
}
