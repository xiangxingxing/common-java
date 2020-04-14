package com.levi.basic;

public class DataTypeMaker {

    public static void EqualTest() {
        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找

        System.out.println(a == b); // false
        System.out.println(aa == bb); // true
        System.out.println(a.equals(b)); // true
        System.out.println(42 == 42.0); // true

        System.out.println(aa.hashCode());
        System.out.println(bb.hashCode());
    }

    public static void IntegerOperation() {

        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;

        //System.out.println(i1==i2); // true: 数值在[-128,127]之间，便返回指向IntegerCache.cache中已经存在的对象的引用；
        //System.out.println(i3==i4); //false: 否则创建一个新的Integer对象

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;

        System.out.println(c == d);// true
        System.out.println(e == f); // false
        System.out.println(c == (a + b)); // true
        System.out.println(c.equals(a + b)); // false ❌ 应该是true
        System.out.println(g == (a + b)); // false ❌ 应该是true
        System.out.println(g.equals(a + b)); // false
        System.out.println(g.equals(a + h)); // true
    }

}
