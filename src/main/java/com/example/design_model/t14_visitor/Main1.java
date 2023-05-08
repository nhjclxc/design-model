package com.example.design_model.t14_visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author LuoXianchao
 * @since 2023/5/7 17:18
 */
public class Main1 {
    public static void main(String[] args) {
        // 通过的
        ObjectStructure passObjectStructure = new ObjectStructure();
        WomanElement womanElement = new WomanElement();
        passObjectStructure.add(womanElement);
        ManElement manElement = new ManElement();
        passObjectStructure.add(manElement);
        passObjectStructure.display(new PassVisitor());

        System.out.println("_______________");
        // 不通过的
        ObjectStructure rejectObjectStructure = new ObjectStructure();
        rejectObjectStructure.add(new WomanElement());
        rejectObjectStructure.display(new RejectVisitor());

        // 未知的
        ObjectStructure unkonwObjectStructure = new ObjectStructure();
        unkonwObjectStructure.add(new WomanElement());
        unkonwObjectStructure.add(new ManElement());
        unkonwObjectStructure.display(new Unkonw());



    }
}
/**
 * 访问者模式
 * 1、优点：访问者模式符合单一职责原则、让程序具有优秀的扩展性，访问者模式可以对功能进行统一，也就是说访问者 关注了其他类的内部细节
 *      可以做报表、UI、拦截器和过滤器等等，适用于数据结构相对稳定的系统
 */

/**
 * 打分系统（数据结构）
 */
class ObjectStructure {
    private final List<Element> elements = new LinkedList<>();
    public ObjectStructure( ) {
    }
    public void add(Element element) {
        elements.add(element);
    }
    public void display(Visitor visitor) {
        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}

/**
 * 具体被访问者
 */
class WomanElement implements Element{
    @Override
    public void accept(Visitor visitor) {
        System.out.print("WomanElement " );
        visitor.score();
    }
}
class ManElement implements Element{
    @Override
    public void accept(Visitor visitor) {
        System.out.print("ManElement " );
        visitor.score();
    }
}
/**
 * 抽象被访问者
 */
interface Element{
    /**
     * 被访问者必须提供一个accept方法，来接收访问者
     * @param visitor 访问者
     */
    void accept(Visitor visitor);
}

/**
 * 访问者具体实现类
 */
class PassVisitor implements Visitor{
    @Override
    public void score() {
        System.out.println("通过 Pass");
    }
}
class RejectVisitor implements Visitor{
    @Override
    public void score() {
        System.out.println("驳回 Reject");
    }
}
class Unkonw implements Visitor{
    @Override
    public void score() {
        System.out.println("未知 Unkonw");
    }
}
/**
 * 抽象访问者
 */
interface Visitor{
    void score();
}