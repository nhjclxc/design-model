package com.example.design_model.t05_adapter;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main4 {
    public static void main(String[] args) {
        // lamdam表达式测试
        new MyClass1().getInterfaceName(e -> {
            return "myName：" + " -- " + e;
        });

        new MyClass2().getInterfaceName((n, a) -> {
            return "myName：" + n + "，myAge：" + a;
        });

        new MyClass2().getInterfaceName(new MyInterface2() {
            @Override
            public String fun(String name, Integer age) {
                return name + "：" + age;
            }
        });
    }
}

interface MyInterface1{
    String fun(String name);
}
class MyClass1{
    public void getInterfaceName(MyInterface1 mi){
        System.out.println( mi.fun("sdfgh"));
    }
}
interface MyInterface2{
    String fun(String name, Integer age);
}
class MyClass2{
    public void getInterfaceName(MyInterface2 mi){
        System.out.println( mi.fun("i2", 666));
    }
}
