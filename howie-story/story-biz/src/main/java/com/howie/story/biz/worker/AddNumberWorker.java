package com.howie.story.biz.worker;

public class AddNumberWorker implements Runnable {
    private volatile int result;
    private final int start;
    private final int end;

    public AddNumberWorker(int start,int end) {
        this.start = start;
        this.end = end;

    }
    public void run() {
        for (int i = this.start;i<=this.end;i++) {
            result = result+i;
        }
    }

    public int getResult () {
        return result;
    }
}
