package com.levi.huawei;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<String, Outer> map = new HashMap<>();
        HashMap<String, Member> memberMap = new HashMap<>();
        HashMap<String, Manager> managerMap = new HashMap<>();
        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String str = in.nextLine();
            if(str.equals("performance")){
                continue;
            }
            String[] strings = str.split(",");
            if(strings.length != 2){
                break;
            }
            Outer outer = new Outer(strings[0], Integer.parseInt(strings[1]));
            map.put(outer.name, outer);
        }


        while (in.hasNext()){
            String str = in.nextLine();
            if(str.equals("organization")){
                continue;
            }
            String[] strings = str.split(",");
            if(strings.length != 3){
                break;
            }
            String manager = strings[0];
            String memName = strings[1];
            String outName = strings[2];

            if(memberMap.containsKey(memName)){
                Member member = memberMap.get(memName);
                member.outers.add(map.get(outName));
            }else {
                ArrayList<Outer> outersList = new ArrayList<>();
                outersList.add(map.get(outName));
                Member member = new Member(strings[1], outersList);
                memberMap.put(memName, member);
            }

            if(!managerMap.containsKey(manager)){
                List<Member> members = new ArrayList<>();
                members.add(memberMap.get(memName));
                Manager boss = new Manager(manager, members);
                managerMap.put(manager, boss);
            }else {
                Manager manager1 = managerMap.get(manager);
                if(!manager1.members.contains(memberMap.get(memName))){
                    manager1.members.add(memberMap.get(memName));
                }
            }
        }

        //排序打印
        //System.out.println(managerMap.get("Aaron").taskSum());
        //System.out.println(memberMap.get("Jone").taskSum());
        //System.out.println(memberMap.get("Abel").taskSum());
        //System.out.println(map.get("Evan").taskCount);

        PriorityQueue<Manager> managers = new PriorityQueue<Manager>(managerMap.size(), (o1, o2) -> {
            if(o1.taskSum() != o2.taskSum()){
                return o2.taskSum() - o1.taskSum();
            }else {
                return o1.name.compareTo(o2.name);
            }
        });

        Collection<Manager> managerCollection = managerMap.values();
        for(Manager m : managerCollection){
            managers.offer(m);
        }

        while(!managers.isEmpty()){
            Manager m = managers.poll();
            printInfo(m);
        }

    }

    private static void printInfo(Manager manager){
        System.out.println(manager);
        PriorityQueue<Member> set = new PriorityQueue<>();
        for (Member member : manager.members) {
            set.offer(member);
        }

        while(!set.isEmpty()){
            Member m = set.poll();
            System.out.println(m);
            PriorityQueue<Outer> queue = new PriorityQueue<>();
            for (Outer outer : m.outers) {
                queue.offer(outer);
            }

            while (!queue.isEmpty()){
                System.out.println(queue.poll());
            }
        }
    }

    static class Manager{
        public String name;
        private List<Member> members;

        public Manager(String name, List<Member> members) {
            this.name = name;
            this.members = members;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Member> getMembers() {
            return members;
        }

        public void setMembers(List<Member> members) {
            this.members = members;
        }

        public int taskSum() {
            int sum = 0;
            for (Member member : members) {
                for(Outer o : member.outers){
                    sum += o.taskCount;
                }
            }

            return sum;
        }

        @Override
        public String toString() {
            return name + "<" + taskSum() + ">";
        }
    }

    static class Member implements Comparable<Member>{
        private String name;
        private List<Outer> outers;
        public Member(String name, List<Outer> outers){
            this.name = name;
            this.outers = outers;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Outer> getOuters() {
            return outers;
        }

        public void setOuters(List<Outer> outers) {
            this.outers = outers;
        }

        public int taskSum() {
            int sum = 0;
            for (Outer o : outers) {
                sum += o.taskCount;
            }

            return sum;
        }

        @Override
        public String toString() {
            return "-" + name + '<' + taskSum() + '>';
        }

        @Override
        public int compareTo(Member o) {
            if(taskSum() != o.taskSum()){
                return o.taskSum() - this.taskSum();
            }else {
                return this.name.compareTo(o.name);
            }
        }
    }

    static class Outer implements Comparable<Outer> {
        public String name;
        private int taskCount;

        public Outer(String name, int taskCount) {
            this.name = name;
            this.taskCount = taskCount;
        }

        @Override
        public String toString() {
            return "--" + name + '<' + taskCount + '>';
        }

        @Override
        public int compareTo(Outer o) {
            if(this.taskCount != o.taskCount){
                return o.taskCount - this.taskCount;
            }else {
                return this.name.compareTo(o.name);
            }
        }
    }
}
