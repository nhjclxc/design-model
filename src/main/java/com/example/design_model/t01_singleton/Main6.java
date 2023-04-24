package com.example.design_model.t01_singleton;


import java.util.concurrent.ThreadFactory;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:32
 */
public class Main6 {

    public static void main(String[] args) {
        ThreadFactory threadFactory = ThreadPoolFactory.getThreadFactory();
        threadFactory.newThread(() -> {
            System.out.println(Singleton6.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton6.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton6.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton6.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton6.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();

    }
}

/**
 * 6、静态内部类
 * 静态内部类特点：
 *  1.静态内部类的宿主类(外部的那个类)被加载的时候，静态内部类不会被加载
 *  2.只有当第一次使用到静态内部类的时候，静态内部类才会去加载，然而类的加载是线程安全的，
 *  因此，综合以上两点，静态内部类实现单例是线程安全的
 *
 */
class Singleton6 {

    private String name;

    // 局部静态内部类
    private static class Singleton6InnerClass{
        private static final Singleton6 INSTANCE = new Singleton6(Thread.currentThread().getName());
    }

    // 防止被new对象
    private Singleton6(String name) {
        this.name = name;
    }

    private Singleton6() {
    }

    /**
     * 同步方代码块
     * 双重检索
     */
    public static Singleton6 getInstance() {
        return Singleton6InnerClass.INSTANCE;
    }


    @Override
    public String toString() {
        return "Singleton6{" +
                "name='" + name + '\'' +
                '}';
    }
}
