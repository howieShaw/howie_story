package com.howie.story.biz.designPattern.strategy;

public class ChannelNotice implements INoticeStrategy {
    @Override
    public void notice(Object id) {
        Long channelId = (Long) id;
        System.out.println("发送频道数据给一层，chanelId:"+channelId);
    }
}
