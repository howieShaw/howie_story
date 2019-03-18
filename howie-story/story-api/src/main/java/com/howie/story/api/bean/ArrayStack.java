package com.howie.story.api.bean;

import com.howie.story.api.bean.api.StackApi;

/**
 * @Author:xiaohaoyun
 * @Description： 栈
 * @Date：created in 上午9:56 2018/6/11
 * @Modified by:xiaohaoyun
 */
public class ArrayStack<E> implements StackApi<E> {
    /**
     * 栈内元素
     */
    Object[] data = null;
    /**
     * 站内元素个数
     */
    int size =0;
    /**
     * 栈顶指针
     */
    int top = -1;

    ArrayStack (int initCapacity) {
        data = new Object[initCapacity];
    }

    ArrayStack() {
        this(16);
    }
    @Override
    public E pop() {
        if (data == null || top == size-1) {
            return null;
        }
        E e = (E)data[top];
        data[top--] = null;
        return e;
    }

    @Override
    public boolean push(E e) {
        if (size == data.length) {
            return false;
        }
        data[top++] = e;

        return true;
    }

    @Override
    public E peek() {
        if (data == null || top == size-1) {
            return null;
        }
        E e = (E)data[top];

        return e;
    }

    @Override
    public int indexOf(E e) {
        int index = 0;
        for (int i =0;i<=top;i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {

        return top == -1;
    }
}
