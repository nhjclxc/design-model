package com.example.design_model.t11_proxy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author LuoXianchao
 * @since 2023/05/03 10:23
 */
public class Main1 {
    public static void main(String[] args) {
        UserDaoProxy userDaoProxy = new UserDaoProxy(new UserDaoImpl());

        userDaoProxy.addUser(new User(1,"张三1"));
        userDaoProxy.addUser(new User(2,"张三2"));
        userDaoProxy.addUser(new User(3,"张三3"));
        userDaoProxy.addUser(new User(1,"张三4"));
        User user = userDaoProxy.getUser(null);
        User user1 = userDaoProxy.getUser(345);
        int i = userDaoProxy.deleteUser(null);
        int i1 = userDaoProxy.deleteUser(234);
        int i2 = userDaoProxy.deleteUser(3);

        System.out.println();
    }
}
/**
 *静态代理
 * 静态代理在使用时需要定义一个接口或抽象父类，被代理对象（目标对象）与代理对象一起实现相同的接口或抽象父类。
 *
 *以下以MyBatis的代理模式为例实现过程：
 * 1)定义一个接口:IUserDao
 * 2)目标对象UserDAO实现接口IUserDAO
 * 3)使用静态代理方式,就需要在代理对象UserDAOProxy中也实现IUserDAO
 * 4)调用的时候通过调用代理对象的方法来调用目标对象.
 * 5)特别提醒:代理对象与目标对象要实现相同的接口,然后通过调用相同的方法来调用目标对象的方法。
 *
 *静态代理优缺点：
 * 1)优点:在不修改目标对象的功能前提下，能通过代理对象对目标功能扩展
 * 2)缺点:因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类
 * 3)一旦接口增加方法,目标对象与代理对象都要维护
 */


/**
 * 代理类
 */
class UserDaoProxy implements IuserDao{ //基于接口的实现完成代理
    /**
     * 看似注入的是这个接口，实则使用的是这个接口的实现类
     */
    private final IuserDao userDao;
    public UserDaoProxy(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    /**
     * 检测访问权限
     */
    private boolean checkAccess() {
        System.out.println("Proxy: Checking access prior to firing a real request.");
        return true;
    }
    /**
     * 保存访问日志
     */
    private void logAccess(User user) {
        System.out.println("Proxy: Logging the time of request." + user);
    }
    /**
     * 以下三个方法对目标对象方法进行增强
     */
    @Override
    public User getUser(Integer id) {
        if (checkAccess()) {
            if (Objects.isNull(id)) {
                System.out.println("Key 不能为空");
                return null;
            }
            User user = userDao.getUser(id);
            logAccess(user);
            return user;
        }
        return null;
    }
    @Override
    public int addUser(User user) {
        if (Objects.isNull(user)){
            System.out.println("不能添加空数据。");
            return 0;
        }
        Integer id = user.getId();
        User temp = userDao.getUser(id);
        if (!Objects.isNull(temp)){
            System.out.println("id = " + id + "的数据已存在。");
            return 0;
        }
        return userDao.addUser(user);
    }
    @Override
    public int deleteUser(Integer id) {
        if (Objects.isNull(id)){
            System.out.println("Key 不能为空");
            return 0;
        }
        User temp = userDao.getUser(id);
        if (Objects.isNull(temp)){
            System.out.println("该id不存在，删除失败。");
            return 0;
        }
        return userDao.deleteUser(id);
    }
}
/**
 * 实现类
 */
class UserDaoImpl implements IuserDao{

    /**
     * 假装用这个map来保存数据
     */
    private final Map<Integer, User> userMap = new HashMap<>();
    @Override
    public User getUser(Integer id) {
        return userMap.get(id);
    }
    @Override
    public int addUser(User user) {
        userMap.put(user.getId(), user);
        return 1;
    }
    @Override
    public int deleteUser(Integer id) {
        userMap.remove(id);
        return 1;
    }
}
/**
 * 接口（抽象父类）
 */
interface IuserDao{
    User getUser(Integer id);
    int addUser(User user);
    int deleteUser(Integer id);
}

@Data
@AllArgsConstructor
class User {
    private Integer id;
    private String name;
}
