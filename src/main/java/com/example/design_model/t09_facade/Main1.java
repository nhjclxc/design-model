package com.example.design_model.t09_facade;

/**
 * @author LuoXianchao
 * @since 2023/05/02 16:02
 */
public class Main1 {
    public static void main(String[] args) {
        HomeFacade homeFacade = new HomeFacade();
        homeFacade.open();
        homeFacade.play();
        homeFacade.shutdown();
    }
}

class HomeFacade{

    /**
     * 将各个子系统组合进来
     */
    private Device1 device1 = null;
    private Device2 device2 = null;
    private Device3 device3 = null;

    public HomeFacade() {
          this.device1 = new Device1();
          this.device2 = new Device2();
          this.device3 = new Device3(this.device2);
    }

    public void open(){
        this.device1.open();
        this.device3.open();
   }
   public void play(){
       this.device3.play();
    }
   public void pause(){
       this.device3.pause();
    }
   public void shutdown(){
       this.device3.shutdown();
       this.device2.shutdown();
       this.device1.shutdown();
    }
}

class Device1  {
    
    public void open() {
        System.out.println(" Device1 open");
    }
    
    public void play() {
        System.out.println(" Device1 play");
    }
    
    public void pause() {
        System.out.println(" Device1 pause");
    }
    
    public void shutdown() {
        System.out.println(" Device1 shutdown");
    }
}

class Device2  {
    
    public void open() {
        System.out.println(" Device2 open");
    }
    
    public void play() {
        System.out.println(" Device2 play");
    }
    
    public void pause() {
        System.out.println(" Device2 pause");
    }
    
    public void shutdown() {
        System.out.println(" Device2 shutdown");
    }
}
class Device3  {
    private Device2 device2;
    public Device3(Device2 device2){
        this.device2 = device2;
    }
    
    public void open() {
        System.out.println("open device2 ");
        this.device2.open();
        System.out.println(" Device3 open");
    }
    
    public void play() {
        System.out.println(" Device3 play");
    }
    
    public void pause() {
        System.out.println(" Device3 pause");
    }
    
    public void shutdown() {
        System.out.println(" Device3 shutdown");
    }
}


