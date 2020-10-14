package juc.c_000;

public class T05_Synchronized {
    private int count= 10;
    public static void main(String[] args) {

    }

    public synchronized void m1() { //等同于在方法的代码执行时要synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public  void m2() { //等同于上面m1
        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }

    }
}
