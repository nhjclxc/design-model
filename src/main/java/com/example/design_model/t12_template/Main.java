package com.example.design_model.t12_template;

/**
 * @author LuoXianchao
 * @since 2023/05/03 17:18
 */
public class Main {

}
/**
 * 模板方法模式：Template Method Pattern
 *  1、在一个抽象类公开定义了执行它的方法模板，他的子类可以按需要重写被组合的模板方法，但模板方法还是父类的
 *  2、模板方法定义一个操作中的算法骨架（方法调用顺序），而将一些方法执行的具体过程延迟到子类中，
 *      使得子类可以不改变一个算法的结构，就可以重新定义算法的某些步骤
 *
 *  抽象父类要实现模板方法，就是在这里定义算法的执行流程，并且用final修饰，防止子类重写 public final Object excute(){ // 执行...}
 *  子类仅仅重写具体的实现，不对excute方法进行修改
 */