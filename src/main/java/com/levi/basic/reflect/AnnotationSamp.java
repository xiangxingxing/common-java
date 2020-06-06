package com.levi.basic.reflect;

import java.lang.reflect.Method;

@MyAnnotation(username = "levi", password = 123)
public class AnnotationSamp {
    public static void main(String[] args) {
        //samp2();


    }

    @MyAnnotation(username = "doSome", password = 369)
    public void doSome() {

    }

    public static void samp1() {
        try {
            Class c = Class.forName("com.levi.basic.reflect.AnnotationSamp");
            if (c.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation = (MyAnnotation) c.getAnnotation(MyAnnotation.class);
                String value = annotation.value();
                System.out.println(value);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void samp2() {
        try {
            Class c = Class.forName("com.levi.basic.reflect.AnnotationSamp");
            Method method = c.getDeclaredMethod("doSome");
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation an = method.getAnnotation(MyAnnotation.class);

                System.out.println(an.username());
                System.out.println(an.password());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

