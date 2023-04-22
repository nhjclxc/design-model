package com.example.design.t05_open_close.test1;

/**
 * @author LuoXianchao
 * @since 2023/4/21 21:58
 */
public class Main {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drwa(new Circle());
        graphicEditor.drwa(new Retangle());
    }
}

/**
 * 接口架构：
 * Shape
 *      --继承--> Circle
 *      --继承--> Retangle
 *
 * GraphicEditor
 *      --使用--> Shape
 *      --使用--> Circle
 *      --使用--> Retangle
 */

class GraphicEditor{
    public void drwa(Shape shape){
        if (shape.type == 1){
            System.out.println("drawCircle");
        }else if (shape.type == 2){
            System.out.println("drawRetangle");
        }

        //新增一个三角形类后要新增的代码如下，此时带来了一个问题
        // 再提供方接口那边做了修改，而且再使用方实现这边也修改了，这样维护就难了，违背了开闭原则
        else if (shape.type == 3){
            System.out.println("drawTriangle");
        }

    }
}

class Shape{
    int type;
    public void draw(){
        System.out.println("drawShape");
    }
}
class Circle extends Shape {
    public Circle(){
        super.type = 1;
    }
    public void draw(){
        System.out.println("drawCircle");
    }
}
class Retangle extends Shape {
    public Retangle(){
        super.type = 2;
    }
    public void draw(){
        System.out.println("drawRetangle");
    }
}


// 上面的实现看视很好，但是不遵循开闭原则，即如果此时我再加一个类三角形，
// 那么下面我会新增一个三角形的类，然而GraphicEditor的代码也必然要进行变动
class Triangle extends Shape{
    public Triangle(){
        super.type = 3;
    }
    public void draw(){
        System.out.println("drawTriangle");
    }
}