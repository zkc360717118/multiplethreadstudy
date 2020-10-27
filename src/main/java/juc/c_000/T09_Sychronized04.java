package juc.c_000;

import java.util.concurrent.TimeUnit;

/**
 * sychronized 锁住的内容，在异常发生的时候，会被释放
 */
public class T09_Sychronized04 {
    int num =0;
    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            num++;
            System.out.println(Thread.currentThread().getName() + "num: " + num );

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (num == 5) {
                int i = 5 / 0;
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T09_Sychronized04 t = new T09_Sychronized04();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };

        new Thread(r, "t1").start();  // 当num =5 会抛异常，然后 第二个线程t2 才有机会。

        TimeUnit.SECONDS.sleep(1);

        new Thread(r,"t2").start();
    }
}

