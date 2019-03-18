package com.howie.story.api.bean.node;

import com.howie.story.api.bean.api.QueueApi;

public class ArrayQueue<T> implements QueueApi<T> {
    private Object[] data;
    private int size = 0;

    ArrayQueue(int capacity) {
        data = new Object[capacity];
    }

    ArrayQueue () {
        this(16);
    }

    @Override
    public boolean add(T t) {
        if (!isEmpty()) {
            return false;
        }
        data[size++] =  t;
        if (size == data.length) {
            size = data.length;
        }
        return true;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }

        return (T)data[size];
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T t = (T)data[size];
        data[size--] = null;
        if (size < 0) {
            size = 0;
        }
        return t;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public boolean isFull() {
        return size == data.length;
    }
}
