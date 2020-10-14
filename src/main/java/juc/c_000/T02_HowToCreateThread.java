package juc.c_000;

/**
 * 创建线程的三种方式
 */
public class T02_HowToCreateThread {
    public static void main(String[] args) {
    //方法1
        new MyThread().start();
    //方法2
        new Thread(new MyRun()).start();
    //方法3
        new Thread(()->{
            System.out.println("lambda thread");
        }).start();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("hello MyThread!");
        }
    }

    static class MyRun implements Runnable {

        public void run() {
            System.out.println("hello runnable thread");
        }
    }


}
