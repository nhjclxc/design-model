package com.example.design_model.t06_bridge;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main2 {
    public static void main(String[] args) {

        FoldedPhone2 foldedPhone2 = new FoldedPhone2(new IPhone2());
        System.out.println(foldedPhone2.getStyleBrand());
        foldedPhone2.call();

        FullScreenPhone2 fullScreenPhone2 = new FullScreenPhone2(new LG2());
        System.out.println(fullScreenPhone2.getStyleBrand());
        fullScreenPhone2.call();
    }
}

/**
 * 品牌实现
 */
interface Brand2{
    void call();
    String getBrandName();
}
class IPhone2 implements Brand2{
    @Override
    public void call() {
        System.out.println("IPhone call");
    }
    public String getBrandName() {
        return "IPhone";
    }
}
class LG2 implements Brand2{
    public String getBrandName() {
        return "LG";
    }
    @Override
    public void call() {
        System.out.println("LG call");
    }
}

/**
 * 样式抽象
 */
abstract class AbstractPhone2{
    /**
     * 对Phone类外部，不直接提供对Style和Brand属性的操作
     * 而是已Phone类开放方法的形式让别人修改
     */
    private final String style;
    /**
     * 聚合品牌
     */
    private final Brand2 brand2;
    protected AbstractPhone2(String style, Brand2 brand2) {
        this.style = style;
        this.brand2 = brand2;
    }

    protected String getStyleBrand(){
        return this.style + "的" + this.brand2.getBrandName();
    }

    protected String getStyle() {
        return this.style;
    }

    protected void call() {
        this.brand2.call();
    }
}
class FoldedPhone2 extends AbstractPhone2{

    public FoldedPhone2(Brand2 brand2) {
        super("FoldedPhone", brand2);
    }
}

// 以下可以认为是新增的手机品牌或手机样式

/**
 * 品牌，直接增加品牌，与与现有的品牌或手机样式无无任何关系
 */
class Samsung2 implements Brand2{
    @Override
    public String getBrandName() {
        return "Samsung";
    }
    @Override
    public void call() {
        System.out.println("Samsung call");
    }
}

/**
 * 样式，直接增加样式，与与现有的品牌或手机样式无无任何关系
 */
class FullScreenPhone2 extends AbstractPhone2  {
    public FullScreenPhone2(Brand2 brand2) {
        super("FullScreen", brand2);
    }
}


