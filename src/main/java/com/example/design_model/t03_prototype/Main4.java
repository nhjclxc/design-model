package com.example.design_model.t03_prototype;


import lombok.Data;
import lombok.ToString;

import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * @author LuoXianchao
 * @since 2023/4/23 21:05
 */
public class Main4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
/*
        // 重写clone()方法实现深拷贝
        Sheep4 sheep41 = new Sheep4("小肥羊 666", 5, "大黑山");
        Sheep4 temp = new Sheep4("美羊羊 666", 6, "小粉色");
        sheep41.friend = temp;
        Sheep4 sheep42 = sheep41.clone();
        // 现在我想知道内部那只羊的信息
        System.out.println(sheep41.friend );
        System.out.println(sheep42.friend );
        // 通过下面语句的输出，我们容易得出结论，重写了clone()方法之后对于引用数据类型实现了深拷贝
        System.out.println(Integer.toHexString(sheep41.friend.hashCode()) );
        System.out.println(Integer.toHexString(sheep42.friend.hashCode()) );
        System.out.println(sheep41.friend == sheep42.friend);//false

        System.out.println("\n-----------------------------\n");

        // 序列化实现深拷贝
        DeepProtoType dp = new DeepProtoType("啊哈哈", sheep41);
        System.out.println(dp);
        System.out.println(Integer.toHexString(dp.hashCode()));
        DeepProtoType dp1 = dp.deepClone();
        System.out.println(dp1);
        System.out.println(Integer.toHexString(dp1.hashCode()));

        System.out.println("-------------------");
        dp.name = "哦哦哦哦哦";
        dp1.sheep4 = temp;
        System.out.println(dp);
        System.out.println(Integer.toHexString(dp.hashCode()));
        System.out.println(dp1);
        System.out.println(Integer.toHexString(dp1.hashCode()));


*/

        Sheep4 sheep = new Sheep4("小肥羊 666", 5, "大黑山");
        Sheep4 temp = new Sheep4("美羊羊 666", 6, "小粉色");
        sheep.friend = temp;
        Sheep4 sheep4 = ObjectUtils.deepClone(sheep);
        System.out.println(Integer.toHexString(sheep.hashCode()));
        System.out.println(sheep);
        System.out.println(Integer.toHexString(sheep4.hashCode()));
        System.out.println(sheep4);

    }
}
/** Main4旨在解决.clone()方法对于引用类型的深拷贝问题
 * 经过Main3的分析我们知道，.clone()方法对于内部对象的拷贝是浅拷贝，Sheep4在重写的.cloen()方法中将实现深拷贝
 *      clone()方法里面再次调用clone()方法实现深拷贝
 */

/**深拷贝介绍
 * 1)复制对象的所有基本数据类型的成员变量值
 * 2)为所有引用数据类型的成员变量申请存储空间，并复制每个引用数据类型成员变量所引用的对象，直到该对象可达的所有对象。
 *  也就是说，对象进行深拷贝要对整个对象进行拷贝
 * 3) 深拷贝实现方式1:重写clone方法来实现深拷贝
 * 4) 深拷贝实现方式2:通过对象序列化实现深拷贝
 *
 * 通过下面两种实现方式，方式一对于引用数据类型少的时候可以使用，但是引用数据一旦变多的话，对于每一个引用数据都要写一下，就很难受
 * 而方式二，无论你有多少个引用数据类型，都是要写一遍，序列化与反序列化即可。因此推荐使用方式二，
 *
 * 注意：实现方式二的时候，设计到的所有类必须实现Serializable接口，并且设置序列ID才能拷贝成功
 */

@ToString
class Sheep4 implements Cloneable, Serializable{ //实现Serializable接口是为了方式二所用
    public String name;
    public Integer age;
    public String color;

    public Sheep4 friend;

    public Sheep4() {}
    public Sheep4(String name, Integer age, String color ) {
        this.name = name;
        this.age = age;
        this.color = color;
    }


    /**
     * 方式一：重写clone()方法实现对象序列化
     */
    @Override
    public Sheep4 clone() {
        try {
            // 下面的super.clone();完成对基本数据类型的克隆
            Sheep4 clone = (Sheep4) super.clone(); //super.clone()返回Object
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            // 重写克隆方法实现深拷贝（引用数据类型的克隆）
            // 以下调用内部对象的.clone()实现内部对象的深拷贝 this.friend.clone();
            try {
                // 这边要使用try...cathc来捕获异常，
                // 注意看Main4.main()方法下面的sheep41对象，这个对象的this.friend是有数据的不会造成空指针，但是，this.friend对象里面的friend是null，
                // 所以第一次调用到this.friend.clone()的时候是正常执行的，当this.friend内部的friend再去调用.clone()方法的时候就会出现空指针
                clone.friend = this.friend.clone();
            } catch (Exception ignored) {
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    private static final long serialVersionUID = 1L;
}

class DeepProtoType implements Serializable{

    private static final long serialVersionUID = 1L;

    public String name;

    public Sheep4 sheep4;

    public DeepProtoType() {
    }

    public DeepProtoType(String name, Sheep4 sheep4) {
        this.name = name;
        this.sheep4 = sheep4;
    }

    /**
     * 方式二：对象序列化实现深拷贝
     *
     * 使用序列化与反序列化实现对象的深拷贝
     *  整体思路：先使用对象序列化将对象数据读取成byte字节数据，之后将这个字节数组输出成对应的对象
     * @return {@link DeepProtoType}
     */
    public DeepProtoType deepClone() throws IOException, ClassNotFoundException {
        DeepProtoType deepProtoType = null;

        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream boi = null;
        ObjectInputStream ois = null;
        try {
            // 序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);// 将本对象写到对象流里面

            // 反序列化
            boi = new ByteArrayInputStream(bos.toByteArray()); //上面读取到的数据在这里进行输出
            ois = new ObjectInputStream(boi);
            Object o = ois.readObject();//获取上面输入进来的对象（获取boi = new ByteArrayInputStream(bos.toByteArray());输入的数据）

            // 返回数据
            deepProtoType = (DeepProtoType)o;
        } catch (Exception ignored) {
        }finally {
            try {
                if (!Objects.isNull(bos)){
                    bos.close();
                }
                if (!Objects.isNull(oos)){
                    oos.close();
                }
                if (!Objects.isNull(boi)){
                    boi.close();
                }
                if (!Objects.isNull(ois)) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return deepProtoType;
    }

    @Override
    public String toString() {
        return "DeepProtoType{" +
                "name='" + name + '\'' +
                ", sheep4=" + sheep4 +
                '}';
    }
}

/**
 * 对象工具类
 */
class ObjectUtils{
    /**
     * 使用序列化与反序列化实现对象的深拷贝
     *  整体思路：先使用对象序列化将对象数据读取成byte字节数据，之后将这个字节数组输出成对应的对象
     * @return {@link T}
     */
    public static<T> T deepClone(T obj) {
        try {
            T newObj = null;
            ByteArrayOutputStream bos = null;
            ObjectOutputStream oos = null;
            ByteArrayInputStream boi = null;
            ObjectInputStream ois = null;
            try {

                // 序列化
                bos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(obj);// 将本对象写到对象流里面

                // 反序列化
                boi = new ByteArrayInputStream(bos.toByteArray()); //上面读取到的数据在这里进行输出
                ois = new ObjectInputStream(boi);
                newObj = (T)ois.readObject();//获取上面输入进来的对象（获取boi = new ByteArrayInputStream(bos.toByteArray());输入的数据）

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if (!Objects.isNull(bos)){
                        bos.close();
                    }
                    if (!Objects.isNull(oos)){
                        oos.close();
                    }
                    if (!Objects.isNull(boi)){
                        boi.close();
                    }
                    if (!Objects.isNull(ois)) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return newObj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}