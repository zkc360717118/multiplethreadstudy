package juc.c_000;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 方法可重入
 *  同样的二个synchronized方法，如果被同一个线程锁掉，那么他们这个线程可以同时进入这2个同步方法，
 */
public class T08_Synchronized03 {
    synchronized void m1()  {
        System.out.println("m1 start");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        m2();
        System.out.println("m1 end");
    }

    synchronized void m2() {
        System.out.println("m2 start");
    }

    public static void main(String[] args) {
        T08_Synchronized03 t = new T08_Synchronized03();
        t.m1();
    }
}
