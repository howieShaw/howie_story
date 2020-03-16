package com.howie.story.biz.designPattern.strategy;

public class ModuleNotice implements INoticeStrategy {
    @Override
    public void notice(Object id) {
        Long moduleId = (Long)id;
        System.out.println("发送模块数据给一层，模块Id:"+moduleId);
    }
}
