package com.example.design_model.t07_decorator;


/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main1 {
    public static void main(String[] args) {
        // 两份巧克力Chocolate ＋ 一份牛奶Milk的 DecafCoffee   cost = 30*2 + 40 + 10 = 110

        // 记住：装饰者模式里面必须先有被装饰者对象，才能用装饰者来装饰这个被装饰者
/*
        // 先得到coffee  被装饰者
        AbstractComponentDrink decafCoffee = new DecafCoffee("DecafCoffee"); // 使用多态接收
        System.out.println(decafCoffee.getDesc());

        // 用牛奶装饰上面的 DecafCoffee 以下三个都是装饰者
        Milk milkDecafCoffee = new Milk("Milk", decafCoffee);
        System.out.println(milkDecafCoffee.getDesc());

        // 用巧克力装饰上面的 milkDecafCoffee
        Chocolate chocolateMilkDecafCoffee = new Chocolate("Chocolate", milkDecafCoffee);
        System.out.println(chocolateMilkDecafCoffee.getDesc());

        // 用巧克力装饰上面的 chocolateMilkDecafCoffee
        Chocolate DoubleChocolateMilkDecafCoffee = new Chocolate("Chocolate", chocolateMilkDecafCoffee);
        System.out.println(DoubleChocolateMilkDecafCoffee.getDesc());
        System.out.println(DoubleChocolateMilkDecafCoffee.getDescPrice());

        System.out.println(DoubleChocolateMilkDecafCoffee.cost());
        System.out.println(DoubleChocolateMilkDecafCoffee.discountCost());
*/
        AbstractComponentDrink drink = order();
        System.out.println(drink);
        if (drink instanceof Decorator){
            Decorator decorator = (Decorator) drink;
            System.out.println(decorator.getDescPrice());
        }
    }

    private static AbstractComponentDrink order() {
        AbstractComponentDrink drink = null;
        // 先得到coffee  被装饰者
        drink = new DecafCoffee("DecafCoffee"); // 使用多态接收
        System.out.println(drink.getDesc());

        // 用牛奶装饰上面的 DecafCoffee 以下三个都是装饰者
        drink = new Milk("Milk", drink);
        System.out.println(drink.getDesc());

        // 用巧克力装饰上面的 milkDecafCoffee
        drink = new Chocolate("Chocolate", drink);
        System.out.println(drink.getDesc());

        // 用巧克力装饰上面的 chocolateMilkDecafCoffee
        drink = new Chocolate("Chocolate", drink);
        System.out.println(drink.getDesc());

        System.out.println(drink.cost());
        return drink;
    }
}
/**
 * 记住：装饰者模式里面必须先有被装饰者对象，才能用装饰者来装饰这个被装饰者
 */

/**
 * 被装饰者
 */
// 抽象的被装饰者
abstract class AbstractComponentDrink {
    protected String desc;
    protected Integer price;

    public AbstractComponentDrink(String desc, Integer price) {
        this.desc = desc;
        this.price = price;
    }
    public int cost(){
        return price;
    }
    public String getDesc() {
        return price + "元一杯的" + desc;
    }
}
// 具体的被装饰者抽象类
abstract class AbstractComponentCoffee extends AbstractComponentDrink{
    public AbstractComponentCoffee(String desc, Integer price) {
        super(desc,price);
    }
    public int cost() {
        return super.cost();
    }
}
// 具体的被装饰者1
class DecafCoffee extends AbstractComponentCoffee{
    public DecafCoffee(String desc ) {
        super(desc,10);
    }
    @Override
    public int cost() {
        return super.cost();
    }
}
// 具体的被装饰者2
class ShortBlackCoffee extends AbstractComponentCoffee{
    public ShortBlackCoffee(String desc ) {
        super(desc, 20);
    }
    @Override
    public int cost() {
        return super.cost();
    }
}

/**
 * 装饰者
 */
// 装饰者抽象类
abstract class Decorator extends AbstractComponentDrink{
    /**
     * 装饰者模式的核心，即继承了被装饰者，又组合了被装饰者
     * 下面的acDrink就是被装饰者
     */
    protected AbstractComponentDrink acDrink; // 将Drink抽象类装起来
    public Decorator(String desc, Integer price, AbstractComponentDrink acDrink) {
        // 设置装饰者的信息
        super(desc, price);
        // 设置被装饰者的信息
        this.acDrink = acDrink; //acDrink就是被包装的对象，前一个对象
    }

    /**
     * 当多个装饰者被组合的时候，内部会实现递归的调用cost方法来获得花销
     */
    @Override
    public int cost() {
        // Drink的价格加上调料的价格
        return this.acDrink.cost() + super.price;

    }
    public String getDesc() {
        return this.acDrink.getDesc() +" 加上" + super.price + "元一份的" + super.desc;
    }
    public String getDescPrice() {
        return this.getDesc() + "，总结消费 cost = " + this.cost();
    }

    /**
     * 现在我有个新需求，现在搞活动，我要实现价格打折怎么计算？？？？？？
     */
    private double rate = 0.9;
    public int discountCost(){
        return (int) (this.cost() * rate);
    }
}
// 装饰者具体实现类1
class Chocolate extends Decorator{
    public Chocolate(String desc, AbstractComponentDrink acDrink) {
        super(desc, 30, acDrink);
    }
}
// 装饰者具体实现类2
class Milk extends Decorator{
    public Milk(String desc, AbstractComponentDrink acDrink) {
        super(desc, 40, acDrink);
    }
}





