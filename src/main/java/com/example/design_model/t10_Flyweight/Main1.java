package com.example.design_model.t10_Flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * 五子棋问题
 *
 * @author LuoXianchao
 * @since 2023/05/02 19:32
 */
public class Main1 {
    public static void main(String[] args) {
        //　○●
        FlyweightFactory factory = new FlyweightFactory();
        factory.getFlyweigt("■").use(new Point(2,4));
        factory.getFlyweigt("■").use(new Point(3,4));
        factory.getFlyweigt("□").use(new Point(4,4));
        factory.getFlyweigt("■").use(new Point(5,4));
        factory.getFlyweigt("□").use(new Point(4,4));
        factory.getFlyweigt("□").use(new Point(2,4));
        factory.getFlyweigt("■").use(new Point(7,4));
    }
}

class FlyweightFactory{
    private Map<String, Flyweight> pool = new HashMap<>();

    /**
     * 保证一场棋局只会出现两种棋子
     */
    public Flyweight getFlyweigt(String color){//　○●
        Flyweight flyweight = pool.get(color);
        if (Objects.isNull(flyweight)) {
            flyweight = new ConcreteFlyweight(color);
            pool.put(color, flyweight); //
        }
        return flyweight;
    }

}
class ConcreteFlyweight extends Flyweight{
    public ConcreteFlyweight(String color) {
        super(color);
    }
}
abstract class Flyweight{
    /**
     * 享元模式对象共用的部分全部写道Flyweight类里面
     *          不共用的部分传进来（不要写在Flyweight里面）
     */
    /**
     * color 就是这个享元对象的内部状态，
     * 每一个对应的享元的该值都是同一个
     */
    protected String color;
    /**
     * point 就是这个享元对象的外部状态，
     * 每一个对应的享元的该值都不同
     */
    public Flyweight(String color) {
        this.color = color;
    }

    /**
     * 声明为static类型，一场棋局只能有一个棋盘
     */
    private static Flyweight[][] usedPointMatrix = new Flyweight[8][8];// 1 被占用

    public void use(Point point){ //　○●
        Integer x = point.getX();
        Integer y = point.getY();
        String msg = "("+x+","+y+")点被"+this.color+"该点位已被占用！";
        if (null != usedPointMatrix[x][y]){
            msg += "请重新下子。";
            System.out.println(msg);
            return;
        }
        System.out.println(msg);
        usedPointMatrix[x][y] = this;
        printChessboard();

        // 判断棋局是否结束（可根据八皇后思想判断） 依据结果终止游戏或继续游戏
    }

    public void printChessboard() {
        for (int i = 0; i < usedPointMatrix.length; i++) {
            for (int j = 0; j < usedPointMatrix[i].length; j++) {
                Flyweight flyweight = usedPointMatrix[i][j];
                if (null != flyweight){//　○●
                    System.out.print(flyweight.color +" ");
                }else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

/**
 * 外部状态
 */
class Point{
    private Integer x;
    private Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}