package com.howie.story.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 1:44 PM 2018/12/9
 * @Modified by:xiaohaoyun
 */
public class ObjWaitTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        try {

            lock.lock();

            lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        Object obj = new Object();

        synchronized (obj) {
            try {
                obj.wait();
                obj.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            obj.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
