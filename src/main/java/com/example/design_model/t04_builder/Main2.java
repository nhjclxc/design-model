package com.example.design_model.t04_builder;

import lombok.Data;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main2 {
    public static void main(String[] args) {
        Hourse2Driector hourse2Driector = new Hourse2Driector(new CommonHourse2Builder());
        House2 house2 = hourse2Driector.construct();
        System.out.println(house2);
        System.out.println();

        hourse2Driector.setBuilder(new valliHourse2Builder());
        House2 house21 = hourse2Driector.construct();
        System.out.println(house21);


//        StringBuilder

    }
}

/**
 * 建造者模式的基本介绍，又叫生成器模式，是一种对象的构建模式
 * 它可以将复杂的对象创建过程抽象出来（抽象类别），使得这个抽象过程的不用实现方式可以构造出不同表现（属性）的对象
 * 构造者模式是一步一步创建的一个复杂对象，他允许用户只通过指定复杂对象的类型和内容就可以构建它们，
 * 用户不需要知道内部的构建实现细节
 *
 * 建造者模式的四个角色
 * 1、Product产品角色：一个具体的产品对象
 * 2、Builder抽象建造者：创建一个Product对象的各个部件指定的接口
 * 3、ConcreateBuilder具体建造者：实现接口，构建和装配各个部件
 * 4、Director指挥者：构建一个使用Builder接口的对象。它主要是用于创建一个复杂的对象，主要有两个作用
 *      4.1、隔离了客户与对象的生产过程。4.2、负责控制产品对象的生产过程。
 *
 * 理解上述四个对象：
 *   1、产品就是我要创建的对象
 *   2、抽象建造者就是创建定义创建产品都要有什么步骤，但是抽象建造者不会去实现具体的步骤，具体实现交给具体建造者
 *   3、具体建造者就是去继承并且实现抽象建造者定义的方法细节，实现每一个创建步骤（只实现没一个步骤，每一个步骤之间没联系，要通过指挥者来联系）
 *   4、指挥者里面聚合了抽象建造者，但是实际使用的时候是其子类，指挥者的主要作用是将具体建造者的实现步骤组合在一期
 *
 * 具体的建造者实现类个数跟据产品的不同型式而定
 *
 *
 * 抽象工厂模式与建造者模式的区别：
 *     抽象工厂模式实现对产品家族的创建，一个产品家族是这样的一系列产品:具有不同分类维度的产品组合，采用抽象工厂模式不需要关心构建过程，只关心什么产品
 *     由什么工厂生产即可。而建造者模式则是要求按照指定的蓝图建造产品，它的主要目的是通过组装零配件而产生一个新产品
 */

/**
 * 指挥者
 */
class Hourse2Driector {
    /**
     * 指挥者针对抽象建造者编程，后续的建造者可以无限制的追加
     * 符合开闭原则
     */
    public AbstractHourse2Builder builder;
    public Hourse2Driector(){}
    public Hourse2Driector(AbstractHourse2Builder hourse2Builder){
        this.builder = hourse2Builder;
    }

    public void setBuilder(AbstractHourse2Builder hourse2Builder){
        this.builder = hourse2Builder;
    }

    /**
     * 指挥者去将具体创建过程连接起来
     * 建创建的过程组合在一起
     */
    public House2 construct(){
        builder.buildBase();
        builder.buildWall();
        builder.buildRoof();
        return builder.build();
    }
}

/**
 * 定义产品的抽象生产过程
 */
abstract class AbstractHourse2Builder {
    protected House2 house2 = new House2(); //
    // 可以将以下三个方法的返回值定义为Builder，方便后续使用链式调用
    public abstract void buildBase();
    public abstract void buildWall();
    public abstract void buildRoof();
    public  House2 build(){
        return house2;
    }
}

/**
 * 实现产品的具体生产过程
 */
class CommonHourse2Builder extends AbstractHourse2Builder {
    public void buildBase(){
        System.out.println("CommonBuilder buildBase");
        this.house2.name =  "CommonBuilder buildBase";
    }
    public void buildWall(){
        System.out.println("CommonBuilder buildWall");
        this.house2.name +=  " + buildWall";
    }
    public void buildRoof(){
        System.out.println("CommonBuilder buildRoof");
        this.house2.name +=  " + buildRoof";
    }
    public House2 build(){
        return this.house2;
    }
}
/**
 * 实现产品的具体生产过程
 */
class valliHourse2Builder extends AbstractHourse2Builder {
    public void buildBase(){
        System.out.println("valliBuilder buildBase");
        this.house2.name =  "valliBuilder buildBase";
    }
    public void buildWall(){
        System.out.println("valliBuilder buildWall");
        this.house2.name +=  " + buildWall";
    }
    public void buildRoof(){
        System.out.println("valliBuilder buildRoof");
        this.house2.name +=  " + buildRoof";
    }
    public House2 build(){
        return this.house2;
    }
}

/**
 * 产品
 */
@Data
class House2{
    public String name;
}