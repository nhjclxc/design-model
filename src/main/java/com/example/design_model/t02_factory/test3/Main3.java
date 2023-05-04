package com.example.design_model.t02_factory.test3;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author LuoXianchao
 * @since 2023/4/26 20:46
 */


/**工厂方法模式：
 *      将披萨项目的实例化功能抽象成抽象方法，在不同的口味点餐类种具体实现
 * 到以来一个创建对象的抽象方法，有子类决定要实例化的类，【【工厂方法模式将对象的实例化推迟到子类】】
 *
 *      将普通工厂创建对象的方法先抽象，让后再有各个不同的实际创建对象的工厂去实现；具体的工厂类实现父类的实例化对象方法
 */
/**
 * 披萨项目新的需求:客户在点披萨时，可以点不同口味的披萨，比如北京的奶酪pizza、
 * 北京的胡椒pizza或者是伦敦的奶酪pizza、伦敦的胡椒pizza.
 */

public class Main3 {
    public static void main(String[] args) {
        System.out.println(new OrderPizza(new LondonPizzaFactory()).getPizza(CheesePizza.class, "辣的"));
        System.out.println();
        System.out.println(new OrderPizza(new LondonPizzaFactory()).getPizza(GreekPizza.class, "辣的"));
        System.out.println();

        System.out.println(new OrderPizza(new BeiJingPizzaFactory()).getPizza(CheesePizza.class, "甜甜的"));
        System.out.println();
        System.out.println(new OrderPizza(new BeiJingPizzaFactory()).getPizza(GreekPizza.class, "甜甜的"));
        System.out.println();
    }
}

/**
 * 关于一下的类图看 *.uml.01_UML符号基础.drawio里面的第三页
 */
class OrderPizza{
    private PizzaFactory pizzaFactory;
    public OrderPizza(){}
    public OrderPizza(PizzaFactory pizzaFactory){
        this.pizzaFactory = pizzaFactory;
    }
    public Pizza getPizza(Class<? extends Pizza> clazz, String taste){
        return pizzaFactory.createPizza(clazz, taste);
    }
}

/**
 * 工厂方法模式(在方法上做抽象)
 */
abstract class PizzaFactory{
    /**
     * 将普通工厂创建对象的方法先抽象，让后再有各个不同的实际创建对象的工厂去实现
     */
    // 抽取创建实列的方法到工厂抽象类
    public abstract <T>  Pizza createPizza(Class<? extends Pizza> clazz, String taste);
}

class BeiJingPizzaFactory extends PizzaFactory{

    /**
     * 具体的工厂类实现父类的实例化披萨方法
     */
    @Override
    public <T>  Pizza createPizza(@NotNull Class<? extends Pizza> clazz, String taste){
        Pizza pizza = null;
        try {
            // 北京工厂的创建披萨方式
            System.out.println("工厂方法模式 BeiJingPizzaFactory 创建披萨");
            pizza = ( Pizza) clazz.newInstance();
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            pizza.setTaste(taste);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return pizza;
    }
}
class LondonPizzaFactory extends PizzaFactory{

    @Override
    public <T>  Pizza createPizza(@NotNull Class<? extends Pizza> clazz, String taste){
        Pizza pizza = null;
        try {
            // 伦敦工厂的创建披萨方式
            System.out.println("工厂方法模式 LondonPizzaFactory 创建披萨");
            pizza = clazz.newInstance();
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            pizza.setTaste(taste);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return pizza;
    }
}




@Data
class Pizza{

    public String name;
    public String taste;

    public Pizza(){ }
    public Pizza(String name, String taste){
        this.name = name;
        this.taste = taste;
    }

    public void prepare(){}

    public void bake(){
        System.out.println(this.name + " - bake");
    }

    public void cut(){
        System.out.println(this.name + " - cut");
    }

    public void box(){
        System.out.println(this.name + " - box");
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", taste='" + taste + '\'' +
                '}';
    }
}


@Data
class CheesePizza extends  Pizza {
    public CheesePizza(String name, String taste) {
        super(name,taste);
    }
    public CheesePizza() {
        this("CheesePizza", null);
    }
    public CheesePizza(String taste) {
        this("CheesePizza", taste);
    }
    @Override
    public void prepare() {
        System.out.println(this.name + " - prepare");
    }
    @Override
    public String toString() {
        return "CheesePizza{" +
                "name='" + name + '\'' +
                ", taste='" + taste + '\'' +
                '}';
    }
}
@Data
class GreekPizza extends  Pizza {
    public GreekPizza(String name,String taste) {
        super(name, taste);
    }
    public GreekPizza() {
        this("GreekPizza", null);}
    public GreekPizza(String taste) {
        this("GreekPizza", taste);
    }
    @Override
    public void prepare() {
        System.out.println(this.name + " - prepare");
    }
}
@Data
class OtherPizza extends  Pizza {
    public OtherPizza(String name, String taste) {
        super(name, taste);
    }
    public OtherPizza( ) {
        this("GreekPizza", null);}
    public OtherPizza(String taste) {
        this("OtherPizza", taste);
    }
    @Override
    public void prepare() {
        System.out.println(this.name + " - prepare");
    }
}
