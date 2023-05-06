package com.example.design_model.t13_command;

/**
 * @author LuoXianchao
 * @since 2023/5/5 20:19
 */
public class Main1 {
    public static void main(String[] args) {
        LandArmy landArmy = new LandArmy();
        LandArmyCommand landArmyCommand = new LandArmyCommand(landArmy);
        Invoker invoker = new Invoker(landArmyCommand);
        invoker.execute("坦克进攻");


        AirArmy airArmy = new AirArmy();
        AirArmyCommand airArmyCommand = new AirArmyCommand(airArmy);
        Invoker ariInvoker = new Invoker(airArmyCommand);
        ariInvoker.execute("轰炸机进攻");
    }
}

/**
 * 调用者（命令发出者）
 */
class Invoker{
    private final Command command;
    public Invoker(Command command) {
        this.command = command;
    }
    public void execute(String commandType){
        command.execute(commandType);
    }
}

/**
 * 被调用者（命令接收者）2
 */
class LandArmy implements Receiver{
    public void action(String commandType){
        System.out.println("LandArmy执行了：" + commandType + "命令。");
    }
}
/**
 * 被调用者（命令接收者）1
 */
class AirArmy implements Receiver{
    public void action(String commandType){
        System.out.println("AirArmy执行了：" + commandType + "命令。");
    }
}
/**
 * 接收者接口
 */
interface Receiver{
    void action(String commandType);
}
/**
 * 具体某个命令2
 */
class LandArmyCommand extends Command{ // implements Command{
    // private final Receiver receiver;
    public LandArmyCommand(Receiver receiver) {
        super(receiver);
        // this.receiver = receiver;
    }
    @Override
    public void execute(String commandType) {
        receiver.action(commandType);
    }
    @Override
    public void undo(String commandType) {

    }
}
/**
 * 具体某个命令1
 */
class AirArmyCommand extends Command{ // implements Command{
    // private final Receiver receiver;
    public AirArmyCommand(Receiver receiver) {
        super(receiver);
        // this.receiver = receiver;
    }
    @Override
    public void execute(String commandType) {
        receiver.action(commandType);
    }
    @Override
    public void undo(String commandType) {

    }
}
/**
 * 命令接口
 */
abstract class Command{
    protected Receiver receiver;
    public Command(Receiver receiver) {
        this.receiver = receiver;
    }
    public abstract void execute(String commandType);
    public abstract void undo(String commandType);
}
// interface Command{
//     void execute(String commandType);
//     void undo(String commandType);
// }
