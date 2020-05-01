package hand.JUC.A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test_A1B2C3_ReentranLock_condition {
    static int i = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                while (i < 3) {
                    c2.signalAll();
                    c1.await();
                    System.out.print(i);
                    i++;
                }
                c2.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 3; i++) {
                    System.out.print((char) (65 + i));
                    c1.signalAll();
                    c2.await();

                }
                c1.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();
    }
}
