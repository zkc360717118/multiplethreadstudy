package juc.c_000;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
//        testSleep();
//        testYield();
        testJoin();
    }

    static void testSleep() {
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("t"+i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void testYield() {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("A"+i+" " + Thread.currentThread().getName());
                if(i % 10 ==0) Thread.yield(); //退出当前处理器，重进进入队列，等待执行
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("------B"+i+" " + Thread.currentThread().getName());
                if(i % 10 ==0) Thread.yield();
            }
        }).start();
    }

    static void testJoin() {
        Thread t1 = new Thread(()->{
            for(int i=0; i<50; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                    //TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{

            try {
                t1.join();  // t2 线程执行到这里，先去执行t1 线程，执行完了以后，接着执行,如果没有join. 那就是T1 和t2 各自打印自己的东西
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(int i=0; i<10; i++) {
                System.out.println("B" + i);
                try {
                    Thread.sleep(500);
                    //TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
