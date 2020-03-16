package com.howie.story.biz.designPattern.strategy;

public class NoticeContext {
    private INoticeStrategy noticeStrategy;

    public NoticeContext(INoticeStrategy noticeStrategy) {
        this.noticeStrategy = noticeStrategy;
    }

    public void notice (Object id) {
        this.noticeStrategy.notice(id);
    }

}
