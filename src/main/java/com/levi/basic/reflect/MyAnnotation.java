package com.levi.basic.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//只允许该注解标注类、方法
@Target({ElementType.TYPE,ElementType.METHOD})
//希望注解可以被反射
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation{
    String value() default "Buaa";

    String username();
    int password();
}
