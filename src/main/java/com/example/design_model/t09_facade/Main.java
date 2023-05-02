package com.example.design_model.t09_facade;


import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LuoXianchao
 * @since 2023/05/02 11:07
 */
public class Main {
}
/**
 * 外观模式：
 *  当多个类具有一些相同的方法时，可以考虑用一个高层的接口来定义这些方法，然后再用具体的实现类来实现这些接口
 *  当外部想用具体实现类的方法是，通过多态的方式提供给外部使用，对于使用者而言他们只知道高层抽象的接口里面的一些方法
 *  具体实现不关心，只需达到他们使用的目的即可。这个高层的接口也叫做 界面类，顾名思义就是提供给外界使用的入口
 *
 *  外观模式 Facade，也叫过程模式：外观模式为子系统中的一组接口提供一个一致的界面，次模式定义了一个高层接口
 *  外观模式通过定义的一个高层接口，用以屏蔽内部子系统的实现细节，使得调用端只需根据这个接口发生依赖即可，无需关心内部实现细节
 */