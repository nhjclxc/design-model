package com.example.design_model.t10_Flyweight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author LuoXianchao
 * @since 2023/05/02 19:43
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        // 1 新闻，2博客，3公众号
        WebSiteFactory factory = new WebSiteFactory();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (Objects.equals(reader.readLine(), "y")) {
            System.out.print("input type = ");
            String type = reader.readLine(); // 内部状态
            WebSite webSite = factory.getWebSite(type);
            System.out.print("input userName = "); // 外部状态
            String userName = reader.readLine();
            System.out.print("input content = "); // 外部状态
            String content = reader.readLine();
            webSite.use(new User(userName), content);
            System.out.print("input continue?");
        }
        reader.close();
    }
}

class WebSiteFactory{
    /**
     * webSites池pool
     */
    private Map<String, WebSite> pool = new HashMap<>();
    public WebSite getWebSite(String type){
        WebSite webSite = pool.get(type);
        if (Objects.isNull(webSite)) {
            webSite = new ConcreteWebSite(type);
            pool.put(type, webSite); // webSite就是享元对象
        }
        return webSite;
    }

    public int getWebSiteCategory(){
        return pool.size();
    }

}
class ConcreteWebSite extends WebSite{
    public ConcreteWebSite(String type) {
        super(type);
    }

    @Override
    public void use(User user, String content) {
        System.out.println(user.name + "以{" + this.type + "}形式发出，关于《" + content + "》的一则消息。");
    }
}
abstract class WebSite{
    /**
     * type内部状态，享元共享的数据，
     * User content 是外部状态
     */
    // 1新闻，2博客，3公众号
    protected String type;

    public WebSite(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public abstract void use(User user, String content);
}
class User{
    public String name;
    public User(String name) {
        this.name = name;
    }
}
