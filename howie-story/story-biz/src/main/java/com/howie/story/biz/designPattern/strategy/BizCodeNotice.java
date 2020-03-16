package com.howie.story.biz.designPattern.strategy;

public class BizCodeNotice implements INoticeStrategy {
    @Override
    public void notice(Object id) {
        String bizCode = (String) id;
        System.out.println("发送业务号数据给一层,bizCode:"+bizCode);
    }
}
