package com.example.design.t02_interface_geli.test1;

/**
 * @author LuoXianchao
 * @since 2023/4/19 22:36
 */
public class Main1 {
    public static void main(String[] args) {
        A a = new A();
        a.op1(new B());


    }
}

// A用MyInterface的fun1、fun2
// C用MyInterface的fun3

// A通过接口MyInterface依赖B
class A{
    public void op1(MyInterface mi){
        mi.fun1();
    }
    public void op2(MyInterface mi){
        mi.fun2();
        // mi.fun3();
    }
}

// 仅仅只设计一个接口，不符合接口隔离原则，具体看test.Main2.java
interface MyInterface{
    void fun1();
    void fun2();
    void fun3();
}

class B implements MyInterface{
    @Override
    public void fun1() {
        System.out.println(" B.fun1");
    }
    @Override
    public void fun2() {
        System.out.println(" B.fun2");
    }
    @Override
    public void fun3() {
        System.out.println(" B.fun3");
    }
}

class D implements MyInterface{
    @Override
    public void fun1() {
        System.out.println(" D.fun1");
    }
    @Override
    public void fun2() {
        System.out.println(" D.fun2");
    }
    @Override
    public void fun3() {
        System.out.println(" D.fun3");
    }
}