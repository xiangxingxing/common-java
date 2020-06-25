package com.levi.dataStructures.tree;

public class MyTreeNode<T> {
    public T value;
    public MyTreeNode<T> left;
    public MyTreeNode<T> right;

    public MyTreeNode(T value) {
        this.value = value;
    }

    MyTreeNode(T val, MyTreeNode<T> left, MyTreeNode<T> right) {
         this.value = val;
         this.left = left;
         this.right = right;
     }

    public MyTreeNode<T> setLeft(T value){
        this.left = new MyTreeNode<>(value);
        return left;
    }

    public MyTreeNode<T> setRight(T value){
        this.right = new MyTreeNode<>(value);
        return right;
    }
}
