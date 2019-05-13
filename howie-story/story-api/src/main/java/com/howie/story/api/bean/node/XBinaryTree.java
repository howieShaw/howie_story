package com.howie.story.api.bean.node;

import com.howie.story.api.bean.SNode;
import com.howie.story.api.bean.TreeNode;
import com.howie.story.api.bean.api.QueueApi;

import java.util.*;

public class XBinaryTree {
    public TreeNode<Integer> root;


    public boolean insert(int val) {
        TreeNode<Integer> node = new TreeNode<>(val,null,null);

        if (root == null) {
            root = node;
            return true;
        }

        TreeNode<Integer> curNode = root;


        while (true) {
            TreeNode<Integer> parentNode = curNode;

            //如果新的节点大于当前节点，往节点的右变放入
            if (node.getVal() > curNode.getVal()) {
                curNode = curNode.getRight();
                if (curNode == null) {
                    parentNode.setRight(node);
                    return true;
                }
            } else {
                //新的节点没有当前节点大，放当前节点的左边
                curNode = curNode.getLeft();
                if (curNode == null) {
                    parentNode.setLeft(node);
                    return true;
                }
            }
        }

    }

    /**
     *  ------------------------------使用递归方法遍历 start -----------------------
     *
     */
    public void traverseBeforeDiGui (TreeNode<Integer> node) {

        if (node == null) {
            return;
        }
        System.out.print(node.getVal());
        traverseBeforeDiGui(node.getLeft());
        traverseBeforeDiGui(node.getRight());

    }

    public void traverseMidDiGui (TreeNode<Integer> node) {

        if (node == null) {
            return;
        }

        traverseMidDiGui(node.getLeft());
        System.out.print(node.getVal());
        traverseMidDiGui(node.getRight());
    }

    public void traverseAfterDiGui (TreeNode<Integer> node) {

        if (node == null) {
            return;
        }

        traverseAfterDiGui(node.getLeft());
        traverseAfterDiGui(node.getRight());
        System.out.print(node.getVal());

    }

    /**
     *  ------------------------------使用递归方法遍历 end -----------------------
     */

    /**
     *
     * @return 深度优先遍历,前序遍历
     */
    public List<Integer> traverseDepthFirst () {
        return traverseDepthFirst(root);
    }

    private List<Integer> traverseDepthFirst (TreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }

        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()) {
            TreeNode<Integer> treeNode = stack.pop();

            //先压右节点再压左节点，这样出栈就是先左再右了
            if (treeNode.getRight() != null) {
                stack.push(treeNode.getRight());
            }

            if (treeNode.getLeft() != null) {
                stack.push(treeNode.getLeft());
            }
            list.add(treeNode.getVal());
        }

        return list;
    }

    /**
     * 广度优先遍历
     * @return
     */
    public List<Integer> traverseBreadthFirst() {

        return traverseBreadthFirst(root);
    }

    private List<Integer> traverseBreadthFirst(TreeNode<Integer> root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<Integer> treeNode = queue.poll();
            if (treeNode.getLeft() != null) {
                queue.offer(treeNode.getLeft());
            }

            if (treeNode.getRight() != null) {
                queue.offer(treeNode.getRight());
            }

            list.add(treeNode.getVal());
        }

        return list;
    }

    public List<Integer> traverseMidList () {
        return traverseMidList(root);
    }

    /**
     *  非递归中序遍历
     * @param root
     * @return
     */
    public List<Integer> traverseMidList (TreeNode<Integer> root) {
        List<Integer> list =new ArrayList<>();

        if (root == null) {
            return null;
        }
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode<Integer> treeNode = stack.peek();
            if (treeNode.getLeft() == null) {
                TreeNode<Integer> left = stack.pop();
                list.add(left.getVal());
                if (treeNode.getRight() != null) {
                    stack.push(treeNode.getRight());
                }
            } else {
                stack.push(treeNode.getLeft());
                treeNode.setLeft(null);
            }
        }

        return list;

    }

    /**
     *  非递归后续遍历
     * @param node
     * @return
     */
    public List<Integer> traverseAfter (TreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }

        Stack<TreeNode<Integer>> stack = new Stack<>();

        stack.push(node);
        while (!stack.empty()) {
            TreeNode<Integer> treeNode = stack.peek();
            if (treeNode.getLeft() == null && treeNode.getRight() == null) {
                list.add(stack.pop().getVal());
                continue;
            }

            if (treeNode.getRight() !=null) {
                stack.push(treeNode.getRight());
                treeNode.setRight(null);
            }

            if (treeNode.getLeft() != null) {
                stack.push(treeNode.getLeft());
                treeNode.setLeft(null);
            }
        }

        return list;
    }


}
