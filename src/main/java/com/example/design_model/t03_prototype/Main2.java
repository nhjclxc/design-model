package com.example.design_model.t03_prototype;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main2 {
    public static void main(String[] args) {
        String name = "Tom";
        int age = 29;
        String color = "白色";
        Sheep2 sheep2 = new Sheep2(name, age, color,1,2);
        List<Sheep2> Sheep2List = new ArrayList<>();
        Sheep2List.add(sheep2);
        for (int i = 0; i < 9; i++) {
            Sheep2List.add(sheep2.clone());
        }
        Sheep2List.get(5).name = "aSDFGHJGFDS";
        for (Sheep2 Sheep2 : Sheep2List) {
            System.out.println(Sheep2 + " --- " + sheep2.hashCode());
        }
        System.out.println();
        sheep2.name = "小肥羊 666";
        for (Sheep2 Sheep2 : Sheep2List) {
            System.out.println(Sheep2 + " --- " + sheep2.hashCode());
        }
        Sheep2 sheep21 = Sheep2List.get(3);
        System.out.println(sheep2.equals(sheep21));
        System.out.println(sheep2.equals(Sheep2List.get(5)));

        System.out.println(Integer.toHexString(sheep2.hashCode()));
        System.out.println(Integer.toHexString(Sheep2List.get(3).hashCode()));
        System.out.println(Integer.toHexString(Sheep2List.get(5).hashCode()));

        System.out.println("---------------------");
        Sheep2 sheep22 = new Sheep2("小肥羊 666", 5, "粉色",3,2);
        Sheep2 sheep23 = sheep22.clone();
        System.out.println(sheep22 +" -- "+ sheep22.hashCode());
        System.out.println(sheep23 +" -- "+ sheep22.hashCode());
        //以下输出false，说明原型模式创建的对象只是属性相同，其内部数据地址不同
        System.out.println(sheep22 == sheep23);
        System.out.println(sheep22.equals(sheep23)) ;

    }
}

/**
 * Main1 方法中的改进思路
 * 由于java中Object类是所有类的根类，Object类提供了一额clone()方法，改方法可以将Java对象复制一份，
 * 但是需要实现clone的Java类必须要实现一个Cloneable接口，改接口表示改类能过复制切具有复制的能力
 *      ===>> 原型模式
 *
 *      原型模式创建的对象只是属性相同，其内部数据地址不同
 */
class Sheep2 implements Cloneable{
    public String name;
    public Integer age;
    public String color;

    public Sheep2(String name, Integer age, String color, Integer height, Integer weight) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.height = height;
        this.weight = weight;
    }

    /**
     * 当克隆类的属性不断增加时，使用sheep.clone();方法来进行克隆的时候无需做任何改变
     */
    public Integer height;
    public Integer weight;



    public Sheep2() {
    }

    @Override
    public Sheep2 clone() {
        try {
            Sheep2 clone = (Sheep2) super.clone(); //super.clone()返回Object
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}