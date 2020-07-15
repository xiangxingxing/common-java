package com.levi.jvm;

public class ClassLoaderSamp {
    public static void main(String[] args) {
        samp1();
    }

    public static void samp1(){
        //获取应用程序类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//jdk.internal.loader.ClassLoaders$AppClassLoader@3d4eac69

        //通过线程获取类加载器 //jdk.internal.loader.ClassLoaders$AppClassLoader@3d4eac69
        System.out.println(Thread.currentThread().getContextClassLoader());

        //获取其上层：扩展类加载器 加载ext/*.jar资源
        ClassLoader exClassLoader = systemClassLoader.getParent();
        System.out.println(exClassLoader);//jdk.internal.loader.ClassLoaders$PlatformClassLoader@77459877

        //获取其上层：获取不到引导类加载器
        ClassLoader bootstrapClassLoader = exClassLoader.getParent();
        System.out.println(bootstrapClassLoader);//null

        //对于用户自定义类来说，默认使用应用程序类加载器来进行加载
        ClassLoader classLoader = ClassLoaderSamp.class.getClassLoader();
        System.out.println(classLoader);//jdk.internal.loader.ClassLoaders$AppClassLoader@3d4eac69

        //对于java核心类库，均使用引导类加载器进行加载
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);//null

    }

}
