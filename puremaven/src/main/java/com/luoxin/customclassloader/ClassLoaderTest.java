package com.luoxin.customclassloader;

import java.lang.reflect.Method;

public class ClassLoaderTest {

    public static void main(String args[]) throws Exception {
        //初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载
        CustomClassLoader classLoader = new CustomClassLoader( "D:\\T03__Work\\practice\\puremaven\\target\\classes\\");
        Class clazz = classLoader.loadClass("com.luoxin.customclassloader.Math");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("sout", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass().getName());
    }
}