package juc.msb02;

import java.util.ArrayList;
import java.util.List;

/**
 * 接着上面的例子。只是少了volatile . 为啥结果还是一样的？
 */
public class T05_VolatileVsSync {
    /*volatile*/ int count = 0;

    synchronized void m() {
        for (int i = 0; i < 10000; i++)
            count++;
    }

    public static void main(String[] args) {
        T05_VolatileVsSync t = new T05_VolatileVsSync();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);

    }

}
