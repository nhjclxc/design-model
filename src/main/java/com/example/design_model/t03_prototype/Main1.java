package com.example.design_model.t03_prototype;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main1 {
    /**
     * 不使用原型模型，直接new10次
     */
    public static void main(String[] args) {
        String name = "Tom";
        int age = 29;
        String color = "白色";
        List<Sheep> sheepList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            sheepList.add(new Sheep(name,age,color));
        }
        for (Sheep sheep : sheepList) {
            System.out.println(sheep);
        }
    }
}
@Data
@AllArgsConstructor
class Sheep {
    public String name;
    public Integer age;
    public String color;
    public Sheep() {
    }
}