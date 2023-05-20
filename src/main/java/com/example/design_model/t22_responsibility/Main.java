package com.example.design_model.t22_responsibility;


/**
 * @author LuoXianchao
 * @since 2023/5/20 13:51
 */
public class Main {
    public static void main(String[] args) {
        Requst requst = new Requst(8000, new VicePresidentHandler());
        requst.processRequst();
    }
}

/**
 * 请求类
 */
class Requst {
    public Integer price;
    public Handler handler;
    public Requst() {}
    public Requst(Integer price, Handler handler) {
        this.price = price;
        this.handler = handler;
    }
    public void processRequst( ) {
        this.handler.processRequst(this);
    }
}
/**
 * 校长
 */
class PresidentHandler extends Handler{
    public PresidentHandler() {
        super(null);
    }
    @Override
    public void processRequst(Requst requst) {
        if (requst.price < 30000){
            System.out.println("不支持的操作");
            return;
        }
        if (requst.price <= 50000){
            System.out.println("本次请求由 校长 处理");
            return;
        }
        System.out.println("请求金额过大，不支持的请求金额。");
    }
}
/**
 * 副校长
 */
class VicePresidentHandler extends Handler{
    public VicePresidentHandler( ) {
        super(new PresidentHandler());
    }
    @Override
    public void processRequst(Requst requst) {
        if (requst.price < 10000){
            System.out.println("副校长 不支持的操作");
        }else if (requst.price <= 30000){
            System.out.println("本次请求由 副校长 处理");
        }
        // 请求传递
        this.nextHandler.processRequst(requst);
    }
}
/**
 * 院长类
 */
class DeanrHandler extends Handler{
    public DeanrHandler( ) {
        super(new VicePresidentHandler());
    }
    @Override
    public void processRequst(Requst requst) {
        if (requst.price < 5000){
            System.out.println("不支持的操作");
            return;
        }
        if (requst.price <= 10000){
            System.out.println("本次请求由 院长类 处理");
            return;
        }
        // 请求传递
        this.nextHandler.processRequst(requst);
    }
}
/**
 * 教学主任
 */
class TeacherHandler extends Handler{
    public TeacherHandler() {
        super(new DeanrHandler());
    }
    @Override
    public void processRequst(Requst requst) {
        if (requst.price < 0){
            System.out.println("不支持的操作");
            return;
        }
        if (requst.price <= 5000){
            System.out.println("本次请求由 教学主任 处理");
            return;
        }
        // 请求传递
        this.nextHandler.processRequst(requst);
    }
}
/**
 * 抽象处理类
 */
abstract class Handler{
    protected Handler nextHandler;
    public Handler( ) { }
    public Handler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
    public abstract void processRequst(Requst requst);
}