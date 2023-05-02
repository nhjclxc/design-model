package com.example.design_model.t08_composite;

import java.util.*;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main1 {
    public static void main(String[] args) {
        Leaf leaf11 = new Leaf("计算机科学与技术");
        Leaf leaf12 = new Leaf("软件过程");
        Leaf leaf13 = new Leaf("电子信息");
        Component computerCollege = new Composite("计算机学院", new ArrayList<>(Arrays.asList(leaf11, leaf12, leaf13)));

        Leaf leaf21 = new Leaf("公共管理");
        Leaf leaf22 = new Leaf("旅游管理");
        Component manageCollege = new Composite("管理学院", new ArrayList<>(Arrays.asList(leaf21, leaf22)));

        Leaf leaf31 = new Leaf("艺术设计");
        Component artCollege = new Composite("美术学院", new ArrayList<>(Collections.singletonList(leaf31)));


        List<Component> collegeList = Arrays.asList(computerCollege, manageCollege, artCollege);
        Component university = new Composite("知名大学", collegeList);

        System.out.println(university);
        System.out.println();

        // 组合模式提供了一种统一的管理方式，无论是叶子节点还是费叶子节点，都可以使用Component提供的方法来管理本节点
        Component artCollege1 = university.getChild(0);
        System.out.println(artCollege1);
        artCollege1.reName("信息科学与工程学院");
        System.out.println(artCollege1);
        Component leaf131 = artCollege1.getChild(2);
        System.out.println(leaf131);
        leaf131.reName("通信工程");
        System.out.println(leaf131);
        System.out.println(artCollege1);
        System.out.println();


        //
        Component artCollege2 = university.getChild(2);
        System.out.println(artCollege2);
        Leaf leaf32 = new Leaf("服装设计");
        artCollege2.add(leaf32);
        System.out.println(artCollege2);

        artCollege2.remove(leaf31);
        System.out.println(artCollege2);


        System.out.println();
        System.out.println(university);

        System.out.println();
        university.print();

        System.out.println();
        computerCollege.print();
        System.out.println();

        leaf12.print();

    }
}

/**
 *
 */
abstract class Component{
    public String name;

    /**
     * 抽象类的构造方法最好写出 protected防止外部调用（抽象类不支持实例化），同时可以提供给子类使用
     */
    protected Component(String name) {
        this.name = name;
    }

    /**
     * 当叶子节点调用没有重写的时候调用以下方法就会抛出 UnsupportedOperationException不支持操作的异常
     */
    void add(Component component){ throw new UnsupportedOperationException(); }
    void remove(Component component){ throw new UnsupportedOperationException();}
    Component getChild(int i){ throw new UnsupportedOperationException(); }
    String reName(String newName){
        this.name = newName;
        return this.name;
    }
    public void print(){
        System.out.println(this.name);
    }
    @Override
    public String toString() {
        return "Component{" +
                "name='" + name + '\'' +
                '}';
    }
}

/**
 * 对于这个Composite类，我这里是用这一个类来表示学校和学院，并没有将学校和学院分开表示，后期随着业务逻辑的变更可能学校和学院的操作逻辑不同
 * 这时你就要考虑将Composite类拆成 学校University 和 学院College等多个类别来具体到莫格实体上面
 */
class Composite extends Component{
    private List<Component> components;
    public Composite(String name, List<Component> components) {
        super(name);
        this.components = components;
    }
    @Override
    public void add(Component component) {
        if (Objects.isNull(components)){
            components = new ArrayList<>();
        }
        components.add(component);
    }
    @Override
    public void remove(Component component) {
        if (Objects.isNull(components)){
            return;
        }
        components.remove(component);
    }
    @Override
    public Component getChild(int i) {
        if (Objects.isNull(components)){
            return null;
        }
        return components.get(i);
    }

    @Override
    public String toString() {
        return "Composite{" +
                "name=" + name +
                "，components=" + components +
                '}';
    }

    public void print(){
//        StringBuilder sb = new StringBuilder();
//        sb.append("【").append(this.name);
//        for (Component com : this.components) {
//            sb.append("[").append(com.name);
//            if (com instanceof Composite){
//                Composite temp = (Composite) com;
//                for (Component component : temp.components) {
//                    sb.append("<").append(component.name).append(">,");
//                }
//            }
//            sb.append("]、");
//        }
//        sb.append("】");
//        System.out.println(sb);

        System.out.println("================"+this.name+"================");
        for (Component com : this.components) {
            System.out.println("--------"+com.name+"--------");
            if (com instanceof Composite){
                Composite temp = (Composite) com;
                for (Component component : temp.components) {
                    System.out.println(component.name);
                }
            }
        }
    }
}

class Leaf extends Component{
    @Override
    public void print() {
        super.print();
    }

    public Leaf(String name) {
        super(name);
    }
}



