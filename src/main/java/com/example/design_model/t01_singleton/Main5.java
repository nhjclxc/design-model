package com.example.design_model.t01_singleton;


import java.util.Objects;
import java.util.concurrent.ThreadFactory;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:32
 */
public class Main5 {

    public static void main(String[] args) {
        ThreadFactory threadFactory = ThreadPoolFactory.getThreadFactory();
        threadFactory.newThread(() -> {
            System.out.println(Singleton5.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton5.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton5.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton5.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton5.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();

    }
}

/**
 * 5、懒汉式线程安全（同步代码块）
 * 懒加载
 */
class Singleton5 {

    private String name;

    private static volatile Singleton5 instance;

    // 防止被new对象
    private Singleton5(String name) {
        this.name = name;
    }

    private Singleton5() {
    }

    /**
     * 同步方代码块
     * 双重检索
     */
    public static Singleton5 getInstance() {
        // 其他操作 。。。
        // synchronized只同步new对象的这个地方，提高getInstance方法效率
        if (Objects.isNull(instance)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 双重检索实现同步代码块
            synchronized (Singleton5.class) {
                if (Objects.isNull(instance)) {
                    instance = new Singleton5(Thread.currentThread().getName());
                }
            }
        }
        // 其他操作 。。。
        return instance;
    }


    @Override
    public String toString() {
        return "Singleton5{" +
                "name='" + name + '\'' +
                '}';
    }
}
