package com.example.design_model.t02_factory.test1;


/**
 * @author LuoXianchao
 * @since 2023/4/25 21:04
 */
public class Main1 {
    public static void main(String[] args) {

        Pizza pizza = OrderPizzaFactory.producePizza(GreekPizza.class);
        System.out.println(pizza);
        Pizza pizza1 = OrderPizzaFactory.producePizza(CheesePizza.class);
        System.out.println(pizza1);
    }
}

/**
 * 普通工厂模式
 *
 */
class OrderPizzaFactory{

    /**
     * 提供一个实例化对象的方法，
     * 将创建对象的过程封装起来，具体对象的创建过程不在有用户去实现，而是通过这个工厂来完成
     * 别人想获取一个工厂对于的实例化对象时，不在需要繁琐的new，初始化数据了，全部有工厂实现
     * 工厂直接返回一个你要想的对象给你
     */
    // 类型通配符向上限定<? extends Pizza> 它表示的类型是Pizza或者其子类型
    public static Pizza producePizza(Class<? extends Pizza> clazz){
        Pizza pizza = null;
        try {
            pizza = clazz.newInstance();
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return pizza;
    }
}

 class Pizza{

    public String name;

    public Pizza( ){
    }
    public Pizza(String name){
        this.name = name;
    }

    public  void prepare(){}

    public void bake(){
        System.out.println(this.name + " - bake");
    }

    public void cut(){
        System.out.println(this.name + " - cut");
    }

    public void box(){
        System.out.println(this.name + " - box");
    }

}

class CheesePizza extends Pizza{
    public CheesePizza(String name) {
        super(name);
    }
    public CheesePizza( ) {
        this("CheesePizza");
    }
    @Override
    public void prepare() {
        System.out.println(this.name + " - prepare");
    }
}
class GreekPizza extends Pizza{
    public GreekPizza(String name) {
        super(name);
    }
    public GreekPizza( ) {
        this("GreekPizza");
    }
    @Override
    public void prepare() {
        System.out.println(this.name + " - prepare");
    }
}