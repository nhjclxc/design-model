package com.example.design.t02_interface_geli.test3;



/**
 * @author LuoXianchao
 * @since 2023/4/19 22:36
 */
public class Main2 {
    public static void main(String[] args) {
        A a = new A();
        a.op1(new B());//A通过MyInterface1依赖B
        a.op2(new B());//A通过MyInterface2依赖B
        // 如何理解上面的依赖？？？
        // 是指，在定义方法的时候写的是接口名，而不是具体的类名
        // 而在实际使用a的op1()方法时，可以工具传入的参数灵活确定他的实现类
        // 比如向下面的A通过MyInterface1依赖了D的实现
        a.op1(new D());

        // 这个就叫做接口隔离


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
    public void op1( MyInterface1 mi){
        mi.fun1();
    }
    public void op2( MyInterface2 mi){
        mi.fun2();
    }
}
// C通过接口MyInterface3依赖B
class C{
    public void op1( MyInterface1 mi){
        mi.fun1();
    }
    public void op3( MyInterface3 mi){
        mi.fun3();
    }
}

/**
 * // 最小接口原则
 */
// 接口不继承，而是由实现类用多实现接口

// 以下三个接口设计为接口隔离原则，
// B类、D类都要实现的公共接口放在MyInterface1里面，其他各自拥有的接口放在各自直接实现的接口里卖弄
// A用MyInterface2可以得到fun1、fun2
// C用MyInterface3可以得到fun1、fun3
interface MyInterface1{
    void fun1();
}
interface MyInterface2{
    void fun2();
}
interface MyInterface3{
    void fun3();
}

class B implements MyInterface2, MyInterface1 {
    @Override
    public void fun1() {
        System.out.println(" B.fun1");
    }
    @Override
    public void fun2() {
        System.out.println(" B.fun2");
    }
}

class D implements MyInterface3, MyInterface1  {
    @Override
    public void fun1() {
        System.out.println(" D.fun1");
    }
    @Override
    public void fun3() {
        System.out.println(" D.fun3");
    }
}