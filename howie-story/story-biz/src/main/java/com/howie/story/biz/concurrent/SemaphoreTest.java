package com.howie.story.biz.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {

    }

    public static void limiting () {
        //定义信号量
        Semaphore semaphore = new Semaphore(100);
        //获取当前Semaphore对象上是正在等待许可证的线程数量。若当前有线程正在队列等待，直接返回
        if (semaphore.getQueueLength() > 0) {
            return;
        }

        try {
            //当前线程尝试去阻塞的获取1个许可证(不可中断的)。此过程是阻塞的，它会一直等待许可证，直到发生以下任意一件事：
            semaphore.acquire();
            //处理业务逻辑
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放
            semaphore.release();
        }
    }
}
