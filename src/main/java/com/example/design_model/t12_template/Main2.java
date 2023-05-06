package com.example.design_model.t12_template;

/**
 * @author LuoXianchao
 * @since 2023/05/03 17:33
 */
public class Main2 {
    public static void main(String[] args) {
        AbstractDrink2 drink = new ConcreteDrink2("调味品 1,2,3,4,5");
        String data = drink.excute("大豆 花生米 水");
        System.out.println(data);
    }
}
/**
 * 模板方法种的钩子方法：在模板方法模式的父类中，我们可以定义一个方法，它默认不做任何事情，
 * 子类可以似情况要不要覆盖这个方法，这个方法就称为钩子
 */

/**
 * 具体实现子类
 */
class ConcreteDrink2 extends AbstractDrink2 {

    private String args;

    public ConcreteDrink2(String args) {
        this.args = args;
    }

    @Override
    protected String selectMaterial(String origin) {
        System.out.println(origin + "材料挑选中 。。。");
        return "< 挑选后的材料" + origin + " >";
    }

    @Override
    protected String addIngredients(String origin) {
        System.out.println(origin + "添加配料中 。。。");
        return "[ " + args + "添加配料后的材料" + origin + " ]";
    }

    // @Override
    // protected boolean isAddIngredients() {
    //     return !super.isAddIngredients();
    // }
}

/**
 * 定义抽象父类
 */
abstract class AbstractDrink2 {
    // 选材

    /**
     * 钩子方法
     */
    protected String selectMaterial(String origin) {
        System.out.println(origin + "材料挑选中 。。。");
        return "< 挑选后的材料" + origin + " >";
    }

    // 添加配料
    protected abstract String addIngredients(String origin);

    //浸泡
    protected String soak(String origin) {
        System.out.println(origin + "材料浸泡中 。。。");
        return "{ 浸泡后的材料" + origin + " }";
    }

    // 打碎
    protected String smash(String origin) {
        System.out.println(origin + "材料打碎中 。。。");
        return "【 打碎后的材料" + origin + " 】";
    }

    // final修饰 禁止被子类重写
    public final String excute(String origin) {
        origin = selectMaterial(origin);
        if (isAddIngredients()) {
            origin = addIngredients(origin);
        }
        origin = soak(origin);
        origin = smash(origin);
        return origin;
    }

    /**
     * 钩子方法
     */
    protected boolean isAddIngredients() {
        return true;
    }
}