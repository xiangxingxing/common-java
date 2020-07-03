package com.levi.dataStructures.queue;

/*
 * LeetCode232:用栈实现队列
 * */

import java.util.LinkedList;

public class MyQueue {
    private LinkedList<Integer> stack1;
    private LinkedList<Integer> stack2;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stack1.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop(){
        if (!stack2.isEmpty()){
            return stack2.pop();
        }
        else  {
            if(stack1.isEmpty()){
                throw new RuntimeException("Queue is empty.");
            }
            else {
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }

                return stack2.pop();
            }
        }
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (!stack2.isEmpty()){
            return stack2.peek();
        }
        else  {
            if(stack1.isEmpty()){
                throw new RuntimeException("Queue is empty.");
            }
            else {
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }

                return stack2.peek();
            }
        }
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
