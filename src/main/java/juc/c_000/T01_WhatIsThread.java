package juc.c_000;

import java.util.concurrent.TimeUnit;

public class T01_WhatIsThread {
    private static class T1 extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10 ; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 线程：" + Thread.currentThread().getName());
            }
        }
    }

    /**
     * 如果用run方法，这里全部使用了main 线程
     * 如果使用了start方法,分别使用了2个不同的线程
     * @param args
     */
    public static void main(String[] args) {
        //测试1
        new T1().run();
        //测试2
//        new T1().start();

        for(int i=0; i<10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main 线程：" + Thread.currentThread().getName());
        }
    }

}
