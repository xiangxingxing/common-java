package com.levi.dataStructures.tree;

import java.util.*;

public class MyTree {
    /*
    * example tree
    *               24
    *       3               5
    *   10        2       60      22
    *
    * */
    public static void main(String[] args) {
        var root = new MyTreeNode<>(24);
        var left = root.setLeft(3);
        left.setLeft(10);
        left.setRight(2);

        var right = root.setRight(5);
        right.setLeft(60);
        right.setRight(22);

        //inOrder(root);//should be : 10 3 2 24 60 5 22
        //inOrderNonRecursion(root);

        //preOrder(root);//should be : 24 3 10 2 5 60 22
        //System.out.print("\n");
        //preOrderNonRecursive(root);
        levelOrder(root);
    }

    public static <T> void preOrder(MyTreeNode<T> root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

    }

    public static <T> void inOrder(MyTreeNode<T> root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.value + " ");
            inOrder(root.right);
        }
    }


    public static <T> void inOrderNonRecursive(MyTreeNode<T> root) {
        var stack = new Stack<MyTreeNode<T>>();
        MyTreeNode<T> p = root;
        while (!Objects.isNull(p) || !stack.isEmpty()) {
            if (Objects.isNull(p)) {
                p = stack.pop();
                System.out.print(p.value + " ");
                p = p.right;
            } else {
                stack.push(p);
                p = p.left;
            }
        }
    }

    public static <T> void preOrderNonRecursive(MyTreeNode<T> root){
        var stack = new Stack<MyTreeNode<T>>();
        MyTreeNode<T> p = root;
        while (!Objects.isNull(p) || !stack.isEmpty()){
            if (!Objects.isNull(p)){
                System.out.print(p.value + " ");
                stack.push(p);
                p = p.left;
            }
            else {
                p = stack.pop();
                p = p.right;
            }
        }
    }

    //DFS


    //BFS
    public static <T> void levelOrder(MyTreeNode<T> root){
        if (Objects.isNull(root)){
            return;
        }
        var queue = new LinkedList<MyTreeNode<T>>();
        queue.add(root);

        while (!queue.isEmpty()){
            MyTreeNode<T> node = queue.poll();//检索并删除此列表的头（第一个元素）
            System.out.print(node.value + " ");//Should be :24 3 5 10 2 60 22
            if (!Objects.isNull(node.left)){
                queue.add(node.left);
            }

            if (!Objects.isNull(node.right)){
                queue.add(node.right);
            }
        }
    }
}
