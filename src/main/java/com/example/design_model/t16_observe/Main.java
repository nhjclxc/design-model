package com.example.design_model.t16_observe;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式 observe pattern
 *  发布-订阅模式 （发布订阅模式）
 *
 * @author LuoXianchao
 * @since 2023/5/10 20:17
 */
public class Main {
    public static void main(String[] args) {
        Observe sina = new Sina();
        Observe netEase = new NetEase();
        Subject subject = new WeatherSubject();
        subject.register(sina);
        subject.notifyObserve(new WeatherData(123,456));

        System.out.println("---------------------");
        subject.register(netEase);
        subject.notifyObserve(new WeatherData(23568,1234));

        System.out.println("---------------------");
        subject.remove(sina);
        subject.notifyObserve(new WeatherData(22223568,1234));

        // Observable
    }
}
/**
 * 具体的发布者
 */
class WeatherSubject implements Subject{
    /**
     * 观察者集合
     */
    private final List<Observe> observeList = new ArrayList<>();
    @Override
    public void register(Observe observe) {
        observeList.add(observe);
    }
    @Override
    public void remove(Observe observe) {
        observeList.remove(observe);
    }
    @Override
    public void notifyObserve(Object o) {
        observeList.forEach(e -> {
            e.receive(o);
        });
    }
}
/**
 * 天气数据
 */
class WeatherData {
    private final Integer temperature;// 温度
    private final Integer humidity;// 湿度
    public WeatherData(Integer temperature, Integer humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }
    public Integer getTemperature() {
        return temperature;
    }
    public Integer getHumidity() {
        return humidity;
    }
}

/**
 * 具体的观察者
 */
class Sina implements Observe{
    @Override
    public void receive(Object o) {
        WeatherData weatherData = (WeatherData)o;
        System.out.println("Sina：temperature = " + weatherData.getTemperature() + "，humidity = " + weatherData.getHumidity());
    }
}
class NetEase implements Observe{
    @Override
    public void receive(Object o) {
        WeatherData weatherData = (WeatherData)o;
        System.out.println("NetEase：temperature = " + weatherData.getTemperature() + "，humidity = " + weatherData.getHumidity());
    }
}

/**
 * 被订阅者接口 Subject（发布者）
 */
interface Subject { //Publisher
    void register(Observe observe);
    void remove(Observe observe);
    void notifyObserve(Object o);
}
/**
 * 观察者接口 Observe （订阅者）
 */
interface Observe { // Subscriber
    void receive(Object o);//接收消息
}