package com.learnConcurrency.deadlock;

/**
 * a
 *
 * @author xuejinhua
 * @date 2019/1/11 15:38
 */
public class DieLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();

        Thread t1 = new Thread(new SyncThread(obj1, obj2), "t1");
        Thread t2 = new Thread(new SyncThread(obj2, obj3), "t2");
        Thread t3 = new Thread(new SyncThread(obj3, obj1), "t3");

        t1.start();
        Thread.sleep(5000);
        t2.start();
        Thread.sleep(5000);
        t3.start();

    }
}

/**
 * 理想状态下dl1线程为true从if执行先打出"if objA"然后再接着打出"if objB"之后释放A、B的锁对象，之后dl2线程执行else语句打出"else objB"，"else objA"。
 * 非理想状态下dl1先打出"if objA"，之后线程dl2执行打出"else objB"，然后1、2线程的锁对象A和B都处于被锁的状态，两个线程争夺锁对象发生死锁现象。
 */
class SyncThread implements Runnable {
    private Object obj1;
    private Object obj2;

    public SyncThread(Object o1, Object o2) {
        this.obj1 = o1;
        this.obj2 = o2;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " acquiring lock on " + obj1);
        synchronized (obj1) {
            System.out.println(name + " acquired lock on " + obj1);
            work();
            System.out.println(name + " acquiring lock on " + obj2);
            synchronized (obj2) {
                System.out.println(name + " acquired lock on " + obj2);
                work();
            }
            System.out.println(name + " released lock on " + obj2);
        }
        System.out.println(name + " released lock on " + obj1);
        System.out.println(name + " finished execution.");
    }

    private void work() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}