package com.example.design_model.t12_template;

/**
 * @author LuoXianchao
 * @since 2023/05/03 17:33
 */
public class Main1 {
    public static void main(String[] args) {
        AbstractDrink drink = new ConcreteDrink("调味品 1,2,3,4,5");
        String data = drink.excute("大豆 花生米 水");
        System.out.println(data);
    }
}
/**
 * 具体实现子类
 */
class ConcreteDrink extends AbstractDrink{

    private String args;

    public ConcreteDrink(String args) {
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
        return "[ "+args+"添加配料后的材料" + origin + " ]";
    }
}

/**
 * 定义抽象父类
 */
abstract class AbstractDrink{
    // 选材
    protected abstract String selectMaterial(String origin );
    // 添加配料
    protected abstract String addIngredients(String origin);
    //浸泡
    protected String soak(String origin){
        System.out.println(origin + "材料浸泡中 。。。");
        return "{ 浸泡后的材料" + origin + " }";
    }
    // 打碎
    protected String smash(String origin){
        System.out.println(origin + "材料打碎中 。。。");
        return "【 打碎后的材料" + origin +" 】";
    }
    // final修饰 禁止被子类重写
    public final String excute(String origin){
        String origin1 = selectMaterial(origin);
        String origin2 = addIngredients(origin1);
        String origin3 = soak(origin2);
        String origin4 = smash(origin3);
        return origin4;
    }
}