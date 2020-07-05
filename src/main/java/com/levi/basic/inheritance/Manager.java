package com.levi.basic.inheritance;

public class Manager extends Employee {

    private double bonus;
    /**
     * @param name
     * @param salary
     * @param year
     * @param month
     * @param day
     */
    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        bonus = 0;
    }

    /*
    *   动态分派
    *   1.invokeVirtual指令执行的第一步就是在运行期确定接收者的实际类型
    *   2.根据实际类型确定方法执行版本
    * */
    public double getSalary(){
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }


    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
