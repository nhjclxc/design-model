package com.example.design_model.t00_mvc;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LuoXianchao
 * @since 2023/5/14 9:38
 */
public class Main {

    public static void main(String[] args) {

        // 初始化
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        UserView view = new UserView();

        // 请求
        UserModel model = new UserModel("zhansan", "zhansan123");
        Model retModel = dispatcherServlet.request(model, 1);
        view.setModel(retModel);

        // view回显model数据
        view.dispaly();
    }
}

//MVC设计模式例子
//Model
interface Model {
}
@Data
@NoArgsConstructor
class UserModel implements Model {
    private static final Map<String, UserModel> userMap = new HashMap<>();
    private String name;
    private String password;
    public UserModel(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public UserModel register(UserModel model) {
        String name = model.getName();
        if (null != userMap.get(name)){
            System.out.println("UserModel.register 该用户已存在");
            return null;
        }
        userMap.put(name, model);
        System.out.println("UserModel.register 注册成功");
        return model;
    }
    public UserModel login(UserModel model) {
        return null;
    }
    public UserModel select(UserModel model) {
        return null;
    }
}

//View
class UserView {
    private Model model;
    public Model getModel() {
        return model;
    }
    public void setModel(Model model) {
        this.model = model;
    }
    public void dispaly() {
        System.out.println("View.dispaly：");
        if (null != this.model) {
            UserModel userModel = (UserModel) this.model;
            System.out.println("Name: " + userModel.getName());
            System.out.println("Password: " + userModel.getPassword());
        }else {
            System.out.println("无数据返回");
        }
    }
}

class DispatcherServlet {
    private static final Map<Class<? extends Model>, Model> controllerMap;
    static {
        controllerMap = new HashMap<>();
        controllerMap.put(UserModel.class, new UserModel());
    }
    /**
     * request方法实现服务分发
     * @param type 1=注册，2=登录，3=查询
     */
    public Model request(Model model, int type){
        Class<? extends Model> clazz = adapt(model);
        Model retModel = null;
        if (UserModel.class.equals(clazz)) {
            UserModel userModel = (UserModel)model;
            UserModel userController = (UserModel)controllerMap.get(UserModel.class);
            switch (type) {
                case 1:
                    retModel = userController.register(userModel);
                    break;
                case 2:
                    retModel = userController.login(userModel);
                    break;
                case 3:
                    retModel = userController.select(userModel);
                    break;
                default:
                    System.out.println("不支持的操作类型，type = " + type);
            }
        }
        return retModel;
    }
    /**
     * 适配器
     */
    private Class<? extends Model> adapt(Model model){
        if (model instanceof UserModel){
            return UserModel.class;
        }
        return null;
    }
}
