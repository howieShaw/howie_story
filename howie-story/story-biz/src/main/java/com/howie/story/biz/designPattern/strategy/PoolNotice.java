package com.howie.story.biz.designPattern.strategy;

public class PoolNotice implements INoticeStrategy {
    @Override
    public void notice(Object id) {
        Long poolId = (Long)id;
        System.out.println("发送资源池数据给一层，poolId:"+poolId);
    }
}
