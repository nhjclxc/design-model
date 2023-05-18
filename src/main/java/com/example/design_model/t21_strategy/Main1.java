package com.example.design_model.t21_strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author LuoXianchao
 * @since 2023/5/17 21:38
 */
public class Main1 {
    public static void main(String[] args) {
        Duck duck = new PekingDuck(new GoodFlyStrategy(), new GoodQuackStrategy());
        duck.fly();
        duck.quack();

        duck.setQuackStrategy(new NoQuackStrategy());
        duck.fly();
        duck.quack();
        System.out.println();
        System.out.println();

        Duck duck2 = new ToyDuck(new NoFlyStrategy(), new NoQuackStrategy());
        duck2.fly();
        duck2.quack();

        duck2.setFlyStrategy(new GoodFlyStrategy());
        duck2.fly();
        duck2.quack();
    }
}
/**
 * 抽象鸭子类
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
abstract class Duck{
    protected FlyStrategy flyStrategy;
    protected QuackStrategy quackStrategy;
    public void fly(){
        flyStrategy.fly();
    }
    public void quack(){
        quackStrategy.quack();
    }
}
class PekingDuck extends Duck{
    public PekingDuck() { super(); }
    public PekingDuck(FlyStrategy flyStrategy, QuackStrategy quackStrategy) {
        super(flyStrategy,quackStrategy);
    }
}
class ToyDuck extends Duck{
    public ToyDuck() { super(); }
    public ToyDuck(FlyStrategy flyStrategy, QuackStrategy quackStrategy) {
        super(flyStrategy,quackStrategy);
    }
}
/**
 * 飞行策略行为
 */
interface FlyStrategy{
    void fly();
}
class GoodFlyStrategy implements FlyStrategy{
    @Override
    public void fly() {
        System.out.println("GoodFlyStrategy.fly");
    }
}
class NoFlyStrategy implements FlyStrategy{
    @Override
    public void fly() {
        System.out.println("NoFlyStrategy.fly");
    }
}
/**
 * 叫策略行为
 */
interface QuackStrategy{
    void quack();
}
class GoodQuackStrategy implements QuackStrategy{
    @Override
    public void quack() {
        System.out.println("GoodQuackStrategy.quack");
    }
}
class NoQuackStrategy implements QuackStrategy{
    @Override
    public void quack() {
        System.out.println("NoQuackStrategy.quack");
    }
}
