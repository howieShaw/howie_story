package com.howie.story.api.bean.api;

public interface QueueApi<T> {

    /**
     *
     * @param t 新增队列元素
     * @return 入队是否成功
     */
    boolean add(T t);

    /**
     *
     * @return 查看队列首个元素
     */
    T peek();

    /**
     *
     * @return 出队
     */
    T poll();

}
