package com.howie.story.biz.process;

import com.howie.story.biz.bean.SNode;

/**
 * @Author:xiaohaoyun
 * @Description： 单项链表
 * @Date：created in 下午6:16 2018/5/30
 * @Modified by:xiaohaoyun
 */
public class SLinkList<T> {
    public SNode<T> top;
    public SNode<T> current;

    public SLinkList (SNode<T> top) {
        this.top = top;
        this.current = this.top;
    }


}
