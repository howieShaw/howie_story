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

    public synchronized void print(String s,int num) {
        this.notify();
        System.out.println(s+"  num:"+num);
        try {
            this.wait();
            if (num ==99) {
                notify();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class PrintODD implements Runnable {

        public void run() {
            for (int i = 1;i<100;i+=2) {
                print("ODD:"+i,i);
                if (i+2 >=100) {
                    Thread.interrupted();
                }
            }

        }
    }

    private class PrintEVEN implements Runnable {

        public void run() {
            for (int i= 2; i<100;i+=2) {
                print("EVEN:"+i,i);
                if (i+2 >=100) {
                    Thread.interrupted();
                }
            }
        }
    }
}
