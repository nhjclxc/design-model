package com.example.designmodel.t03_dependence_reverse.test2;

/**
 * @author LuoXianchao
 * @since 2023/4/20 20:52
 */
public class Main2 {

    public static void main(String[] args) {
        Person person = new Person();
        person.getMsg(new Email());
        person.getMsg(new WeChat());
        person.getMsg(new OICQ());
        person.getMsg(new Twitter());

    //    依赖传递的三种方式：接口传递，构造方法传递、setter方法传递
    }
}

// 有一个Person类要接收Email、WeChat、OICQ的消息
// 方式一如下：如下不满足依赖倒置，Person没有依赖抽象，而是依赖了实现（更甚者Person依赖了更底层的模块）
// 因此方式二的实现就是加上Interfave
interface Communicat {
    String snedMsg();
}
class Email implements Communicat{
    public String snedMsg(){
        return "Email msg";
    }
}
class WeChat implements Communicat{
    public String snedMsg(){
        return "WeChat msg";
    }
}
class OICQ implements Communicat{
    public String snedMsg(){
        return "OICQ msg";
    }
}
class Twitter implements Communicat{
    public String snedMsg(){
        return "Twitter msg";
    }
}

class Person{
    /**
     * Person与接口发送依赖，而不与具体的实现类关联，而是通过接口来间接依赖
     * @param communicat
     */
    public void getMsg(Communicat communicat){
        System.out.println(communicat.snedMsg());
    }

}