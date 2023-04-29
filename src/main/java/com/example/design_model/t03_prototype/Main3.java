package com.example.design_model.t03_prototype;


import lombok.ToString;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main3 {
    public static void main(String[] args) {
//        Sheep3 sheep31 = new Sheep3("小肥羊 666", 5, "大黑山");
//        Sheep3 sheep32 = sheep31.clone();
//        System.out.println(sheep31 );
//        System.out.println(sheep32 );
//        System.out.println(Integer.toHexString(sheep31.hashCode()) );
//        System.out.println(Integer.toHexString(sheep32.hashCode()) );
//        System.out.println(sheep31 == sheep32);

        Sheep3 sheep31 = new Sheep3("小肥羊 666", 5, "大黑山");
        sheep31.friend = new Sheep3("美羊羊 666", 6, "小粉色");
        Sheep3 sheep32 = sheep31.clone();
        // 现在我想知道内部那只羊的信息
        System.out.println(sheep31.friend );
        System.out.println(sheep32.friend );
        // 通过下面语句的输出，我们很容易得出了内部对象的拷贝是浅拷贝，即只拷贝了地址没有拷贝对象内部的属性值
        System.out.println(Integer.toHexString(sheep31.friend.hashCode()) );
        System.out.println(Integer.toHexString(sheep32.friend.hashCode()) );
        System.out.println(sheep31.friend == sheep32.friend);//true


    }
}

/**
 * 在Main1、Main2的对象里面都是基本属性，而没有引用的对象，如果有引用对象，
 * 那么引用对象clone之后的结果又是什么呢？？？？？
 *  即我们经过前面的分析，使用clone()出来的对象其实是一个新的对象（进行了深拷贝），使用toString打印出来的信息虽然都是一样的，
 *  但是他们的地址不同Integer.toHexString(sheep31.hashCode())
 *
 *  在回到上面的问题，如果对象里面还有对象的话，内部的对象是深拷贝还是浅拷贝呢？？
 *  下面的类就使用一个friend属性来试试
 *          通过上面main方法的输出可以知道，内部对象使用的是浅拷贝，如何实现深拷贝呢？请看Main4
 */

/** 浅拷贝介绍
 * 对于数据类型是基本数据类型的成员变量，浅拷贝会直接进行值传递， 也就是将 该属性值复制一份给新的对象。
 * 对于数据类型是引用数据类型的成员变量，比如说成员变量是某个数组、某个类的对象等，那么浅拷贝会进行引用传递，
 *  也就是只是将该成员变量的引用值（内存地址）复制一份给新的对象。因为实际上两个对象的该成员变量都指向同一个实例。
 *   在这种情况下，在一个对象中修改该成员变量会影响到另一个对象的该成员变量值
 * 前面我们克隆羊就是浅拷贝，浅拷贝是使用默认的clone()方法来实现sheep = (Sheep) super.clone();
 *
 *
 * 其实对应上面所说的无论是基本数据类型还是引用数据类型，在Java中都是进行值传递，
 *  而不同的是基本数据类型里面保存的值就是数据，而对于引用数据类型而言，里面保存的是一个对象的地址，
 *  进而造成了使用.clone()方法进行拷贝的时候就出现了基本数据类型是深拷贝，而引用数据类型缺是浅拷贝的原因
 */
@ToString
class Sheep3 implements Cloneable{
    public String name;
    public Integer age;
    public String color;

    public Sheep3 friend;

    public Sheep3() {}
    public Sheep3(String name, Integer age, String color ) {
        this.name = name;
        this.age = age;
        this.color = color;
    }


    @Override
    public Sheep3 clone() {
        try {
            Sheep3 clone = (Sheep3) super.clone(); //super.clone()返回Object
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}