package com.howie.story.biz.designPattern.strategy;

public class NoticeMain {

    public static void main(String[] args) {
        //通知一层下游的业务号
        NoticeContext bizCode = new NoticeContext(new BizCodeNotice());
        bizCode.notice("RNG Never Give Up");

        //通知一层发布频道
        bizCode = new NoticeContext(new ChannelNotice());
        bizCode.notice(10086l);

        //通知一层发布模块
        bizCode = new NoticeContext(new ModuleNotice());
        bizCode.notice(10086l);

        //通知一层发布资源池
        bizCode = new NoticeContext(new ModuleNotice());
        bizCode.notice(10086l);
    }
}
