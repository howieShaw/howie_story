package com.howie.story.biz.designPattern.link;

public class ChainLogger {

    public AbstractLogger getAbstractLogger () {
        ErrorLogger error = new ErrorLogger(AbstractLogger.ERROR);
        DebuggerLogger debugger = new DebuggerLogger(AbstractLogger.DEBUGGER);
        InfoLogger info = new InfoLogger(AbstractLogger.INFO);

        debugger.setNextLogger(info);

        error.setNextLogger(debugger);

        return error;

    }

}
