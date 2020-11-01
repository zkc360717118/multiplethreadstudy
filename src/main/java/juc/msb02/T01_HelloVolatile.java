package juc.msb02;

import java.util.concurrent.TimeUnit;

/**
 * // 对比一下没有volatile ,整个程序运行的结果的区别
 * 没有volatile 程序一直不停止，就算running已经被主线程改成false.
 */
public class T01_HelloVolatile {
    /*volatile*/ boolean running = true;

    void m() {
        System.out.println("m start");

        while (running) {
            //business  logic
        }

        System.out.println("m end");
    }

    public static void main(String[] args) {
        T01_HelloVolatile t = new T01_HelloVolatile();

        new Thread(t::m).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running=false;

    }
}
