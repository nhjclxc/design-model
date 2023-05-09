package com.example.design_model.t15_iterator;

import java.util.Iterator;
import java.util.Map;

/**
 * @author LuoXianchao
 * @since 2023/5/9 20:24
 */
public class Main2 {
}


/**
 * Map的迭代器
 */
class MapIterator<E> implements MyIterator2<E>{

    private final Map<Integer, E> map;
    private final Integer size;
    private Integer index;

    public MapIterator(Map<Integer, E> map) {
        this.map = map;
        this.size = map.size();
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.size;
    }

    @Override
    public E next() {
        return map.get(this.index++);
    }

    @Override
    public void remove() {

    }
}

/**
 * 抽象迭代器
 */
interface MyIterator2<E> extends Iterator<E> {
    @Override
     boolean hasNext();

    @Override
     E next();

    @Override
     void remove();
}