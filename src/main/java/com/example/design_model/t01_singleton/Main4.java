package com.example.design_model.t01_singleton;


import java.util.Objects;
import java.util.concurrent.ThreadFactory;

/**
 *
 * @author LuoXianchao
 * @since 2023/4/23 21:32
 */
public class Main4 {

    public static void main(String[] args) {
        ThreadFactory threadFactory = ThreadPoolFactory.getThreadFactory();
        threadFactory.newThread(() -> {
            System.out.println(Singleton4.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton4.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton4.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton4.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton4.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();

    }
}

/**
 * 4、懒汉式线程安全（同步方法）
 * 懒加载
 */
class Singleton4 {

    private String name;

    private static Singleton4 instance;

    // 防止被new对象
    private Singleton4(String name){ this.name = name;}
    private Singleton4(){}

    /** 同步方法
     * 使用synchronized关键字实现方法的方法资源的互斥
     * 解决了线程安全问题，但是效率太低，如果getInstance方法的内部处理逻辑太多，将导致方法慢
     * 解决方法，我们只锁new对象的那一块代码，不锁方法
     */
    public static synchronized Singleton4 getInstance(){
        if (Objects.isNull(instance)){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton4(Thread.currentThread().getName());
        }
        return instance;
    }


    @Override
    public String toString() {
        return "Singleton4{" +
                "name='" + name + '\'' +
                '}';
    }
}
