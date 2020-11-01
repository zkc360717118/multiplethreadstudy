package juc.msb01;

/**
 * 查看线程状态
 */
public class T04_ThreadState {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("spot2:" + this.getState()); // spot2:RUNNABLE

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        System.out.println("spot1:" + t.getState()); // spot1:NEW

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("spot3:"  + t.getState()); // spot3:TERMINATED
    }
}
