package com.example.design_model.t11_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author LuoXianchao
 * @since 2023/05/03 15:10
 */
public class Main3 {
    public static void main(String[] args) {
        // 代理做方法增强
        ProxyFactory2 factory2 = new ProxyFactory2(new Teacher());
        Teacher teacher = (Teacher)factory2.getProxyInstance();
        String msg = teacher.say("哈哈哈哈哈");
        System.out.println(msg);

    }
}
/**
 * Cglib代理（子类代理）
 *  1、静态代理和JDK代理都必须要求被代理对象要实现一个抽象的接口，但是又时候目标对象只是一个单独的对象，
 *      并没有实现任何接口，这个时候可以使用目标对象的子类来实现代理，这个就是cglib代理
 *  2、他是在内存中构建一个子类对象，从而实现对目标对象的功能扩展
 *  3、cglib是一个代码生成包，它可以在运行时扩展Java类与实现Java接口，被广泛的被许多AOP框架使用，实现方法拦截
 *  4、在AOP编程中如何选择代理模式：
 *      1.目标对象需要实现接口，使用JDK代理
 *      2.目标对象不需要实现接口，用cglib代理
 *  5、cglib包底层是通过处理字节码文件的
 */

/**
 * 代理工厂
 */
class ProxyFactory2 implements MethodInterceptor {

    /**
     * 维护一个目标对象
     */
    private final Object target;

    public ProxyFactory2(Object target) {
        this.target = target;
    }

    /**
     * 维护代理对象
     */
    public Object getProxyInstance() {
        // 1.创建一个工具类
        Enhancer enhancer = new Enhancer();
        // 2.设置父类
        enhancer.setSuperclass(target.getClass());
        // 3.设置回调函数
        enhancer.setCallback(this);
        // 创建target的子类，即我们使用的代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("1");
        Object invoke = method.invoke(target, objects);
        System.out.println("2");
        return invoke;
    }
}

/**
 * 被代理对象
 */
class Teacher{
    public String say(String msg){
        System.out.println("Teacher say " + msg);
        return "Hello ";
    }
}