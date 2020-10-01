package com.levi.huawei;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int endTime = in.nextInt();//结束时刻
        int countOfTask = in.nextInt();
        PriorityQueue<WorkTask> queue = new PriorityQueue<>();
        for (int i = 0; i < countOfTask; i++) {
            int id = in.nextInt();
            int profit = in.nextInt();
            int startTime = in.nextInt();
            int duration = in.nextInt();
            if (startTime + duration > endTime) {
                continue;
            }
            WorkTask task = new WorkTask(id, profit, startTime, duration);
            queue.offer(task);//queue中均是时间符合的任务
        }

        List<List<WorkTask>> res = new ArrayList<>();
        List<WorkTask> tasks = new ArrayList<>(queue);
        helper(tasks, res);
        int max = 0;

        for (int i = 0; i < res.size(); i++) {
            max = Math.max(calculateProfit(res.get(i)), max);
        }
        System.out.println(max);
        int index = 0;
        for(int i = 0; i < res.size(); i++){
            if(calculateProfit(res.get(i)) == max){
                index = i;
                break;
            }
        }
        System.out.println(res.get(index).size());
        for(WorkTask t : res.get(index)){
            System.out.println(t.id );
        }

    }

    private static int calculateProfit(List<WorkTask> tasks) {
        int profit = 0;
        for (WorkTask t : tasks) {
            profit += t.profit;
        }

        return profit;
    }

    private static void helper(List<WorkTask> tasks, List<List<WorkTask>> res) {
        for (int i = 0; i < tasks.size(); i++) {
            List<WorkTask> temp = new ArrayList<>();
            WorkTask task = tasks.get(i);
            temp.add(task);
            int preStart = task.startTime;
            int preEnd = task.duration + preStart;
            for (int j = i + 1; j < tasks.size(); j++) {
                WorkTask t = tasks.get(j);
                if (t.startTime >= preEnd) {
                    temp.add(t);
                    preStart = t.startTime;
                    preEnd = preStart + t.duration;
                }
            }
            res.add(temp);
        }
    }
}

class WorkTask implements Comparable<WorkTask> {
    public int id;//
    public int profit;//万元
    public int startTime;//任务的起始时刻
    public int duration;//耗时

    public WorkTask(int id, int profit, int startTime, int duration) {
        this.id = id;
        this.profit = profit;
        this.startTime = startTime;
        this.duration = duration;
    }

    @Override
    public int compareTo(WorkTask o) {
        return this.startTime - o.startTime;
    }
}