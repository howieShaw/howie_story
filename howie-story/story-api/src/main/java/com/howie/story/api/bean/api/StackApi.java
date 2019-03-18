package com.howie.story.api.bean.api;

public interface StackApi<E> {

    /**
     * 出栈并删除元素
     * @return 栈顶元素
     */
    E pop();

    /**
     *
     * @param e 进栈元素
     * @return 是否成功进栈
     */
    boolean push(E e);

    /**
     *
     * @return 返回栈顶元素 ，单不删除
     */
    E peek();

    /**
     *
     * @param e 栈元素
     * @return 返回元素在栈中的位置
     */
    int indexOf(E e);

    /**
     *
     * @return 栈是否为空
     */
    boolean isEmpty();



}
