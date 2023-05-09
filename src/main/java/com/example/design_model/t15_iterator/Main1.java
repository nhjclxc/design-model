package com.example.design_model.t15_iterator;

import java.util.Iterator;

/**
 * @author LuoXianchao
 * @since 2023/5/8 21:38
 */
public class Main1 {
    public static void main(String[] args) {
        // List<Integer> integerList = Arrays.asList(123, 546, 789, 147, 258, 369);
        // Integer[] integerArray = (Integer[]) integerList.toArray();
        // ConcreteAggregate<Integer> inergerIterator = new ConcreteAggregate<>(integerArray);
        // MyIterator<Integer> iterator = inergerIterator.iterator();
        // while (iterator.hasNext()){
        //     System.out.println(iterator.next());
        // }

        // node测试
        NodeAggregate<Integer> nodeAggregate = new NodeAggregate<>();
        nodeAggregate.add(123);
        nodeAggregate.add(456);
        nodeAggregate.add(789);
        nodeAggregate.add(147);
        nodeAggregate.add(225);
        MyIterator<Integer> nodeIterator = nodeAggregate.iterator();
         while (nodeIterator.hasNext()){
            System.out.println(nodeIterator.next());
             nodeIterator.remove();
        }
    }
}

/**
 * 数据存储的具体实现类 ConcreteAggregate
 * 用到我们的迭代器的类
 */

class NodeAggregate<E> implements Aggregate<E>{
    public NodeAggregate() {
    }

    private final Node<E> header = new Node<>(null,null);//先做一个头节点

    public void add(E data) {
        Node<E> p = header;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new Node<>(data,null);
    }
    @Override
    public MyIterator<E> iterator() {
        return new NodeIterator<>(header);
    }
}

class Node<E> {
    public E data;
    public Node<E> next;
    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }
}
/**
 * 具体的迭代器实现类
 */
class NodeIterator<E> implements MyIterator<E> {
    private final Node<E> data;//数据
    private Node<E> p;//指针p来指向当前遍历到的元素
    public NodeIterator(Node<E> data) {
        this.data = data;
        // this.p = data.next;//指第一个元素

        this.p = data;//指头节点元素
    }

    @Override
    public boolean hasNext() {
        // return this.p != null;

        this.p = this.p.next;
        return this.p != null;
    }
    @Override
    public E next() {
        // E e = this.p.data;
        // this.p = this.p.next;
        // return e;

        return this.p.data;
    }
    @Override
    public void remove() {
        // throw new UnsupportedOperationException("MyIterator.remove");
    }
}


/**
 * 数据存储的具体实现类 ConcreteAggregate
 * 用到我们的迭代器的类
 */
class ConcreteAggregate<E> implements Aggregate<E>{
    /**
     * 数据存储格式
     */
    private final E[] datas;
    public ConcreteAggregate(E[] datas) {
        this.datas = datas;
    }
    @Override
    public MyIterator<E> iterator() {
        return new ConcreteIterator<E>(this.datas);
    }
}

/**
 * 数据存储的抽象接口 aggregate
 */
interface Aggregate<E> {
    MyIterator<E> iterator();
}

/**
 * 具体的迭代器实现类
 */
class ConcreteIterator<E> implements MyIterator<E> {
    private final E[] datas;//数据
    private Integer index;//索引
    private final Integer length;//数据长度
    public ConcreteIterator(E[] datas) {
        this.datas = datas;
        this.length = datas.length;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.length;
    }
    @Override
    public E next() {
        return datas[index++];
    }
    @Override
    public void remove() {
        // index 后面的元素逐个往前移动一个位置
        int i = index;
        for (; i < length; i++){
            datas[i] = datas[i + 1];
        }
        // 最后的元素置空
        datas[i] = null;
        // throw new UnsupportedOperationException("MyIterator.remove");
    }
}

/**
 * 抽象的迭代器接口
 * @param <E> 泛型E表示存储的元素的类型
 */
 interface MyIterator<E> extends Iterator<E> {
    @Override
    public boolean hasNext();

    @Override
    public E next();

    @Override
    public void remove();
}