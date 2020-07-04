package com.levi.basic.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//只允许该注解标注类、方法
@Target({ElementType.TYPE,ElementType.METHOD})
/*
*   SOURCE 类型的注解在编译期就被丢掉了；
*   CLASS 类型的注解仅保存在class文件中，它们不会被加载进JVM；
*   RUNTIME 类型的注解会被加载进JVM，并且在运行期可以被程序读取
* */
//希望注解可以被反射
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation{
    String value() default "Buaa";

    String username();
    int password();
}
