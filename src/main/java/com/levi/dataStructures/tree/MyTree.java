package com.levi.dataStructures.tree;

import com.levi.dataStructures.list.MyListNode;

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

        //MyTreeNode tree2 = buildTree2(new int[]{1, 2, 3}, new int[]{2, 3, 1});
        //System.out.println(tree2);

        //24->3->10
        //24->3->2
        //24->5->60
        //24->5->22
        binaryTreePaths(root).forEach(System.out::println);

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
        //levelOrder2(root);

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
     *    LeetCode104. 二叉树的最大深度
     * */
    public static <T> int maxDepth(MyTreeNode<T> root) {
        if (Objects.isNull(root)) {
            return 0;
        }

        int lDepth = maxDepth(root.left);
        int rDepth = maxDepth(root.right);

        return Math.max(lDepth, rDepth) + 1;
    }

    /**
     * 分治思想
     *    LeetCode111. 二叉树的最小深度
     * */
    public int minDepth(MyTreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null){
            return minDepth(root.right) + 1;
        }
        if(root.right == null){
            return minDepth(root.left) + 1;
        }

        int lDepth = minDepth(root.left);
        int rDepth = minDepth(root.right);

        return Math.min(lDepth, rDepth) + 1;
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

    private static final int NOT_BALANCED = -1;
    //leetCode110 是否平衡 --> 后续遍历，保证每个节点只遍历一次
    public static <T> boolean isBalanced(MyTreeNode<T> root) {
        return balanced(root) != NOT_BALANCED;
    }

    private static  <T> int balanced(MyTreeNode<T> root) {
        if (Objects.isNull(root)) {
            return 0;
        }

        int left = balanced(root.left);
        if (left == NOT_BALANCED) {
            return left;
        }

        int right = balanced(root.right);
        if (right == NOT_BALANCED) {
            return right;
        }

        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : NOT_BALANCED;
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
        else if (root.value.compareTo(val) < 0) {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    /**
    *   LeetCode108. 将有序数组转换为二叉搜索树
     *  将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树
    * */
    public MyTreeNode sortedArrayToBST(int[] nums) {
        return insertBST(nums,0, nums.length - 1);
    }

    public MyTreeNode insertBST(int[] nums, int left, int right){
        if(left > right){
            return null;
        }
        int mid = (left + right) >>> 1;
        MyTreeNode root = new MyTreeNode(nums[mid]);
        root.left = insertBST(nums, left, mid - 1 );
        root.right = insertBST(nums, mid + 1, right);

        return root;
    }

    /**
     *  LeetCode109.有序链表转换二叉搜索树
     *  给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     * */
    public MyTreeNode sortedListToBST(MyListNode head) {
        MyListNode pre = getMiddlePre(head);
        if (pre == null) {
            return null;
        }

        if (pre.next == null) {
            return new MyTreeNode((pre.val));
        }
        MyListNode mid = pre.next;
        pre.next = null;
        MyTreeNode root = new MyTreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);

        return root;
    }

    private MyListNode getMiddlePre(MyListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        MyListNode slow = head;
        MyListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
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

    /**
     * LeetCode100. 相同的树
     */
    public boolean isSameTree(MyTreeNode p, MyTreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.value != q.value) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * LeetCode700. 二叉搜索树中的搜索
     */
    public static <T extends Comparable<T>> MyTreeNode<T> searchBST(MyTreeNode<T> root, T val) {
        if (root == null) return null;

        if (root.value.compareTo(val) > 0) {
            return searchBST(root.left, val);
        }

        if (root.value.compareTo(val) < 0) {
            return searchBST(root.right, val);
        }

        return root;
    }

    //非递归
    public static <T extends Comparable<T>> MyTreeNode<T> searchBST2(MyTreeNode<T> root, T val) {
        MyTreeNode<T> p = root;
        while (p != null && p.value.compareTo(val) != 0) {
            p = p.value.compareTo(val) > 0 ? p.left : p.right;
        }

        return p;
    }

    /**
     * LeetCode450. 删除二叉搜索树中的节点
     * 1.找到对应value的结点
     * 2.结点三种情况：
     *              本身为叶子结点
     *              仅有左子树或右子树
     *              左右子树均有
     */
    public static <T extends Comparable<T>> MyTreeNode<T> deleteNode(MyTreeNode<T> root, T key) {
        if (root == null) return null;
        if (root.value.compareTo(key) == 0) {
            //删除结点
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;

            var minNode = getMin(root.right);
            root.value = minNode.value;
            root.right = deleteNode(root.right, minNode.value);


        } else if (root.value.compareTo(key) > 0) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    private static <T extends Comparable<T>> MyTreeNode<T> getMin(MyTreeNode<T> node) {
        MyTreeNode<T> p = node;
        while (p.left != null) {
            p = p.left;
        }

        return p;
    }

    /**
     * LeetCode105.根据一棵树的前序遍历与中序遍历构造二叉树。
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     *
     * */
    public static MyTreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length < 1){
            return null;
        }

        var root = new MyTreeNode<>(preorder[0]);
        int lLen = 0;
        int i = 0;
        while (inorder[i] != root.value) {
            i++;
            lLen++;
        }
        int rLen = inorder.length - i - 1;
        if (lLen > 0) {
            root.left = buildTree(Arrays.copyOfRange(preorder, 1, lLen + 1), Arrays.copyOf(inorder, lLen));
        }

        if (rLen > 0){
            root.right = buildTree(Arrays.copyOfRange(preorder, lLen + 1, lLen + 1 + rLen),
                    Arrays.copyOfRange(inorder, i + 1, i + 1 + rLen));
        }

        return root;
    }

    /**
     * 优化
     * LeetCode105.根据一棵树的前序遍历与中序遍历构造二叉树。
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     *
     * l1,h1为先序遍历下的第一和最后一个结点坐标
     * [1,2,3]
     * [2,3,1]
     *
     * */
    public static MyTreeNode buildTree2(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static MyTreeNode buildTree(int[] preorder, int l1, int h1, int[] inorder, int l2, int h2 ) {
        if (l1 > h1 || l2 > h2){
            return null;
        }

        var root = new MyTreeNode<>(preorder[l1]);
        int i = l2;
        int lLen = 0;
        while (inorder[i] != root.value){
            i++;
            lLen++;//左子树长度
        }
        int rLen = h2 - l2 - lLen;//右子树长度
        if (lLen > 0){
            root.left = buildTree(preorder, l1 + 1, l1 + lLen, inorder, l2, l2 + lLen - 1);
        }

        if (rLen > 0){
            root.right = buildTree(preorder, h1 - rLen + 1, h1, inorder, h2 - rLen + 1, h2);
        }

        return root;
    }

    /**
     * 480. 二叉树的所有路径
     * 给一棵二叉树，找出从根节点到叶子节点的所有路径
     * 输入：{1,2,3,#,5}
     * 输出：["1->2->5","1->3"]
     * 解释：
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     * */
    public static List<String> binaryTreePaths(MyTreeNode root) {
        // write your code here
        List<String> paths = new ArrayList<>();
        if (root == null){
            return paths;
        }

        if (root.left == null && root.right == null){
            paths.add(root.value.toString());
            return paths;
        }

        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);

        for (String p : leftPaths) {
            paths.add(root.value + "->" + p);
        }

        for (String p : rightPaths) {
            paths.add(root.value + "->" + p);
        }

        return paths;
    }

    /**
    *   二叉搜索树的生成
     *  LeetCode95.不同的二叉搜索树 II
     *      给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树
    * */

}
