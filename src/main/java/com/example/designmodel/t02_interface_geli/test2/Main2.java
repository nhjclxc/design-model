package com.example.designmodel.t02_interface_geli.test2;


/**
 * @author LuoXianchao
 * @since 2023/4/19 22:36
 */
public class Main2 {
    public static void main(String[] args) {
        A a = new A();
        a.op1(new B());
        a.op2(new B());

        C c = new C();
        c.op1(new D());
        c.op3(new D());


    }
}

// 按照接口隔离原则：将MyInterface拆分为几个独立的接口，类A和类C分别与他们需要的接口建立依赖

// A用MyInterface的fun1、fun2
// C用MyInterface的fun1、fun3

// A通过接口MyInterface2依赖B
class A{
    public void op1( MyInterface2 mi){
        mi.fun1();
    }
    public void op2( MyInterface2 mi){
        mi.fun2();
    }
}
// C通过接口MyInterface3依赖B
class C{
    public void op1( MyInterface3 mi){
        mi.fun1();
    }
    public void op3( MyInterface3 mi){
        mi.fun3();
    }
}
// 最小接口原则
// 以下三个接口设计为接口隔离原则，
// B类、D类都要实现的公共接口放在MyInterface1里面，其他各自拥有的接口放在各自直接实现的接口里卖弄
// A用MyInterface2可以得到fun1、fun2
// C用MyInterface3可以得到fun1、fun3
interface MyInterface1{
    void fun1();
}
interface MyInterface2 extends  MyInterface1{
    void fun2();
}
interface MyInterface3 extends  MyInterface1{
    void fun3();
    void fun1();
}

class B implements MyInterface2 {
    @Override
    public void fun1() {
        System.out.println(" B.fun1");
    }
    @Override
    public void fun2() {
        System.out.println(" B.fun2");
    }
}

class D implements MyInterface3 {
    @Override
    public void fun1() {
        System.out.println(" D.fun1");
    }
    @Override
    public void fun3() {
        System.out.println(" D.fun3");
    }
}