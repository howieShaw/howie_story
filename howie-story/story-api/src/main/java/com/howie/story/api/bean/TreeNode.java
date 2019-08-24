package com.howie.story.api.bean;


public class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T val;

    public TreeNode (T val,TreeNode<T> left,TreeNode<T> right) {
        this.left = left;
        this.right = right;
        this.val = val;
    }
    public TreeNode() {
        super();
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }
}
