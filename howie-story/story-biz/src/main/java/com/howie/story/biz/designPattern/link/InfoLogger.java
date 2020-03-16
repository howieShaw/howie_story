package com.howie.story.biz.designPattern.link;

public class InfoLogger extends AbstractLogger {

    public InfoLogger(Integer level) {
        super(level);
    }

    @Override
    public void write(String message) {
        System.out.println("INFO:----"+message);
    }
}
