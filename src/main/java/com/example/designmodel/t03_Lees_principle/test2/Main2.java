package com.example.designmodel.t03_Lees_principle.test2;


/**
 * @author LuoXianchao
 * @since 2023/4/21 21:28
 */
public class Main2 {
    public static void main(String[] args) {
        Class2 class2 = new Class2();
        System.out.println(class2.addDouble(1, 2));
        System.out.println(class2.addDouble2(1, 2));
    }
}

abstract class Base{
    public abstract int add(int a, int b);
}

class Class1 extends Base {
    public int add(int a, int b){
        return a + b;
    }
}

class Class2 extends Base {

    // 由于里氏替换原则，这里Class1和Class2使用组合
    // class1组合到了class2里面
    Class1 class1 = new Class1();

    @Override
    public int add(int a, int b){
        return (a + b) * 10;
    }
    public int addDouble(int a, int b){
        return add(a, b) + add(a, b);
    }
    public int addDouble2(int a, int b){
        // 答案是使用super
        return class1.add(a, b) + class1.add(a, b);
    }
}

