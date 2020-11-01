package juc.msb02;

import java.util.concurrent.TimeUnit;

public class T06_SyncSameObject {
    Object o = new Object();
    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T06_SyncSameObject t = new T06_SyncSameObject();
        //启动第一个线程
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动第二个线程
        t.o = new Object(); //更换了一个新的锁对象
        Thread t2 = new Thread(t::m, "t2");
        t2.start();
    }
}
