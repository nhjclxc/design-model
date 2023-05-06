package com.example.design_model.t13_command;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.reflections.Reflections;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author LuoXianchao
 * @since 2023/5/5 20:51
 */
public class Main3 {

    public static void main(String[] args) {
        RemoteController3 controller3 = new RemoteController3();
        controller3.sendCommand3(LightOnCommand3.class);
        controller3.undoCommand3();
        System.out.println();
        controller3.sendCommand3(LightOffCommand3.class);
        controller3.undoCommand3();
        System.out.println("--------------------");
        controller3.sendCommand3(TVOnCommand3.class);
        controller3.undoCommand3();
        System.out.println();
        controller3.sendCommand3(TVOffCommand3.class);
        controller3.undoCommand3();
    }
}
/**
 * 实现遥控器的命令模式
 */

/**
 * 命令发出者
 */
class RemoteController3{

    // private static final List<Class<? extends Command3>> command3List = Lists.newArrayList();
    // private static final Map<Integer, Class<? extends Command3>> command3Maps = Maps.newHashMap();

    private static final Map<Class<? extends Command3>, Command3> command3Map = new HashMap<>();

    /**
     * 静态代码块对所以命令执行初始化，实现命令的单列
     */
    static{
        //获取该路径下所有类
        Reflections reflections = new Reflections("com.example.design_model.t13_command");
        //获取继承了Command3的所有类
        Set<Class<? extends Command3>> classSet = reflections.getSubTypesOf(Command3.class);
        Iterator<Class<? extends Command3>> iterator = classSet.iterator();
        while (iterator.hasNext()){
            Class<? extends Command3> clazz = iterator.next();
            try {
                command3Map.put(clazz, clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println(command3Map);
    }
    // 撤销命令
    private Command3 undoCommand3;

    public RemoteController3() {
    }
    public void setCommand3(Class<? extends Command3> clazz, Command3 command3){
        command3Map.put(clazz, command3);
    }
    // 发布命令
    public void sendCommand3(Class<? extends Command3> clazz){
        Command3 command3 = command3Map.get(clazz);
        if (Objects.isNull(command3)){
            System.out.println("找不到该命令，请先设置该命令！！！");
            return;
        }
        command3.execute();
        undoCommand3 = command3; //记录本次使用的命令，用于执行撤销
    }
    // 撤销命令
    public void undoCommand3(){
        if (Objects.isNull(undoCommand3)){
            return;// 没有撤销命令，不执行任何内容
        }
        undoCommand3.undo();
        undoCommand3 = null;// 撤销命令执行之后清空撤销命令
    }
}


/**
 * 空命令
 */
class NonCommand3 implements Command3{
    @Override
    public void execute() {
    }
    @Override
    public void undo() {
    }
}

/**
 * TV关闭命令
 */
class TVOffCommand3 implements Command3{
    private final TVReceiver3 tVReceiver3  = new TVReceiver3();
    public TVOffCommand3( ) {
    }
    @Override
    public void execute() {
        this.tVReceiver3.off();
    }
    @Override
    public void undo() {
        this.tVReceiver3.on();
    }
}

/**
 * TV开启命令
 */
class TVOnCommand3 implements Command3{
    private final TVReceiver3 tVReceiver3 = new TVReceiver3();
    public TVOnCommand3( ) {
    }
    @Override
    public void execute() {
        this.tVReceiver3.on();
    }
    @Override
    public void undo() {
        this.tVReceiver3.off();
    }
}

/**
 * 电灯关闭命令
 */
class LightOffCommand3 implements Command3{
    private final LightReceiver3 lightReceiver3  = new LightReceiver3();
    public LightOffCommand3( ) {
    }
    @Override
    public void execute() {
        this.lightReceiver3.off();
    }
    @Override
    public void undo() {
        this.lightReceiver3.on();
    }
}

/**
 * 电灯开启命令
 */
class LightOnCommand3 implements Command3{
    private final LightReceiver3 lightReceiver3 = new LightReceiver3();
    public LightOnCommand3( ) {
    }
    @Override
    public void execute() {
        this.lightReceiver3.on();
    }
    @Override
    public void undo() {
        this.lightReceiver3.off();
    }
}

/**
 * 命令接收者（电灯）
 */
class LightReceiver3{
    public void on(){
        System.out.println("Light.on");
    }
    public void off(){
        System.out.println("Light.off");
    }
}
/**
 * 命令接收者（TV）
 */
class TVReceiver3{
    public void on(){
        System.out.println("TVTV.on");
    }
    public void off(){
        System.out.println("TVTV.off");
    }
}

/**
 * 命令接口
 */
interface Command3{
    void execute();
    void undo();
}