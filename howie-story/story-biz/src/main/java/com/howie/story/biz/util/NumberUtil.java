package com.howie.story.biz.util;

import com.howie.story.biz.worker.AddNumberWorker;

public class NumberUtil {
    public static void main(String[] args) {
        AddNumberWorker worker1 = new AddNumberWorker(1,100000);
        AddNumberWorker worker2 = new AddNumberWorker(100001,200000);
        AddNumberWorker worker3 = new AddNumberWorker(200001,300000);
        Thread thread1 = new Thread(worker1);
        Thread thread2 = new Thread(worker2);
        Thread thread3 = new Thread(worker3);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            System.out.println(worker1.getResult()+worker2.getResult()+worker3.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int p =0;
        for (int i=1;i<300000;i++) {
            p+=i;
        }
        System.out.println("p="+p);


    }


}
