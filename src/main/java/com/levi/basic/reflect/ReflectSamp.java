package com.levi.basic.reflect;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;

/*
* 1.1 反射：可以操作字节码文件，让程序更加灵活
* 1.2 在java.lang.reflect.*
* 1.3 反射机制相关的类
*       java.lang.Class
*       java.lang.reflect.Method
*       java.lang.reflect.Constructor
*       java.lang.reflect.Field
* 1.4 获取class的三种方式
*       Class c = Class.forName("")
*       对象.getClass();
*       int.class/String.class
*
* */

public class ReflectSamp {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ReflectSamp.class);

    public static void main(String[] args) {
        samp1();
        //samp2();
        //samp3();
    }

    //【类路径：在src下的都是类路径】src是类的根路径
    //获取文件的绝对路径
    public static void samp1() {
        String path = Thread.currentThread().getContextClassLoader()
                .getResource("common.properties").getPath();
        //LOGGER.info(path);
    }

    //资源绑定器
    public static void samp2(){
        /*
        * 1.不能加后缀properties
        * 2.只能绑定xxx.properties文件
        *
        * */
        ResourceBundle bundle = ResourceBundle.getBundle("common");
        String value = bundle.getString("class");
        System.out.println(value);
    }

    //反编译类
    public static void samp3(){
        StringBuilder sb = new StringBuilder();
        try {
            Class<?> c = Class.forName("com.levi.basic.reflect.Student");
            sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() + "{\n");

            //Field[] fields = c.getFields();//仅获取public类型的字段
            Field[] fields = c.getDeclaredFields();//获取所有字段
            for (Field f : fields){
                sb.append('\t');
                sb.append(Modifier.toString(f.getModifiers()));
                sb.append(" ");
                sb.append(f.getType().getSimpleName());
                sb.append(" ");
                sb.append(f.getName());
                sb.append(";\n");
            }

            sb.append("}");
            System.out.println(sb);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
