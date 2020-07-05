package com.levi.dataStructures.tree;

import java.util.*;

public class MyTree {
    /**
     * example tree
     *               24
     *        3               5
     *   10       2       60      22
     *
     * */
    public static void main(String[] args) {
        var root = new MyTreeNode<Integer>(24);
        var left = root.setLeft(3);
        left.setLeft(10);
        left.setRight(2);

        var right = root.setRight(5);
        var r1 = right.setLeft(60);
        right.setRight(22);

        //中序遍历
        //inOrder(root);//should be : 10 3 2 24 60 5 22
        //inOrderNonRecursion(root);

        //前序遍历
        //preOrder(root);//should be : 24 3 10 2 5 60 22
        //System.out.print("\n");
        //preOrderNonRecursive(root);

        //后序遍历 should be : 10 2 3 60 22 5 24
        //postOrder(root);
        //postOrderNonRecursive(root);

        //层次遍历
        //levelOrder(root);
        levelOrder2(root);

        //求树的深度
        //System.out.println(maxDepth(root));

        //判断树是否平衡
        //System.out.println(isBalanced(root));

        //最近公共祖先
        //var node = lowestCommonAncestor(root, left, r1);
        //System.out.println(node.value);
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

    public static <T> void preOrderNonRecursive(MyTreeNode<T> root) {
        var stack = new Stack<MyTreeNode<T>>();
        MyTreeNode<T> p = root;
        while (!Objects.isNull(p) || !stack.isEmpty()) {
            if (!Objects.isNull(p)) {
                System.out.print(p.value + " ");
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                p = p.right;
            }
        }
    }

    //后序遍历
    public static <T> void postOrder(MyTreeNode<T> root) {
        if (!Objects.isNull(root)) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.value + " ");
        }
    }

    public static <T> void postOrderNonRecursive(MyTreeNode<T> root) {
        var stack = new Stack<MyTreeNode<T>>();
        var p = root;
        MyTreeNode<T> visited = null;//记录最近访问过的节点
        while (!Objects.isNull(p) || !stack.empty()) {
            if (!Objects.isNull(p)) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.peek();
                if (!Objects.isNull(p.right) && visited != p.right) {
                    p = p.right;
                    stack.push(p);
                    p = p.left;
                } else {
                    p = stack.pop();
                    System.out.print(p.value + " ");
                    visited = p;
                    p = null;
                }
            }
        }
    }

    //DFS


    /**
     * 层次遍历借助队列
     * @param root 树
     * @param <T>  存储类型
     */
    public static <T> void levelOrder(MyTreeNode<T> root) {
        if (Objects.isNull(root)) {
            return;
        }
        var queue = new LinkedList<MyTreeNode<T>>();
        queue.add(root);

        while (!queue.isEmpty()) {
            MyTreeNode<T> node = queue.poll();//检索并删除此列表的头（第一个元素）
            System.out.print(node.value + " ");//Should be :24 3 5 10 2 60 22
            if (!Objects.isNull(node.left)) {
                queue.offer(node.left);
            }

            if (!Objects.isNull(node.right)) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 剑指32:从上到下打印树
     *  ，
     *  ，，
     *  ，，，，
    * */
    public static <T> void levelOrder2(MyTreeNode<T> root){
        if (root == null){
            return;
        }

        var queue = new LinkedList<MyTreeNode<T>>();
        queue.add(root);
        int nextLevel = 0;//表示下一层节点的数目
        int toBePrint = 1;//表示当前层还没有打印的个数
        while (!queue.isEmpty()){
            MyTreeNode<T> node = queue.poll();
            System.out.print(node.value + " ");
            toBePrint--;
            if (node.left != null){
                queue.offer(node.left);
                nextLevel++;
            }
            if (node.right != null){
                queue.offer(node.right);
                nextLevel++;
            }

            if (toBePrint == 0){
                System.out.println();
                toBePrint = nextLevel;
                nextLevel = 0;
            }
        }

    }

    /**
     * 分治思想
     *    求树的深度
     *
     * */
    public static <T> int maxDepth(MyTreeNode<T> root) {
        if (Objects.isNull(root)) {
            return 0;
        }

        int lDepth = maxDepth(root.left);
        int rDepth = maxDepth(root.right);

        return Math.max(lDepth, rDepth) + 1;
    }

    //leetCode98 验证是否有效的二叉排序树
    public  <T extends Comparable<T>> boolean isValidBST(MyTreeNode<T> root){
        return isValidBST(root, null, null);
    }

    private  <T extends Comparable<T>> boolean isValidBST(MyTreeNode<T> root, T lower, T upper){
        if (Objects.isNull(root)){
            return true;//空树也是BST
        }
        T val = root.value;
        if (lower != null && val.compareTo(lower) <= 0) return false;//下界
        if (upper != null && val.compareTo(upper) >= 0) return false;//上界

        //中序遍历
        /*
        *   root的val是左子树的上界，右子树的下界
        * */
        var b1 = isValidBST(root.left, lower, val);
        var b2 = isValidBST(root.right, val, upper);
        return b1 && b2;
    }

    //leetCode110 是否平衡 --> 后续遍历，保证每个节点只遍历一次
    public static <T> boolean isBalanced(MyTreeNode<T> root) {
        return balanced(root) != -1;
    }

    private static  <T> int balanced(MyTreeNode<T> root) {
        if (Objects.isNull(root)) {
            return 0;
        }

        int left = balanced(root.left);
        if (left == -1) {
            return left;
        }

        int right = balanced(root.right);
        if (right == -1) {
            return right;
        }

        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    //leetCode236 两节点最近公共祖先
    public static <T> MyTreeNode<T> lowestCommonAncestor(MyTreeNode<T> root, MyTreeNode<T> p1, MyTreeNode<T> p2) {

        if (root == p1 || root == p2 || root == null) {
            return root;
        }

        var left = lowestCommonAncestor(root.left, p1, p2);
        var right = lowestCommonAncestor(root.right, p1, p2);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }

    }

    //leetCode701 二叉搜索树中的插入操作
    public static <T extends Comparable<T>> MyTreeNode<T> insertIntoBST(MyTreeNode<T> root, T val) {
        if (Objects.isNull(root)){
            root = new MyTreeNode<T>(val);
        }

        else if (root.value.compareTo(val) > 0){
            root.left = insertIntoBST(root.left, val);
        }
        else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    //LeetCode101.对称二叉树:给定一个二叉树，检查它是否是镜像对称的
    public boolean isSymmetric(MyTreeNode root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(MyTreeNode p1, MyTreeNode p2) {
        if (p1 == null && p2 == null){
            return true;
        }
        if (p1 == null || p2 == null){
            return false;
        }
        if (p1.value != p2.value){
            return false;
        }

        return isSymmetric(p1.left, p2.right) && isSymmetric(p1.right, p2.left);
    }

}
