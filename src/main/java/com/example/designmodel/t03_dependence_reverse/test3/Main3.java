package com.example.designmodel.t03_dependence_reverse.test3;

/**
 * @author LuoXianchao
 * @since 2023/4/20 21:12
 */
public class Main3 {
    /**
     * 依赖传递的三种方式：
     * 1、接口传递
     * 2、构造方法传递
     * 3、setter方法传递
     */
    public static void main(String[] args) {
        WeChat weChat = new WeChat();
        new Class1().sayHello(weChat);

        new Class2(weChat).sayHello();

        Class3 class3 = new Class3();
        class3.setMi(weChat);
        class3.sayHello();

    }

}

interface MyInterface{
    void hello(String name);
}
class WeChat implements MyInterface{
    @Override
    public void hello(String name) {
        System.out.println("Hello World! " + name);
    }
}

// 1.接口传递
class Class1{
    public void sayHello(MyInterface mi){
        mi.hello("Class1");
    }
}

// 2.构造方法传递
class Class2{
    private final MyInterface mi;
    public Class2(MyInterface mi){
        this.mi = mi;
    }
    public void sayHello(){
        this.mi.hello("Class2");
    }
}

// 3.setter方法传递
class Class3{
    private MyInterface mi;
    public void setMi(MyInterface mi){
        this.mi = mi;
    }
    public void sayHello(){
        this.mi.hello("Class3");
    }
}

