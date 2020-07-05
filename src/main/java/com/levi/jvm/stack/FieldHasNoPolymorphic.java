package com.levi.jvm.stack;

/**
 *  字段不参与多态！
 *  哪个类的方法访问某个名字的字段时，该名字指的就是这个类能看到的那个字段
 *
 *  结果:
 *          I am Son, i have $ 0
 *          I am Son, i have $ 4
 *          This guy has $ 2
 * */
public class FieldHasNoPolymorphic {
    public static void main(String[] args) {
        Father guy = new Son();
        System.out.println("This guy has $ " + guy.money);
    }

    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am Father, i have $ " + money);
        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            //隐式调用了Father的构造函数，其中showMeTheMoney方法实际版本为Son的版本
            //此时money还没有被赋值即 == 0
            money = 4;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am Son, i have $ " + money);
        }
    }
}
