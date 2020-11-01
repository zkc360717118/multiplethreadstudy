package juc.msb02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 虽然用了 volatile 但是不能保证同步的维问题
 * 下面的例子，按理说下面用10个线程，每个线程循环10000，每次给count+1. 按理说count最后应该有100000。  但是结果却不是。为什么？
 * 因为每个线程执行的m() 不是同步的sychronized.  count+= 不是原子性操作。在程序底层至少有三条系统指令。
 * 怎么解决呢？  在M() 方法前面加上sychronized
 */
public class T04_VolatileNotSync {
    volatile int count = 0;
     void m() {
        for(int i=0; i<10000; i++) count++;
    }

    public static void main(String[] args) {
        T04_VolatileNotSync t = new T04_VolatileNotSync();

        List<Thread> threads = new ArrayList<Thread>();

        for(int i=0; i<10; i++) {
            threads.add(new Thread(t::m, "thread-"+i));
        }

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.count);


    }

}


