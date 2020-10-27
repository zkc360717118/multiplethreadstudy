package juc.c_000;

import java.util.concurrent.TimeUnit;

/**
 * 测试同步方法和非同步方法可以一起使用的，同时一起使用可能存在的问题
 */
public class T07_Synchronized02 {
    String name;
    double balance;

    /**
     * 设定账户
     * @param name
     * @param balance
     */
    public synchronized void set(String name, double balance)  {
        //设定账户
        this.name = name;
        //模拟业务处理
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //设定账户
        this.balance = balance;
    }

    /**
     * 获取余额
     * 如果不加锁，可以和synchronized 修饰的set 同步使用。
     * @return
     */
    public double getBalance() {
//    public synchronized double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) throws InterruptedException {
        T07_Synchronized02 a = new T07_Synchronized02();

        new Thread(()->a.set("zhangshan",100)).start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println(a.getBalance()); //此时虽然账户设置了，但是金额还是0, 原因就是synchronized 方法可以和非synchronized同步使用

        TimeUnit.SECONDS.sleep(2);
        System.out.println(a.getBalance()); // 金额还是100
    }
}
