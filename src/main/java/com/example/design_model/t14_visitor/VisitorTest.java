package com.example.design_model.t14_visitor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LuoXianchao
 * @since 2023/05/30 12:01
 */
public class VisitorTest {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(3,4);
        rectangle.accept(new RearVisitor());

        // 扩充代码计算周长
        rectangle.accept(new PerimeterVisitor());
    }
}
// 下面有一个是初期的计算面积的访问者，现在我在增加一个计算周长的访问者
class PerimeterVisitor implements Visitor2{

    @Override
    public void visitor(Shape shape) {
        if (shape instanceof Rectangle){
            Rectangle rectangle = (Rectangle) shape;
            int perimeter = 2 * (rectangle.getLength() + rectangle.getWidth());
            System.out.println("perimeter = " + perimeter);
        }
    }
}
// 原始代码全部在下面
/**
 * 访问者类
 */
interface Visitor2{
    /**
     * 访问者类，提供一个visitor方法做这个发文章具体的事情
     */
   void visitor(Shape shape);
}
/**
 * 具体的访问者 面积
 */
class RearVisitor implements Visitor2{
    /**
     * 访问者类，提供一个visitor方法做这个发文章具体的事情
     */
    public void visitor(Shape shape){
        if (shape instanceof Rectangle){
            Rectangle rectangle = (Rectangle) shape;
            int area = rectangle.getLength() * rectangle.getWidth();
            System.out.println("area = " + area);
        }
    }
}
/**
 * 被访问者类
 */
interface Shape{
    /**
     * 被访问者类 必须提供一个accept方法接收一个访问者
     */
    void accept(Visitor2 visitor);
}
/**
 * 具体的被访问者
 */
@Data
@AllArgsConstructor
class Rectangle implements Shape{
    private Integer length;
    private Integer width;
    @Override
    public void accept(Visitor2 visitor) {
        visitor.visitor(this);
    }
}