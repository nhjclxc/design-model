package com.example.designmodel.t05_open_close.test2;

/**
 * @author LuoXianchao
 * @since 2023/4/21 21:58
 */
public class Main2 {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drwa(new Circle());
        graphicEditor.drwa(new Retangle());
        graphicEditor.drwa(new Triangle());
        graphicEditor.drwa(new Hyperbola());
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
        shape.draw();
    }
}
// 下面是解决test1.Mian.java不遵循开闭原则的方法
// 把Shape做成一个接口，并提供一个方法draw，让子类去实现，这样我们来了新的图像时只需让新的类去
//  实现Shape.draw()即可，这样过后当我们有新的图像来的时候不断的去实现Shape接口的draw方法即可，
// 在使用方GraphicEditor不需做任何代码修改，即可达到客户端新功能的添加

interface Shape{
    void draw();
}
class Circle implements Shape {
    public void draw(){
        System.out.println("draw Circle");
    }
}
class Retangle implements Shape {
    public void draw(){
        System.out.println("draw Retangle");
    }
}
class Triangle implements Shape{
    public void draw(){
        System.out.println("draw Triangle");
    }
}
// 双曲线
class Hyperbola implements Shape{
    public void draw(){
        System.out.println("draw Hyperbola");
    }
}

// abstract class Shape{
//     int type = 0;
//     public abstract void draw();
// }
// class Circle extends Shape {
//     public Circle(){
//         super.type = 1;
//     }
//     public void draw(){
//         System.out.println("drawCircle");
//     }
// }
// class Retangle extends Shape {
//     public Retangle(){
//         super.type = 2;
//     }
//     public void draw(){
//         System.out.println("drawRetangle");
//     }
// }
// class Triangle extends Shape{
//     public Triangle(){
//         super.type = 3;
//     }
//     public void draw(){
//         System.out.println("drawTriangle");
//     }
// }