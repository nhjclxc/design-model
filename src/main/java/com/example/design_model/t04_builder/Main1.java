package com.example.design_model.t04_builder;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main1 {
    public static void main(String[] args) {
        new CommonHouse().build();
        new VillaHouse().build();
    }
}

/**
 * 未使用建造者模式的缺点：将产品（房子）和产品的创建过程（建房子的流程）耦合
 * 解决方案：将产品与产品的创建过程姐耦合   --->>  使用建造者模式
 */
abstract class AbstractHouse {
    public abstract void buildBase();

    public abstract void buildWall();

    public abstract void buildRoof();
}

class CommonHouse extends AbstractHouse {
    public void build() {
        buildBase();
        buildWall();
        buildRoof();
    }

    @Override
    public void buildBase() {
        System.out.println("CommonHouse buildBase");
    }

    @Override
    public void buildWall() {
        System.out.println("CommonHouse buildWall");
    }

    @Override
    public void buildRoof() {
        System.out.println("CommonHouse buildRoof");
    }
}

class VillaHouse extends AbstractHouse {
    public void build() {
        buildBase();
        buildWall();
        buildRoof();
    }

    @Override
    public void buildBase() {
        System.out.println("VillaHouse buildBase");
    }

    @Override
    public void buildWall() {
        System.out.println("VillaHouse buildWall");
    }

    @Override
    public void buildRoof() {
        System.out.println("VillaHouse buildRoof");
    }
}