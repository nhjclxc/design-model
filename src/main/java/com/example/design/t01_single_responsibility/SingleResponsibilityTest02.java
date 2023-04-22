package com.example.design.t01_single_responsibility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类单一职责原则
 *
 * @author LuoXianchao
 * @since 2023/4/16 17:15
 */
public class SingleResponsibilityTest02 {

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
abstract class VehicleRun {
    protected String name;

    public abstract void run();
}

class Car extends VehicleRun {
    public Car(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(this.name + " 在公路上跑。");
    }
}

class Boat extends VehicleRun {
    public Boat(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(this.name + " 在水上跑。");
    }
}

class Plane extends VehicleRun {
    public Plane(String name) {
        super(name);
    }
    @Override
    public void run() {
        System.out.println(this.name + " 在天上飞。");
    }
}


