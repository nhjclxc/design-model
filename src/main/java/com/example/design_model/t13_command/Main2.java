package com.example.design_model.t13_command;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author LuoXianchao
 * @since 2023/5/5 20:51
 */
public class Main2 {
    public static void main(String[] args) {

        LightReceiver lightReceiver = new LightReceiver();
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);
        RemoteController controller = new RemoteController();
        controller.setOnCommand2(0, lightOnCommand, lightOffCommand);
        Command2[] onCommand2s = controller.getOnCommand2s();
        controller.sendCommand2(onCommand2s[0]);
        controller.undoCommand2();
        System.out.println();
        Command2[] offCommand2s = controller.getOffCommand2s();
        controller.sendCommand2(offCommand2s[0]);
        controller.undoCommand2();

    }
}
/**
 * 实现遥控器的命令模式
 */

/**
 * 命令发出者
 */
class RemoteController2{

    private final Command2[] onCommand2s;
    private final Command2[] offCommand2s;
    // 撤销命令
    private Command2 undoCommand2;

    /**
     * 对所以命令初始化为空命令
     */
    public RemoteController2() {
        onCommand2s = new Command2[6];
        offCommand2s = new Command2[6];
        // 初始化空命令
        for (int i = 0; i < 6; i++) {
            onCommand2s[i] = new NonCommand();
            offCommand2s[i] = new NonCommand();
        }
    }
    public void setOnCommand2(int i, Command2 onCommand2, Command2 offCommand2){
        onCommand2s[i] = onCommand2;
        offCommand2s[i] = offCommand2;
    }

    public Command2[] getOnCommand2s() {
        return onCommand2s;
    }

    public Command2[] getOffCommand2s() {
        return offCommand2s;
    }

    // 发布命令
    public void sendCommand2(Command2 command2){
        command2.execute();
        undoCommand2 = command2; //记录本次使用的命令，用于执行撤销
    }
    // 撤销命令
    public void undoCommand2(){
        if (Objects.isNull(undoCommand2)){
            return;// 没有撤销命令，不执行任何内容
        }
        undoCommand2.undo();
        undoCommand2 = null;// 撤销命令执行之后清空撤销命令
    }
}
class RemoteController{
    // 开命令
    private final Command2[] onCommand2s;
    // 关命令
    private final Command2[] offCommand2s;
    // 撤销命令
    private Command2 undoCommand2;

    /**
     * 对所以命令初始化为空命令
     */
    public RemoteController() {
        onCommand2s = new Command2[6];
        offCommand2s = new Command2[6];
        // 初始化空命令
        for (int i = 0; i < 6; i++) {
            onCommand2s[i] = new NonCommand();
            offCommand2s[i] = new NonCommand();
        }
    }
    public void setOnCommand2(int i, Command2 onCommand2, Command2 offCommand2){
        onCommand2s[i] = onCommand2;
        offCommand2s[i] = offCommand2;
    }

    public Command2[] getOnCommand2s() {
        return onCommand2s;
    }

    public Command2[] getOffCommand2s() {
        return offCommand2s;
    }

    // 发布命令
    public void sendCommand2(Command2 command2){
        command2.execute();
        undoCommand2 = command2; //记录本次使用的命令，用于执行撤销
    }
    // 撤销命令
    public void undoCommand2(){
        if (Objects.isNull(undoCommand2)){
            return;// 没有撤销命令，不执行任何内容
        }
        undoCommand2.undo();
        undoCommand2 = null;// 撤销命令执行之后清空撤销命令
    }
}

/**
 * 空命令
 */
class NonCommand implements Command2{
    @Override
    public void execute() {
    }
    @Override
    public void undo() {
    }
}
/**
 * 电灯关闭命令
 */
class LightOffCommand implements Command2{
    private final LightReceiver lightReceiver;
    public LightOffCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }
    @Override
    public void execute() {
        this.lightReceiver.off();
    }
    @Override
    public void undo() {
        this.lightReceiver.on();
    }
}

/**
 * 电灯开启命令
 */
class LightOnCommand implements Command2{
    private final LightReceiver lightReceiver;
    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }
    @Override
    public void execute() {
        this.lightReceiver.on();
    }
    @Override
    public void undo() {
        this.lightReceiver.off();
    }
}

/**
 * 命令接收者（电灯）
 */
class LightReceiver{
    public void on(){
        System.out.println("Light.on");
    }
    public void off(){
        System.out.println("Light.off");
    }
}

/**
 * 命令接口
 */
interface Command2{
    void execute();
    void undo();
}