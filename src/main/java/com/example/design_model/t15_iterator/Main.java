package com.example.design_model.t15_iterator;


/**
 * @author LuoXianchao
 * @since 2023/5/8 20:15
 */
public class Main {
}
/**
 * 迭代器模式：Iterator Pattern
 * 1、如果我们的集合元素是用不同的方式实现的，有数组、List、Map等形式，当客户端要遍历这些集合元素的时候要
 *      使用多种遍历方式，而且还会暴露元素的内部结构，这时候可以考虑使用迭代器模式。
 * 2、迭代器模式：提供一种遍历集合元素的统一接口，用一致的方法遍历集合元素，不需要知道集合的底层表示（不会暴露元素的内部结构）
 *
 */