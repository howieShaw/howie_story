package com.howie.story.test;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 3:25 PM 2018/11/8
 * @Modified by:xiaohaoyun
 */
public class LongTest {

    private static long time = 0L;

    public static void main(String[] args) {
        new Thread(new ChangeL(111L)).start();
        new Thread(new ChangeL(-333L)).start();
        new Thread(new ChangeL(-444L)).start();
        new Thread(new ChangeL(-999L)).start();
        new Thread(new ReadL()).start();
    }

    public static class ChangeL implements Runnable {
        private long t;
        ChangeL (long time) {
            this.t = time;
        }
        @Override
        public void run() {
            while (true) {
                LongTest.time = this.t;
                Thread.yield();
            }

        }
    }

    public static class ReadL implements Runnable {

        @Override
        public void run() {
            while (true) {
                long read = LongTest.time;
                if (read !=111L && read !=-333L && read !=-444L && read != -999L) {
                    System.out.println(read);
                }
                Thread.yield();
            }

        }
    }





}
