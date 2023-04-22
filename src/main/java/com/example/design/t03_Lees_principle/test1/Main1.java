package com.example.design.t03_Lees_principle.test1;

/**
 * @author LuoXianchao
 * @since 2023/4/21 21:16
 */
public class Main1 {
    public static void main(String[] args) {
        Class2 class2 = new Class2();
        System.out.println(class2.addDouble(1, 2));
        System.out.println(class2.addDouble2(1, 2));
    }
}

class Class1{
    public int add(int a, int b){
        return a + b;
    }
}

class Class2 extends Class1{
    @Override
    public int add(int a, int b){
        return (a + b) * 10;
    }
    public int addDouble(int a, int b){
        // 你觉得以下的add方法是父类的还是子类重写的？??
        // 以下调用的是从写过的，那么我们想要调用父类的应该怎么操作呢？？？答案是使用super
        return add(a, b) + add(a, b);
    }
    public int addDouble2(int a, int b){
        // 答案是使用super
        return super.add(a, b) + super.add(a, b);
    }
}

