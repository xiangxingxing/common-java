package com.levi.lambda;

/*
* 方法引用
* */
public class LambdaSamp {
    public static void main(String[] args) {
        //samp1();
        //samp2();
        samp3();
    }

    public static void samp1() {
        /*
         * printString方法参数是一个函数式接口，所以可以传递lambda
         * */
        printString(System.out::println);//使用方法引用优化lambda表达式
        printString(str -> System.out.println(str));
    }

    //通过对象名引用方法
    public static void samp2() {
        /*前提:对象名已存在，方法也存在
         *
         * */
        printString(str -> {
            MethodRefObject obj = new MethodRefObject();
            obj.printUpperCaseString(str);
        });

        MethodRefObject obj = new MethodRefObject();
        printString(obj::printUpperCaseString);
    }

    /*
    * 通过类名引用静态成员方法
    * 前提:类存在，静态方法也存在
    * */
    public static void samp3() {
        //printString(str -> MethodRefObject.printChars(str));
        //printString(MethodRefObject::printChars);

        int num = calculateAbs(-10, number -> Math.abs(number));
        System.out.println(num);

        int num2 = calculateAbs(-10, Math::abs);
        System.out.println( "num2 = "+num2);
    }

    private static int calculateAbs(int num, Calculable cal){
        return cal.calAbs(num);
    }

    private static void printString(Printable pt) {
        pt.print("hello");
    }
}
