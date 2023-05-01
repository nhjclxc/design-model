package com.example.design_model.t05_adapter;


/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main {
}
/**
 * 适配器模式：Adapter pattern
 * 1、将某个类的接口转化成客户端期望的另一个接口来表示，主要目的是为了兼容性，让原本因接口不匹配不能一期工作的两个类可以协同工作
 * 2、适配器模式属于结构型模式
 * 3、适配器模式主要分为：
 *  3.1、类适配器模式
 *  3.2、对象适配器模式
 *  3.3、接口适配器模式
 *
 * 适配器模式的工作原理：
 *  1、适配器模式：将一个类的接口转化成另一个类的接口，让原本接口不兼容的类可以兼容
 *  2、从使用者的角度看不到被适配者，是解耦的
 *  3、用户调用适配器转化出来的目标（target）接口方法，适配器（adapter）再去调用被适配（source）的相关接口方法
 *  4、用户接收到适配器返回的结果，感觉只是和目标接口进行了交互。
 *      即适配器充当了两者之间的桥梁
 *
 */






