package com.levi.dataStructures.stack;

import java.util.*;

public class MyStack {
    //LeetCode225. 用队列实现栈
    private Queue<Integer> queue;
    private int topItem;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        this.queue = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        queue.offer(x);
        topItem = x;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        int i = queue.size();
        while (i > 2) {
            queue.offer(queue.poll());
            i--;
        }
        topItem = queue.poll();
        queue.offer(topItem);

        return queue.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return topItem;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }

    private HashMap<Node, Node> visited = new HashMap<>();

    //LeetCode133 克隆图
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        var cloned = new Node(node.val, new ArrayList<>());
        visited.put(node, cloned);

        for (var neighbor : node.neighbors) {
            cloned.neighbors.add(cloneGraph(neighbor));
        }

        return cloned;

    }

    /**
    * LeetCode150：逆波兰表达式求值
    *   输入: ["4", "13", "5", "/", "+"]
        输出: 6
        解释: 该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
    * */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Integer n1, n2;
        for (String token : tokens) {
            switch (token) {
                case ("+"):
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 + n2);
                    break;
                case ("-"):
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 - n2);
                    break;
                case ("*"):
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 * n2);
                    break;
                case ("/"):
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 / n2);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }

        return stack.pop();
    }

    //通过数组来实现功能
    public int evalRPN2(String[] tokens) {
        Integer[] integers = new Integer[tokens.length / 2 + 1];//数组最大长度
        int index = 0;
        for (String token : tokens){
            switch (token){
                case ("+"):
                    integers[index - 2] += integers[--index];
                    break;
                case ("-"):
                    integers[index - 2] -= integers[--index];
                    break;
                case ("*"):
                    integers[index - 2] *= integers[--index];
                    break;
                case ("/"):
                    integers[index - 2] /= integers[--index];
                    break;
                default:
                    var num = Integer.parseInt(token);
                    integers[index++] = num;
                    break;
            }
        }

        return integers[0];
    }

}
