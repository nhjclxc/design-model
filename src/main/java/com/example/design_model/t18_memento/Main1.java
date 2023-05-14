package com.example.design_model.t18_memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @author LuoXianchao
 * @since 2023/5/13 17:03
 */
public class Main1 {
    public static void main(String[] args) {

        // test1();

        Caretaker2 caretaker2 = new Caretaker2();
        // 原始对象
        Branch branch = new Branch(789, "测试机构789");
        User user = new User(123, "测试123");
        System.out.println(branch);
        System.out.println(user);

        // 备忘录保存
        caretaker2.put(branch.getBranchMemento());
        caretaker2.put(user.getUserMemento());

        // 某些操作导致属性变化
        branch.setBranchName("branch 变化之后的名字");
        System.out.println(branch);
        user.setName("user 变化之后的名字");
        System.out.println(user);

        // 恢复原有属性
        System.out.println("恢复数据");
        Memento branchMemento = caretaker2.getMemento(BranchMemento.class, branch.getId());
        if (Objects.isNull(branchMemento)){
            System.out.println("该对象没有备份数据");
        }else {
            branch.recoverBranchMemento(branchMemento);
            System.out.println(branch);
        }
        Memento userMemento = caretaker2.getMemento(UserMemento.class, user.getId());
        if (Objects.isNull(userMemento)){
            System.out.println("该对象没有备份数据");
        }else {
            user.recoverUserMemento(userMemento);
            System.out.println(user);
        }

        System.out.println("-------------------");
        caretaker2.put(new Branch(999, "测试机构999").getBranchMemento());
        Memento branchMemento2 = caretaker2.getMemento(BranchMemento.class, 666);
        if (Objects.isNull(branchMemento2)){
            System.out.println("该对象没有备份数据");
        }else {
            branch.recoverBranchMemento(branchMemento2);
            System.out.println(branch);
        }
    }

    private static void test1() {
        Caretaker caretaker = new Caretaker();
        // 原始对象
        User user = new User(123, "测试123");
        System.out.println(user);

        // 备忘录保存
        // Memento userMemento = user.getUserMemento();
        // caretaker.add(userMemento);
        // caretaker.add(new User(456, "测试456").getUserMemento());

        caretaker.put(user);
        caretaker.put(new User(456, "测试456"));
        // 某些操作导致属性变化
        user.setName("变化之后的名字");
        System.out.println(user);

        // 恢复原有属性
        Memento memento = caretaker.getMemento(user.getId());
        if (Objects.isNull(memento)){
            System.out.println("该对象没有备份数据");
        }
        user.recoverUserMemento(memento);
        System.out.println(user);
    }
}
/**
 * 抽象备忘录对象的管理对象，；里面会保存很多备忘录对象
 */

class Caretaker2 {
    /**
     * key：备忘录对象Memento的所有子类
     * value：该子类的List集合
     */
    private final Map<Class<? extends Memento> , List<Memento>> mementoClassMap = new HashMap<>();

    public Caretaker2() {
    }

    public void put(Memento memento) {
        Class<? extends Memento> clazz = memento.getClass();
        List<Memento> mementoList;
        if (mementoClassMap.containsKey(clazz)){
            mementoList = mementoClassMap.get(clazz);
        }else {
            mementoList = new ArrayList<>();
        }
        mementoList.add(memento);
        mementoClassMap.put(clazz, mementoList);
    }

    public Memento getMemento(Class<? extends Memento> clazz, int id) {
        if (!mementoClassMap.containsKey(clazz)){
            return null;
        }
        List<Memento> mementoList = mementoClassMap.get(clazz);
        return findMemento(mementoList, id);
    }

    private Memento findMemento(List<Memento> mementoList, int id){
        return mementoList.stream().filter(e -> {
            if (e instanceof UserMemento) {
                UserMemento userMemento = (UserMemento) e;
                return Objects.deepEquals(id, userMemento.getId());
            }else if (e instanceof BranchMemento) {
                BranchMemento branchMemento = (BranchMemento) e;
                return Objects.deepEquals(id, branchMemento.getId());
            }
            return false;
        }).findAny().orElse(null);
    }
}

class Caretaker {
    private final Map<Integer, Memento> mementoMap = new HashMap<>();

    public Caretaker( ) {
    }
    public void put(User user){
        mementoMap.put(user.getId(),user.getUserMemento());
    }
    public Memento getMemento(int id){
        if (mementoMap.containsKey(id)){
            return mementoMap.get(id);
        }
        return null;
    }
    // private final List<Memento> mementoList = new ArrayList<>();
    //
    // public Caretaker( ) {
    // }
    // public void add(Memento memento){
    //     mementoList.add(memento);
    // }
    // public Memento getMemento(int id){
    //     return mementoList.stream().filter(e -> {
    //         if (e instanceof UserMemento) {
    //             UserMemento userMemento = (UserMemento) e;
    //             return Objects.deepEquals(id, userMemento.getId());
    //         }
    //         return false;
    //     }).findAny().orElse(null);
    // }
}

/**
 * User备忘录对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class UserMemento implements Memento{
    private Integer id;
    private String name;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class BranchMemento implements Memento{
    private Integer id;
    private String branchName;
}
/**
 * 抽象备忘录对象
 */
interface Memento{
}

/**
 * 原始对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private Integer id;
    private String name;
    public Memento getUserMemento(){
        return new UserMemento(this.id, this.name);
    }
    public void recoverUserMemento(Memento memento){
        if (memento instanceof UserMemento){
            UserMemento userMemento = (UserMemento) memento;
            this.name = userMemento.getName();
        }
    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Branch{
    private Integer id;
    private String branchName;
    public Memento getBranchMemento(){
        return new BranchMemento(this.id, this.branchName);
    }
    public void recoverBranchMemento(Memento memento){
        if (memento instanceof BranchMemento){
            BranchMemento branchMemento = (BranchMemento) memento;
            this.branchName = branchMemento.getBranchName();
        }
    }
}
