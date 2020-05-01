package hand.JUC;

import sun.awt.windows.ThemeReader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLockCondition<E> {
    //面试题2
    //固定同步容器  消费者和生产者

    private LinkedList<E> list = new LinkedList<>();
    private static final int MAX = 10;
    private int count = 0;

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition producer = reentrantLock.newCondition();
    private Condition consumer = reentrantLock.newCondition();

    public void put(E e) {
        try {
            reentrantLock.lock();
            while (count == MAX) {
                producer.await();
            }
            count++;
            list.add(e);
            consumer.signalAll();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public E get() {
        E e = null;
        try {
            reentrantLock.lock();
            while (count == 0) {
                consumer.await();
            }
            count--;
            e = list.removeFirst();
            producer.signalAll();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        return e;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        TestReentrantLockCondition<String> testReentrantLockCondition = new TestReentrantLockCondition();
        CountDownLatch countDownLatch = new CountDownLatch(12);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(testReentrantLockCondition.get());
                }
                countDownLatch.countDown();
            }, "c" + i).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    testReentrantLockCondition.put(Thread.currentThread().getName() + " " + j);
                }
                countDownLatch.countDown();

            }, "p" + i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("mian end,count: " + testReentrantLockCondition.getCount());

    }
}
