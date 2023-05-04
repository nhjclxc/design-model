package com.example.design_model.t02_factory.test4;

import lombok.Data;

/**
 * @author LuoXianchao
 * @since 2023/4/27 20:30
 */
public class Main4 {
    public static void main(String[] args) {
        System.out.println(new OrderPizza().getPizza(new BeiJingPizzaFactoryImpl(), Pizza1.class));
        System.out.println(new OrderPizza().getPizza(new BeiJingPizzaFactoryImpl(), Pizza2.class));
        System.out.println(new OrderPizza().getPizza(new ShangHaiPizzaFactoryImpl(), Pizza1.class));
        System.out.println(new OrderPizza().getPizza(new ShangHaiPizzaFactoryImpl(), Pizza2.class));
    }
}

/**抽象工厂模式
 * 定义一个interface用于创建相关或有依赖关系的对象簇，而无需指明具体的类
 * 抽象工厂模式可以将普通工厂模式和工厂方法模式进行整合
 * 从设计层面看，抽象工厂模式就是对普通工厂模式的进一步抽象
 * 将工厂抽象成两层，AbsFactory抽象工厂层和AbsFactoryImpl抽象工厂实现层，使用的时候可以根据创建对象的类型
 * 使用对于的工厂子类，这样将单个简单的工厂变成了工厂簇，更利于代码的开发
 */

interface PizzaFactory {
    Pizza producePizza(Class<? extends Pizza> clazz);
}

class BeiJingPizzaFactoryImpl implements PizzaFactory {
    @Override
    public Pizza producePizza(Class<? extends Pizza> clazz){
        Pizza pizza = null;
        try {
            pizza = clazz.newInstance();
            pizza.setName("BeiJingPizzaFactoryImpl " + clazz.getName());
        }catch (Exception ignored){
        }
        return pizza;
    }
}
class ShangHaiPizzaFactoryImpl implements PizzaFactory {
    @Override
    public Pizza producePizza(Class<? extends Pizza> clazz){
        Pizza pizza = null;
        try {
            pizza = clazz.newInstance();
            pizza.setName("ShangHaiPizzaFactoryImpl " + clazz.getSimpleName());
        }catch (Exception ignored){
        }
        return pizza;
    }
}

class OrderPizza {
    public Pizza getPizza(PizzaFactory pizzaFactory, Class<? extends Pizza> clazz){
        return pizzaFactory.producePizza(clazz);
    }
}

@Data
abstract class Pizza {
    public String name;
    public Pizza(){
        super();
    }
    public Pizza(String name){
        this.name = name;
    }
}
@Data
class Pizza1 extends Pizza {
    public Pizza1(){
        super();
    }
    public Pizza1(String name){
        super(name);
    }
    @Override
    public String toString() {
        return "Pizza1{" +
                "name='" + name + '\'' +
                '}';
    }
}
@Data
class Pizza2 extends Pizza{
    public Pizza2(){
        super();
    }
    public Pizza2(String name){
        super(name);
    }

    @Override
    public String toString() {
        return "Pizza2{" +
                "name='" + name + '\'' +
                '}';
    }
}


