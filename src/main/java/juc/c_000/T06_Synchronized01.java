package juc.c_000;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 加锁方法，保证同一个方法块同一时间只能有一个线程被执行
 *
 */
public class T06_Synchronized01 implements Runnable {
    private volatile int count = 100;

    @Override
    public  void run() {
//    public synchronized void run() {  //增加synchronized以后，保证同一时间只能有一个线程访问下面的代码块
        System.out.println(Thread.currentThread().getName() + " count = " + count);
        count--;
    }

    public static void main(String[] args) throws InterruptedException {
        T06_Synchronized01 t = new T06_Synchronized01();

        for (int i = 0; i < 100; i++) {
            new Thread(t,"Thread"+i).start();
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(t.count);
    }
}
