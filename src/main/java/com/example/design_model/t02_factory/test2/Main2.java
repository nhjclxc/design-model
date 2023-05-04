package com.example.design_model.t02_factory.test2;

import java.util.Objects;

/**
 * @author LuoXianchao
 * @since 2023/4/25 21:04
 */
public class Main2 {
    public static void main(String[] args) {

        System.out.println(OrderPizza1.getPizza());
        System.out.println(OrderPizza2.getPizza());
        System.out.println((new OrderPizza3(new OrderPizzaFactory())).getPizza());
    }
}
class OrderPizza1{
    public static Pizza getPizza(){
         return OrderPizzaFactory.createPizza(GreekPizza.class);
    }
}
class OrderPizza2{
    public static Pizza getPizza(){
         return new OrderPizzaFactory().producePizza(CheesePizza.class);
    }
}
class OrderPizza3{
    private Pizza pizza;
    private OrderPizzaFactory orderPizzaFactory;

    public OrderPizza3() {
        setOrderPizzaFactory(new OrderPizzaFactory());
    }
    public OrderPizza3(OrderPizzaFactory orderPizzaFactory) {
        setOrderPizzaFactory(orderPizzaFactory);
    }
    public void setOrderPizzaFactory(OrderPizzaFactory orderPizzaFactory) {
        this.orderPizzaFactory = orderPizzaFactory;
    }

    public Pizza getPizza() {
        pizza = orderPizzaFactory.producePizza(OtherPizza.class);
        // 对pizza在此进行处理

        // 返回披萨
        return this.pizza;
    }
}
/**
 * 普通工厂模式
 */
class OrderPizzaFactory{
    
    public<T> Pizza producePizza(Class<T> clazz){
        Pizza pizza = null;
        try {
            pizza = ( Pizza) clazz.newInstance();
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            System.out.println("普通工厂模式 创建披萨");
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return pizza;
    }
    public static <T> Pizza createPizza(Class<T> clazz){
        Pizza pizza = null;
        try {
            pizza = ( Pizza) clazz.newInstance();
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            System.out.println("普通工厂模式 创建披萨");
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

class CheesePizza extends Pizza {
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
class GreekPizza extends Pizza {
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
class OtherPizza extends Pizza {
    public OtherPizza(String name) {
        super(name);
    }
    public OtherPizza( ) {
        this("OtherPizza");
    }
    @Override
    public void prepare() {
        System.out.println(this.name + " - prepare");
    }
}