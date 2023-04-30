package com.example.design_model.t05_adapter;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main1 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());

        System.out.println(" ------- ");
        Phone phone2 = new Phone();
        phone2.charging(new QuickVoltageAdapter());
    }
}
/**
 * 类适配器模式的实现：
 *      Adapter类继承被适配者(source)，实现目标(target)的接口，来完成 source -> target
 *      （被适配器类继承的就是源；转化的哪个类就是适配器类；适配器类数出来的就是目标）
 */

/**
 * 被适配者
 */
class Voltage220V{
    private static final int SOURCE = 10;
    protected Integer output220V(){
        System.out.println("输出220V电量：" + SOURCE);
        return SOURCE;
    }
}
/**
 * 输出目标（适配接口）
 */
interface IVoltage5V {
    Integer output5V();
}
/**适配器
 *      继承被适配者类（输入）
 *      实现目标接口（输出）
 */
class VoltageAdapter extends Voltage220V implements IVoltage5V {
    @Override
    public Integer output5V() {
        // 获取220V电量
        Integer src = super.output220V();
        System.out.println("适配器得到电量：" + src);

        // 适配器的具体转化过程
        int target = src - 9;

        // 返回转化之后的电量
        System.out.println("适配器输出电量：" + target);
        return target;
    }
}

/**
 * 超级快充
 */
class QuickVoltageAdapter extends Voltage220V implements IVoltage5V {
    @Override
    public Integer output5V() {
        // 获取220V电量
        Integer src = super.output220V();
        System.out.println("快充适配器得到电量：" + src);

        // 适配器的具体转化过程
        int target = src - 3;

        // 返回转化之后的电量
        System.out.println("快充适配器输出电量：" + target);
        return target;
    }
}
/**
 * 使用的客户端
 */
class Phone {
    private Integer electricQuantity = 90;
//    private IVoltage5V iVoltage5V;
//
//    public void setiVoltage5V(IVoltage5V iVoltage5V) {
//        this.iVoltage5V = iVoltage5V;
//    }

    /**
     * 充电方法，聚合接口 IVoltage5V
     */
    public void charging(IVoltage5V iVoltage5V) throws InterruptedException {
        while (true) {
            int temp = this.electricQuantity + iVoltage5V.output5V();

            // 手机电量保护
            if (temp > 100){
                System.out.println("充电已完成，充电保护自动断开电源连接。");
                break;
            }
            this.electricQuantity = temp;
            System.out.println(" now electricQuantity = " + this.electricQuantity);
            System.out.println();
            Thread.sleep(800);
        }
    }
}
