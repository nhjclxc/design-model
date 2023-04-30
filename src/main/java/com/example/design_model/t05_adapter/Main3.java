package com.example.design_model.t05_adapter;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main3 {
    public static void main(String[] args) {

        // 默认适配器模式，使用匿名内部类的方式实现抽象类的方法
        Phone3 phone31 = new Phone3();
        phone31.charge(new AbstractVoltageAdapter(new Voltage220V3Impl()) {
            /**
             * 匿名内部类的方式，有选择性的实现接口的方法
             * VoltageAdapter3具体实现适配器
             */
            @Override
            public Integer output5V() {
                Integer integer = super.output5V();//7
                System.out.println(integer);
                return integer - 2;
            }
        });

        Phone3 phone32 = new Phone3();
        phone32.charge(new AbstractVoltageAdapter(new Voltage220V3Impl()) {
            /**
             * 匿名内部类的方式，有选择性的实现接口的方法
             * VoltageAdapter3具体实现适配器
             */
            @Override
            public Integer output5V() {
                Integer integer = super.output5V();//7
                System.out.println(integer);
                return integer - 2;
            }
        });
    }
}
/**
 * 接口适配器模式（缺省适配器模式）
 *  当不需要全部实现目标接口提供的方法时，可以先设计一个出现类实现接口（被适配者），
 *      并为该接口中每个方法提供一个默认实现（方法体为空），那么该抽象类的子类就可以有选择型的覆盖某些方法来实现需求。
 *
 *  适用于一个接口不想使用其所有方法的情况
 *
 */

/**
 * 被适配者
 */
abstract class Voltage220V3{
    abstract Integer output220V();
}
class Voltage220V3Impl extends Voltage220V3{
    public Integer output220V(){
        System.out.println("source返回：" + 10);
        return 10;
    }
}
/**
 * 目标
 */
interface Voltage5V3 {
    Integer output5V();
    Integer output5V123();
    Integer output5V456();
}
/**
 * AbstractVoltageAdapter作为适配器的缓冲层
 */
abstract class AbstractVoltageAdapter implements Voltage5V3 {
    protected Voltage220V3 voltage220V3;
    public AbstractVoltageAdapter(Voltage220V3 voltage220V3) {
        this.voltage220V3 = voltage220V3;
    }
    @Override
    public Integer output5V() {
        int source = voltage220V3.output220V();
        source -= 3;
        System.out.println("抽象适配器输出电压：" + source);
        return source;
    }
    @Override
    public Integer output5V123() {
        return null;
    }
    @Override
    public Integer output5V456() {
        return null;
    }
}
class Phone3{
    public void charge(AbstractVoltageAdapter adapter){
        System.out.println("充入电量：" + adapter.output5V());
    }
}

