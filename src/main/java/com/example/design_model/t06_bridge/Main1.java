package com.example.design_model.t06_bridge;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main1 {
    public static void main(String[] args) {

        System.out.println(new PhoneImpl(new FoldedPhoneStyle(), new IPhone()).getStyleBrand());
        System.out.println(new PhoneImpl(new FoldedPhoneStyle(), new LG()).getStyleBrand());
        System.out.println(new PhoneImpl(new UpRightPhoneStyle(), new IPhone()).getStyleBrand());
        System.out.println(new PhoneImpl(new UpRightPhoneStyle(), new LG()).getStyleBrand());

        System.out.println();
        // 以下是新增的手机样式 / 手机品牌
        PhoneImpl fullScreenSamsungPhone = new PhoneImpl(new FullScreenStyle(), new Samsung());
        System.out.println(fullScreenSamsungPhone.getStyleBrand());
        fullScreenSamsungPhone.call();
        System.out.println(fullScreenSamsungPhone.getStyle());

    }
}
/**桥接模式的注意事项
 * 1）实现了抽象和实现部分的分离，从而极大的提供了系统的灵活性，让抽象部分和实
 * 现部分独立开来，这有助于系统进行分层设计，从而产生更好的结构化系统。
 * 2)对于系统的高层部分，只需要知道抽象部分和实现部分的接口就可以了，其它的部
 * 分由具体业务来完成。
 * 3)桥接模式替代多层继承方案，可以减少子类的个数，降低系统的管理和维护成本。
 * 4)桥接模式的引入增加了系统的理解和设计难度，由于聚合关联关系建立在抽象层，
 * 要求开发者针对抽象进行设计和编程
 * 5)桥接模式要求正确识别出系统中两个独立变化的维度，因此其使用范围有一定的局
 * 限性，即需要有这样的应用场景。
 *
 * 桥接模式应用场景
 * 1）对于那些不希望使用继承或因为多层次继承导致系统类的个数急剧增加的系统，桥
 * 接模式尤为适用.
 * 2)常见的应用场景:
 *  -JDBc驱动程序
 *  -银行转账系统
 *      转账分类:网上转账，柜台转账，AMT车专账
 *      车转专账用户类型:普通用户，银卡用户，金卡用户..
 *  -消息管理
 *      消息类型:即时消息,延时消息
 *      消息分类:手机短信，邮件消息，QQ消息...
 */

/**
 * 品牌实现
 */
interface Brand{
    void call();
    String getBrandName();
}
class IPhone implements Brand{
    @Override
    public void call() {
        System.out.println("IPhone call");
    }
    public String getBrandName() {
        return "IPhone";
    }
}
class LG implements Brand{
    public String getBrandName() {
        return "LG";
    }
    @Override
    public void call() {
        System.out.println("LG call");
    }
}

/**
 * 样式抽象，样式一般都是一些固定的都西，属性比较多，所以这里使用抽象类
 * 而手机品牌的行为却是一个不断变化的东西，所以上面的Brnd使用了接口，去不断的追加定义方法
 */
abstract class Style{
    protected String style;

    public Style(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
class FoldedPhoneStyle extends Style{
    public FoldedPhoneStyle() {
        super("Folded");
    }
}
class UpRightPhoneStyle extends Style{
    public UpRightPhoneStyle() {
        super("UpRight");
    }
}
/**
 * Phone类作为Style和Brand的桥梁
 * 手机抽象类里面有样式 有品牌，互不相关
 */
abstract class AbstractPhone{
    /**
     * 对Phone类外部，不直接提供对Style和Brand属性的操作
     * 而是已Phone类开放方法的形式让别人修改
     */
    /**
     * 聚合样式
     */
    private final Style style;
    /**
     * 聚合品牌
     */
    private final Brand brand;
    protected AbstractPhone(Style style, Brand brand) {
        this.style = style;
        this.brand = brand;
    }

    protected String getStyleBrand(){
        return this.style.getStyle() + "的" + this.brand.getBrandName();
    }

    protected String getStyle() {
        return style.getStyle();
    }

    protected void call() {
        this.brand.call();
    }
}
class PhoneImpl extends AbstractPhone{

    public PhoneImpl(Style style, Brand brand) {
        super(style, brand);
    }
}

// 以下可以认为是新增的手机品牌或手机样式

/**
 * 品牌，直接增加品牌，与与现有的品牌或手机样式无无任何关系
 */
class Samsung implements Brand{
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
class FullScreenStyle extends Style{
    public FullScreenStyle() {
        super("FullScreen");
    }
}


