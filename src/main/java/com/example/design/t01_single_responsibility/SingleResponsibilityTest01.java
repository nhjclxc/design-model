package com.example.design.t01_single_responsibility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 方法单一职责原则
 *
 * @author LuoXianchao
 * @since 2023/4/16 17:15
 */
public class SingleResponsibilityTest01 {

    public static void main(String[] args) {
        VehicleRun vehicle = new Car("毛豆3");
        vehicle.run();

        vehicle = new Boat("泰坦尼克号");
        vehicle.run();

        vehicle = new Plane("B-2幽灵隐身轰炸机");
        vehicle.run();
    }

}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Vehicle {
    protected String name;

    public void runRoad() {
        System.out.println(this.name + " 在公路上跑。");
    }
    public void runWater() {
        System.out.println(this.name + " 在水上跑。");
    }
    public void runAir() {
        System.out.println(this.name + " 在天上飞。");
    }
}

