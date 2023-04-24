package com.example.design_model.t01_singleton;


import java.util.Objects;
import java.util.concurrent.ThreadFactory;

/**
 *
 * @author LuoXianchao
 * @since 2023/4/23 21:32
 */
public class Main3 {

    public static void main(String[] args) {
        ThreadFactory threadFactory = ThreadPoolFactory.getThreadFactory();
        threadFactory.newThread(() -> {
            System.out.println(Singleton3.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton3.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton3.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton3.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();
        threadFactory.newThread(() -> {
            System.out.println(Singleton3.getInstance() + " -- " + Thread.currentThread().getName());
        }).start();

    }
}

/**
 * 3、懒汉式线程不安全
 * 懒加载
 */
class Singleton3 {

    private String name;

    private static Singleton3 instance;

    // 防止被new对象
    private Singleton3(String name){ this.name = name;}
    private Singleton3(){}

    /**
     * 第一次被使用的时候创建对象，
     * 但是对于多线程调用的时候可能会产生多个对象
     * @return
     */
    public static Singleton3 getInstance(){
        if (Objects.isNull(instance)){
            try {
                // 睡眠10ms模拟在做一些操作
                // 当本线程睡眠之后，这个线程的CPU时间片将被占用，本线程由运行态转变为阻塞态
                // 其他线程抢到cpu时间片之后就会在次到上面的if里面去判断，发现instance变量还是null，
                //所以这些线程都会进入if来new对象，但是当前面的线程在此获得cpu时间片的时候，还是回去创建instance对象
                // 因为这些线程的PC指针已经进入了，if代码块。
                // 综上所述：本方法实现单例在对多线程时失效，单线程时可以使用。
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton3(Thread.currentThread().getName());
        }
        return instance;
    }


    @Override
    public String toString() {
        return "Singleton3{" +
                "name='" + name + '\'' +
                '}';
    }
}
