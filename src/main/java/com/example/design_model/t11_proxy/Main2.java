package com.example.design_model.t11_proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author LuoXianchao
 * @since 2023/05/03 13:09
 */
public class Main2 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        IuserDao2 userDao = (IuserDao2)new ProxyFactory(new UserDaoImpl2()).getProxyInstance();
//        IuserDao2 userDao = ProxyFactory.getProxyInstance(UserDaoImpl2.class, IuserDao2.class);
        userDao.doSay("你好动态代理");
        int plus = userDao.plus(234, 123456);
        System.out.println("plus = " + plus);
        System.out.println(userDao);
        System.out.println(userDao.getClass()); // com.example.design_model.t11_proxy.$Proxy0的$Proxy0 表示内存中动态生成了代理对象
        System.out.println();


//        UserDaoImpl2 userDaoImpl22 = new UserDaoImpl2();
//        factory.setTarget(userDaoImpl22);
//        Object proxyInstance2 = factory.getProxyInstance();
//        IuserDao2 userDao2 = (IuserDao2)proxyInstance2;
//        userDao2.doSay("你好动态代理22222");
//        System.out.println(userDao2);
//        System.out.println(userDao2.getClass());
//        System.out.println();
//
//        System.out.println(userDao.equals(userDao2));

    }
}
/**
 * 动态代理：
 *  1、代理对象不需要实现接口；被代理对象任然需要实现接口，否则不能使用动态代理
 *  2、代理对象生成是利用JDK的API来实现的，动态的在内存中构建对象
 *      JDK代理类所在的包：java.lang.reflect.Proxy
 *
 * 动态代理步骤
 *  使用java.lang.reflect.Proxy.newProxyInstance()方法创建代理对象
 *    三个参数：
 *      ClassLoader loader：被代理对象(目标对象)的类加载器，target.getClass().getClassLoader()
 *      Class<?>[] interfaces：目标对象实现的接口类型
 *      InvocationHandler h：事件处理，执行目标对象的方法时，会触发该句柄，会把当前执行的目标对象的方法传入
 *
 */
/**
 * 代理类
 */
class ProxyFactory {
    /**
     * 看似注入的是这个接口，实则使用的是这个接口的实现类
     */
    private Object target1; // 目标对象
    public ProxyFactory(Object target) {
        this.target1 = target;
    }

    public void setTarget(Object target) {
        this.target1 = target;
    }

    public Object getProxyInstance() {
        Object o = Proxy.newProxyInstance(target1.getClass().getClassLoader(), target1.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String arg = "";
                        if (null != args) {
                            StringBuilder sb = new StringBuilder();
                            for (Object a : args) {
                                sb.append(a).append(", ");
                            }
                            arg = sb.substring(0, sb.length() - 2);
                        }
                        System.out.println("动态代理执行。。。" + method.getName() + "(" + arg + ")");
                        // 调用方法前做某件事
                        // invoke(Object obj, Object... args)
                        //  obj是被被代理的对象，method是被代理对象的方法，args是该方法的入参，返回值就是该方法的执行结果
                        Object invoke = method.invoke(target1, args);
                        // 调用方法后做某件事
                        return invoke;
                    }
                });

        return o;
    }
        /**
         * 获取代理对象实例
         * @param target 代理对象的实现子类
         * @param returnClazz 返回对象的字节码
         * @return 返回
         * @param <T>
         */
    public static<K,T> T getProxyInstance(Class<K> target, Class<T> returnClazz) {
        Object o = Proxy.newProxyInstance(target.getClassLoader(), target.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String arg = "";
                        if (null != args) {
                            StringBuilder sb = new StringBuilder();
                            for (Object a : args) {
                                sb.append(a).append(", ");
                            }
                            arg = sb.substring(0, sb.length() - 2);
                        }
                        System.out.println("动态代理执行。。。" + method.getName() + "(" + arg + ")");
                        // 调用方法前做某件事
                        // invoke(Object obj, Object... args)
                        //  obj是被被代理的对象，method是被代理对象的方法，args是该方法的入参，返回值就是该方法的执行结果
                        Object invoke = method.invoke(target.newInstance(), args);
                        // 调用方法后做某件事
                        return invoke;
                    }
                });

        return returnClazz.cast(o);
    }

}
/**
 * 实现类
 */
class UserDaoImpl2 implements IuserDao2{
    public void doSay(String msg){
        System.out.println(msg);
    }
    public int plus(int a, int b){
        return a+b;
    }
}
/**
 * 接口（抽象父类）
 */
interface IuserDao2{
    void doSay(String msg);
    int plus(int a, int b);
}
