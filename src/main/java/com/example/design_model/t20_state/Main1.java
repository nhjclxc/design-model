package com.example.design_model.t20_state;

import lombok.Data;

import java.util.Random;

/**
 * @author LuoXianchao
 * @since 2023/5/15 21:30
 */
public class Main1 {
    public static void main(String[] args) {
        StateActivity stateActivity = new StateActivity();
        //当前state = 不能抽奖 初始为不能抽奖的状态，扣除积分之后的state就是可以抽奖的状态
        stateActivity.getState().deduct();
        System.out.println(stateActivity.getState());

        // 当前state = 可以抽奖
        boolean draw = stateActivity.getState().draw();
        System.out.println(stateActivity.getState());
        if (draw){
            stateActivity.getState().award();
            System.out.println(stateActivity.getState());
        }
        System.out.println(stateActivity.getState());


    }
}

/**
 * 以下各个方法实现完成之后还要对活动图类里面的状态进行修改
 */
/**
 * 具体状态4 奖品领完
 */
class NoAwardState extends State{
    { super.name = "NoAwardState"; }

    public NoAwardState(StateActivity stateActivity) {
        super(stateActivity);
    }

    @Override
    public void deduct() {
    }
    @Override
    public boolean draw() {
        return false;
    }
    @Override
    public void award() {
    }
}
/**
 * 具体状态3 发放奖品
 */
class AwardState extends State{
    { super.name = "AwardState"; }

    public AwardState(StateActivity stateActivity) {
        super(stateActivity);
    }

    @Override
    public void deduct() {
    }
    @Override
    public boolean draw() {
        return false;
    }
    @Override
    public void award() {
        // 还有奖品发放并且回到不能抽奖的状态，没奖品回去不能抽奖的状态，
        System.out.println("AwardState.award.after = " + stateActivity.getPrize());
        if (stateActivity.getPrize() > 0) {
            stateActivity.deductPrize(1);
            System.out.println("AwardState.award.before = " + stateActivity.getPrize());
            this.stateActivity.setState(stateActivity.getNoDrawState());
        }else {
            System.out.println("奖品发放完毕，你的手速太慢了！！！");
            this.stateActivity.setState(stateActivity.getNoAwardState());
        }
    }
}
/**
 * 具体状态2 可以抽奖
 */
class DrawState extends State{
    { super.name = "DrawState"; }

    public DrawState(StateActivity stateActivity) {
        super(stateActivity);
    }

    @Override
    public void deduct() {
        System.out.println("DrawState.deduct.after = " + stateActivity.getIntegral());
    }
    @Override
    public boolean draw() {
        System.out.println("DrawState.draw = " + stateActivity.getIntegral());
        Random random = new Random();
        int i = random.nextInt(10);
        System.out.println("i = " + i);
        if (i >= 5){
            //中奖 去发放奖品的状态
            this.stateActivity.setState(stateActivity.getAwardState());
            return true;
        }
        // 没抽中奖品 回去不能抽奖的状态
        this.stateActivity.setState(stateActivity.getNoDrawState());
        return false;
    }
    @Override
    public void award() {
        System.out.println("DrawState.award." );
    }
}
/**
 * 具体状态1 不能抽奖
 */
class NoDrawState extends State{
    { super.name = "NoDrawState"; }

    public NoDrawState(StateActivity stateActivity) {
        super(stateActivity);
    }

    @Override
    public void deduct() {
        // 扣除10各积分之后，才可以转去可以抽奖的状态
        System.out.println("NoDrawState.deduct.after = " +  stateActivity.getIntegral());
        if ( stateActivity.getIntegral() >= 10) {
            stateActivity.deductIntegral(10);
            System.out.println("NoDrawState.deduct.before = " +  stateActivity.getIntegral());
            this.stateActivity.setState(stateActivity.getDrawState());
        }else {
            System.out.println("积分不足，不能抽奖");
        }
    }
    @Override
    public boolean draw() {
        System.out.println("NoDrawState.draw = " + stateActivity.getIntegral());
        return false;
    }
    @Override
    public void award() {
        System.out.println("NoDrawState.award");
    }
}

/**
 * 抽象状态类
 */
abstract class State{
    protected String name;
    protected StateActivity stateActivity;
    public State(StateActivity stateActivity) {
        this.stateActivity = stateActivity;
    }
    /**
     * 扣除积分
     */
    abstract void deduct();
    /**
     * 抽奖方法
     * @return 返回是否抽中
     */
    abstract boolean draw();
    /**
     * 发放奖品
     */
    abstract void award();
    public void setStateActivity(StateActivity stateActivity) {
        this.stateActivity = stateActivity;
    }
    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                '}';
    }
}

/**
 * 活动图类 Activity 上下文Context持有者，里面会保存所有状态的对象
 */
@Data
class StateActivity {
    /**
     * 上下文类持有奖品信息和各个状态的对象
     */
    protected  Integer integral = 10; // 积分数
    protected  Integer prize = 3; // 奖品数
    private final State awardState = new AwardState(this);
    private final State drawState = new DrawState(this);
    private final State noAwardState = new NoAwardState(this);
    private final State noDrawState = new NoDrawState(this);

    private State state;

    public StateActivity( ) {
        this.state = noDrawState;
    }
    public void deductIntegral(Integer integral) {
        this.integral -= integral;
    }
    public void deductPrize(Integer prize) {
        this.prize -= prize;
    }
}