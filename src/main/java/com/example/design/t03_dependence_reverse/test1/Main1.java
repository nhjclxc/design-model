package com.example.design.t03_dependence_reverse.test1;

/**
 * @author LuoXianchao
 * @since 2023/4/20 20:52
 */
public class Main1 {

    public static void main(String[] args) {
        Person person = new Person();
        person.getMsg(new OICQ());
        person.getMsg(new WeChat());
        person.getMsg(new Email());
    }
}
// 有一个Person类要接收Email、WeChat、OICQ的消息
// 方式一如下：如下不满足依赖倒置，Person没有依赖抽象，而是依赖了实现（更甚者Person依赖了更底层的模块）
// 因此方式二的实现就是加上Interfave
class Email{
    public String snedMsg(){
        return "Email msg";
    }
}
class WeChat{
    public String snedMsg(){
        return "WeChat msg";
    }
}
class OICQ{
    public String snedMsg(){
        return "OICQ msg";
    }
}

class Person{
    public void getMsg(Email email){
        System.out.println(email.snedMsg());
    }
    public void getMsg(WeChat weChat){
        System.out.println(weChat.snedMsg());
    }
    public void getMsg(OICQ oicq){
        System.out.println(oicq.snedMsg());
    }
}