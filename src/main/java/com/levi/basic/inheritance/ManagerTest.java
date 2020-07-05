package com.levi.basic.inheritance;

public class ManagerTest {
    public static void main(String[] args) {
        var boss = new Manager("levi", 80000, 1995, 1, 1);
        boss.setBonus(5000);

        var staff = new Employee[3];
        staff[0] = boss;
        staff[1] = new Employee("kd", 10000, 2000,2,2);
        staff[2] = new Employee("kb", 20000, 2002,3,3);


        for (Employee e : staff){
            System.out.println("name " + e.getName() + ",salary=" + e.getSalary());
        }
    }
}
