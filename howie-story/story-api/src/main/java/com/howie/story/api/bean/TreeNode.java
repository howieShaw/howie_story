package com.howie.story.api.bean;


public class TreeNode<T> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    public T val;

    TreeNode (T val,TreeNode<T> left,TreeNode<T> right) {
        this.left = left;
        this.right = right;
    }
    TreeNode() {
        super();
    }

}
