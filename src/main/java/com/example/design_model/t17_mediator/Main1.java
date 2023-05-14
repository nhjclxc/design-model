package com.example.design_model.t17_mediator;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author LuoXianchao
 * @since 2023/5/11 19:56
 */
public class Main1 {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Colleague c1 = new Alarm("Alarm", mediator);
        Colleague c2 = new CoffeeMachine("CoffeeMachine", mediator);
        Colleague c3 = new TV("TV", mediator);
        c1.sendMessage(1);
    }
}

/**
 * 具体中介者
 */
class ConcreteMediator implements Mediator{
    //聚合同事，为了使得中介者可以在同事之间交流
    private final Map<String, Colleague> colleagueMap = new HashMap<>();

    public void add(String name, Colleague colleague) {
        colleagueMap.put(name, colleague);
    }

    @Override
    public String getMessage(Colleague colleague, int key) {
        System.out.println("中介者接收到数据：");

        if (colleague instanceof Alarm){
            if (key == 1) {
                colleague.operator(key);
                AtomicReference<Colleague> coffeeMachine = new AtomicReference<>();
                this.colleagueMap.forEach((k, v) -> {
                    if (v instanceof CoffeeMachine){
                        coffeeMachine.set(v);
                    }
                });
                if (!Objects.isNull(coffeeMachine.get())){
                    coffeeMachine.get().operator(key);
                }
            }
        }else if (colleague instanceof CoffeeMachine){
            colleague.operator(key);
        }else if (colleague instanceof TV){
            colleague.operator(key);
        }

        return null;
    }
}
/**
 * 具体的同事类
 */
class Alarm implements Colleague{
    private final String name;
    private final Mediator mediator;
    public Alarm(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
        this.mediator.add(name, this);
    }
    @Override
    public void sendMessage(int key) {
        String msg = this.name + " 发出了消息";
        System.out.println(msg);
        this.mediator.getMessage(this, key);
    }
    @Override
    public void operator(int key) {
        if (key == 0) {
            System.out.println("Alarm close");
        }else if (key ==1){
            System.out.println("Alarm open");
        }
    }
}

class CoffeeMachine implements Colleague{
    private final String name;
    private final Mediator mediator;
    public CoffeeMachine(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
        this.mediator.add(name, this);
    }
    @Override
    public void sendMessage(int key) {
        String msg = this.name + " 发出了消息";
        System.out.println(msg);
        this.mediator.getMessage(this, key);
    }
    @Override
    public void operator(int key) {
        if (key == 0) {
            System.out.println("CoffeeMachine close");
        }else if (key ==1){
            System.out.println("CoffeeMachine open");
        }
    }
}
class TV implements Colleague{
    private final String name;
    private final Mediator mediator;
    public TV(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
        this.mediator.add(name, this);
    }
    @Override
    public void sendMessage(int key) {
        String msg = this.name + " 发出了消息";
        System.out.println(msg);
        this.mediator.getMessage(this, key);
    }

    @Override
    public void operator(int key) {
        if (key == 0) {
            System.out.println("TV close");
        }else if (key ==1){
            System.out.println("TV open");
        }
    }
}

/**
 * 抽象中介者mediator
 */
interface Mediator {
    void add(String name, Colleague colleague);
    String getMessage(Colleague colleague, int key);
}
/**
 * 抽象 同事类
 */
interface Colleague {
    void sendMessage(int key);
    void operator(int key);
}