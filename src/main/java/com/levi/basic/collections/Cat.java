package com.levi.basic.collections;

public class Cat implements Comparable<Cat> {
    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //姓名优先，年龄其次
    @Override
    public int compareTo(Cat o) {
        if (!name.equals(o.name)){
            return this.name.compareTo(o.name);
        }
        //return this.age - o.age;
        return o.age - this.age;
    }


    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
