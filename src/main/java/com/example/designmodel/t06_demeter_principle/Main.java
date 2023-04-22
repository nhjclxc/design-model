package com.example.designmodel.t06_demeter_principle;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LuoXianchao
 * @since 2023/4/22 13:35
 */
public class Main {
/**迪米特原则
 * 1.一个对应应该对其他对象保持最少的了解
 * 2.类与类关系越密切，耦合度就会越大
 * 3.迪米特原则：一个类对自己依赖的类知道的越少越好，也就是说，对于被依赖的类不管多么复杂，
 *  都尽量将逻辑封装在提供方的内部，即对于提供方而言，对外出了提供public的方法外，其他内部的实现细节不提供给别人使用（不暴露给别人）
 * 4.迪米特原则规定，只与直接的朋友通信
 * 5.直接的朋友时什么？
 *  每一个对象都会与其他对象有耦合关系，只要两个对象之间有耦合关系，我们就是这两个对象之间是朋友，
 *  耦合的方式很多，依赖、关联、组合、聚合等。其中我们称出现成员变量、方法参数、方法返回值的类为直接朋友。
 *  而出现在局部变量中的类不是直接朋友。也就是说，陌生的类最好不要以局部变量的方式出现在类的内部。
 *
 * 迪米特原则：如果要使用其他类的话，尽量将其他类变成直接朋友（成员变量、参数、返回值）
 *      核心：降低类与类之间的耦合
 *
 */
/**类的依赖关系介绍：
 下面的两个类，即表示两个类之间的依赖关系，即B类依赖了A类
 class A{
     private int x;
 }
 class B{
     private A a;
 }


 */
    public static void main(String[] args) {
        List<Integer> list = Collections.singletonList(1);

    }

}
