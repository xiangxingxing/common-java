package com.levi.dataStructures.list;


/*
*       ‼️1.头结点是否可能变，若可能变的话，就设置dummy节点‼️
*       ‼️2.需要先保存后续节点，再调整当前节点的指向‼️
* */
public class MyLinkedList {

    /*
        leetCode82:给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字
        1->2->3->3->4->4->5  :  1->2->5
    */
    public static <T extends Comparable<T>> ListNode<T> deleteDuplicate(ListNode<T> head) {
        var dummy = new ListNode<T>(null);
        dummy.next = head;
        ListNode<T> cur = dummy;
        ListNode<T> pNode = head;
        while (pNode != null && pNode.next != null) {
            //保持cur和pNode的前后关系
            if (cur.next.val.compareTo(pNode.next.val) != 0) {
                cur = cur.next;
            } else {
                while (pNode != null && pNode.next != null && cur.next.val.compareTo(pNode.next.val) == 0) {
                    pNode = pNode.next;
                }

                cur.next = pNode.next;
            }
            pNode = pNode.next;
        }

        return dummy.next;
    }

    //leetCode83:给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次
    public static <T extends Comparable<T>> ListNode<T> deleteDuplicates(ListNode<T> head) {
        ListNode<T> pNode = head;

        while (pNode != null && pNode.next != null) {
            if (pNode.val.compareTo(pNode.next.val) == 0) {
                pNode.next = pNode.next.next;
            } else {
                pNode = pNode.next;
            }
        }

        return head;

    }

    /*
        leetCode206:反转一个单链表
        1->2->3->4->5->NULL  :  5->4->3->2->1->NULL
    */
    public static <T> ListNode<T> reverseList(ListNode<T> head) {
        ListNode<T> cur = head;
        ListNode<T> pre = null;
        while (cur != null) {
            /*
            *   ‼️ 标准的反转步骤 ‼️
            * */
            ListNode<T> post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }

        return pre;
    }

    /*
    *   LeetCode92:反转从位置 m 到 n 的链表。请使用一趟扫描完成反转
    *   输入: 1->2->3->4->5->NULL, m = 2, n = 4
    *   输出: 1->4->3->2->5->NULL
    *  【以下为迭代法，同样可以使用递归法进行反转】
    * */
    public static <T> ListNode<T> reverseBetween(ListNode<T> head, int m, int n) {
        var dummy = new ListNode(0);
        dummy.next = head;

        ListNode<T> mNode = head;
        var mPreNode = dummy;

        //找第m个节点
        int i = 1;
        for (; i < m; i++){
            mPreNode = mNode;
            mNode = mNode.next;
        }

        ListNode<T> nNode = mNode;
        ListNode<T> nPostNode = nNode.next;
        int j = i;
        while (j < n){
            var pre = nNode;
            nNode = nPostNode;
            nPostNode = nNode.next;//保存
            //修改nNode指针
            nNode.next = pre;
            j++;
        }

        mNode.next = nPostNode;
        mPreNode.next = nNode;


        return dummy.next;
    }

    /*
     *   leetCode21. 合并两个有序链表
     * */
    public static <T extends Comparable<T>> ListNode<T> mergeTwoLists(ListNode<T> l1, ListNode<T> l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode<T> mergedHead;
        if (l1.val.compareTo(l2.val) <= 0) {
            mergedHead = l1;
            mergedHead.next = mergeTwoLists(l1.next, l2);
        } else {
            mergedHead = l2;
            mergedHead.next = mergeTwoLists(l1, l2.next);
        }

        return mergedHead;
    }

    /*
    * leetCode86. 分隔链表
    * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
        应当保留两个分区中每个节点的初始相对位置。
    * 输入: head = 1->4->3->2->5->2, x = 3
    * 输出: 1->2->2->4->3->5
    * */
    public static <T extends Comparable<T>> ListNode<T> partition(ListNode<T> head, T x) {
        ListNode<T> beforeHead = new ListNode(0);
        ListNode<T> before = beforeHead;
        ListNode<T> afterHead = new ListNode(0);
        ListNode<T> after = afterHead;
        ListNode<T> node = head;

        while (node != null){
            if (node.val.compareTo(x) < 0){
                before.next = node;
                before = before.next;
            }
            else{
                after.next = node;
                after = afterHead.next;
            }

            node = node.next;
        }

        after.next = null;
        before.next = afterHead.next;

        return beforeHead.next;

    }

    //LeetCode141：给定一个链表，判断链表中是否有环
    public static <T> boolean hasCycle(ListNode<T> head) {
        if (head == null){
            return false;
        }

        ListNode<T> slow = head;
        ListNode<T> fast = head.next;

        while (fast != null){
            if (fast == slow){
                return true;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast != null){
                fast = fast.next;
            }
        }

        return false;
    }

    /*
        LeetCode142：给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
    */
    public static <T> ListNode<T> detectCycle(ListNode<T> head) {
        var meetNode = meetingNode(head);
        if (meetNode == null){
            return null;
        }

        var pNode = meetNode;
        int count = 1;//环上节点个数
        //‼️注意判断的条件‼️
        //while (pNode.next != pNode){ ❌
        while (pNode.next != meetNode){ //✅ 与meetNode相比较
            pNode = pNode.next;
            count++;
        }

        //设置两个指针，一个先走count长度后，再同时前进，直到相遇
        ListNode<T> ahead = head;
        while (count > 0){
            ahead = ahead.next;
            count--;
        }
        ListNode<T> behind = head;
        while (ahead != behind){
            ahead = ahead.next;
            behind = behind.next;
        }

        return ahead;
    }

    private static <T> ListNode<T> meetingNode(ListNode<T> head){
        if (head == null){
            return null;
        }
        ListNode<T> slow = head;
        ListNode<T> fast = head.next;

        while (fast != null){
            if (fast == slow){
                return fast;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast != null){
                fast = fast.next;
            }
        }

        return null;
    }

    /*
    * leetCode234:请判断一个链表是否为回文链表
    * */
    public boolean isPalindrome(ListNode head) {
        //新建一个倒序的链表
        ListNode reversed = null;
        ListNode cur = head;
        while (cur != null){
            var node = new ListNode(cur.val);
            node.next = reversed;
            reversed = node;
            cur = cur.next;
        }

        cur = head;
        while (cur != null && reversed != null){
            if (cur.val != reversed.val){
                return false;
            }

            cur = cur.next;
            reversed = reversed.next;
        }

        return true;
    }

    /*
    * leetCode148:在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序
    * 输入: -1->5->3->4->0
    * 输出: -1->0->3->4->5
    * ‼️归并排序‼️
    *       1.找到链表的中间节点，分为左、右两个链表
    *       2.对左右分别递归调用sortList
    *       3.将【2】中得到的两个有序链表合并起来
    * */
    public static ListNode sortList(ListNode head) {
        //递归结束条件：元素个数小于2
        if (head == null || head.next == null){
            return head;
        }

        ListNode pHead = head;
        ListNode midNode = middleNode(head);
        ListNode rightHead = midNode.next;
        midNode.next = null;

        ListNode left = sortList(pHead);
        ListNode right = sortList(rightHead);

        ListNode merge = internalMerge(left, right);

        return merge;

    }

    /*  leetCode867
    * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点。
    * 输入：[1,2,3,4,5]
    * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
    * =================
    * 输入：[1,2,3,4,5,6]
    * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
    * */
    private static ListNode middleNode(ListNode head){
        if (head == null || head.next == null){
            return head;
        }

        ListNode slow = head;
        //ListNode fast = head;//leetCode867 中fast初始为head
        ListNode fast = head.next.next;//此处fast初始为head.next.next，这样获取的middleNode为中心节点的前一个，方便归并排序
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private static <T extends Comparable<T>> ListNode<T> internalMerge(ListNode<T> l1, ListNode<T> l2){
        if (l1 == null){
            return l2;
        }

        if (l2 == null){
            return l1;
        }

        ListNode<T> merged = null;
        if (l1.val.compareTo(l2.val) < 0){
            merged = l1;
            merged.next = internalMerge(l1.next, l2);
        }
        else {
            merged = l2;
            merged.next = internalMerge(l1, l2.next);
        }

        return merged;
    }

}