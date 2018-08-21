package com.howie.story.biz.util;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @Author:xiaohaoyun
 * @Description： IO 流
 * @Date：created in 下午3:41 2018/7/27
 * @Modified by:xiaohaoyun
 */
public class IOUtil {

    public static void pipeIo() {
        try {

            final PipedOutputStream outputStream = new PipedOutputStream();
            final PipedInputStream inputStream = new PipedInputStream(outputStream);

            Thread inputThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        outputStream.write("输出流".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            Thread outPutThread = new Thread(new Runnable() {
                public void run() {

                    try {
                        int data = inputStream.read();
                        while (data != -1) {
                            System.out.println(data);
                            System.out.println((char) data);
                            data = inputStream.read();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            inputThread.start();;
            outPutThread.start();

        } catch (IOException e) {
            System.out.println("~~出现异常");
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) {
        IOUtil.pipeIo();
    }


}
