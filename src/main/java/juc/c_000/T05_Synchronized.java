package juc.c_000;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 可以直接写在方法上，也可以直接作用域代码块中
 */
public class T05_Synchronized {
    private int count= 100;

    public synchronized void m1() { //等同于在方法的代码执行时要synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public  void m2() { //等同于上面m1
        synchronized (T05_Synchronized.class){
//        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }

    }
}
