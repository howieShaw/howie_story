package com.howie.story.biz.leetCode.offer;

/**
 * 交替打印奇数和偶数
 */
public class PrintOddAndEven {

    public static void main(String[] args) {
        PrintOddAndEven print = new PrintOddAndEven();
        Thread odd = new Thread(print.new PrintODD());
        Thread even = new Thread(print.new PrintEVEN());
        odd.start();
        even.start();
    }

    public synchronized void print(String s) {
        this.notify();
        System.out.println(s);
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class PrintODD implements Runnable {

        public void run() {
            for (int i = 1;i<100;i+=2) {
                print("ODD:"+i);
            }
        }
    }

    private class PrintEVEN implements Runnable {

        public void run() {
            for (int i= 2; i<100;i+=2) {
                print("EVEN:"+i);
            }
        }
    }
}
