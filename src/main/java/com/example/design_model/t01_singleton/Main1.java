package com.example.design_model.t01_singleton;


import java.util.concurrent.ThreadFactory;

/**
 *
 * @author LuoXianchao
 * @since 2023/4/23 21:32
 */
public class Main1 {

    public static void main(String[] args) {

        ThreadFactory threadFactory = ThreadPoolFactory.getThreadFactory();
        threadFactory.newThread(() -> {
            System.out.println(Singleton1.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton1.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton1.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton1.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton1.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();

    }
}

/**
 * 1、饿汉式（成员变量是静态常量）
 *
 * 该对象在类加载的时候被创建，避免了线程同步问题，但是如果在程序允许过程种该对象没有被使用到的话，可能造成内存浪费
 * 没有实现lazy loading懒加载
 */
class Singleton1 {

    private String name;

    //类加载时创建对象
    private final static Singleton1 instance = new Singleton1(Thread.currentThread().getName());
    // 防止被new对象
    private Singleton1(String name){ this.name = name;}
    private Singleton1(){}

    public static Singleton1 getInstance(){
        return instance;
    }


    @Override
    public String toString() {
        return "Singleton1{" +
                "name='" + name + '\'' +
                '}';
    }
}
