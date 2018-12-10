package com.howie.story.lock;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 11:47 AM 2018/12/6
 * @Modified by:xiaohaoyun
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        SynchronizedTest test1 = new SynchronizedTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.getMaxId();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test1.getMinValue();
            }
        }).start();

    }

    public void getMaxId() {
        System.out.println("getMaxId start");
        try {
            synchronized (this) {
                System.out.println("getMaxId execute");
                Thread.sleep(3000);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("getMaxId end");
    }

    public void getMinValue() {
        System.out.println("getMinValue start");
        try {
            synchronized(this) {
                System.out.println("getMinValue execute");
                Thread.sleep(3000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("getMinValue end");
    }


}
