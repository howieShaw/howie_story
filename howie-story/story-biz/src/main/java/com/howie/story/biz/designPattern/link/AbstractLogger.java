package com.howie.story.biz.designPattern.link;

public abstract class AbstractLogger {

    public static final Integer INFO = 1;
    public static final Integer DEBUGGER = 2;
    public static final Integer ERROR = 3;

    /**
     * 标识
     */
    private Integer level;

    /**
     * 下一个链路对象
     */
    private AbstractLogger nextLogger;

    AbstractLogger (Integer level) {
        this.level = level;
    }

    public void writeLogMessage (int level,String message) {
        if (this.level <= level) {
            write(message);
        }

        if (nextLogger != null) {
            nextLogger.writeLogMessage(level,message);
        }

    }

    /**
     * 公共行为
     */
    public abstract void write(String message);



    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public AbstractLogger getNextLogger() {
        return nextLogger;
    }

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }
}
