package com.example.design_model.t05_adapter;

import java.util.Objects;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        Phone2 phone2 = new Phone2();
        phone2.charging(new VoltageAdapter2(new Voltage2_220V()));
        System.out.println();
        System.out.println();

        Phone2 phone21 = new Phone2();
        phone21.charging(new VoltageAdapter2(new Voltage2_350V()));
    }
}
/**
 * 1、对象适配器：
 *  基本思路与类适配器相同，只是将Adapter类作了一些修改，不是继承被适配(src)的类了，而是持有src类的实例（成员变量），
 *  以解决兼容性问题，即对象适配器的实现为：持有被适配src类，实现目标target类接口，完成src --->> target的转化
 * 2、根据合成复用原则，在系统中尽量使用关联关系来代替继承关系。
 */


/**
 * 被适配者
 */
abstract class Voltage2 {
    private static final int SOURCE = 10;
    protected abstract Integer output220V();
}
class Voltage2_220V extends Voltage2 {
    private static final int SOURCE = 10;
    @Override
    protected Integer output220V(){
        System.out.println("输出220V电量：" + SOURCE);
        return SOURCE;
    }
}
class Voltage2_350V extends Voltage2{
    private static final int SOURCE = 20;
    protected Integer output220V(){
        System.out.println("输出350V电量：" + SOURCE);
        return SOURCE;
    }
}
/**
 * 输出目标（适配接口）
 */
interface IVoltage5V2 {
    Integer output5V();
}
/**适配器
 *      继承被适配者类（输入）
 *      实现目标接口（输出）
 */
class VoltageAdapter2 implements IVoltage5V2 {

    /**
     * 聚合
     */
    private Voltage2 voltage220V;

    VoltageAdapter2() {}
    VoltageAdapter2(Voltage2 voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public Integer output5V() {
        int target = 0;
        if (!Objects.isNull(voltage220V)) {
            // 获取220V电量
            Integer src = voltage220V.output220V();
            System.out.println("适配器得到电量：" + src);

            // 适配器的具体转化过程
            target = src - 8;

            // 返回转化之后的电量
            System.out.println("适配器输出电量：" + target);
        }
        return target;
    }
}

/**
 * 使用的客户端
 */
class Phone2 {
    private Integer electricQuantity = 70;

    /**
     * 充电方法，聚合接口 IVoltage5V
     */
    public void charging(IVoltage5V2 iVoltage5V) throws InterruptedException {
        while (true) {
            int temp = this.electricQuantity + iVoltage5V.output5V();

            // 手机电量保护
            if (temp >= 100 && this.electricQuantity <= 100){
                System.out.println("电量即将充满，改为慢充");
                temp = temp - 5 > 0 ? temp - 5 : 0;
            }

            this.electricQuantity = temp;
            System.out.println(" now electricQuantity = " + this.electricQuantity);
            System.out.println();
            Thread.sleep(800);

            if (temp >= 100 && this.electricQuantity >= 100){
                System.out.println("充电已完成，充电保护自动断开电源连接。");
                break;
            }
        }
    }
}


