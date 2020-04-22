package hand.AQS;


import java.util.concurrent.locks.ReentrantLock;

public class Demo2 {
    //可重入  等同于 ReentrantLock
//    private ReentrantLock lock = new ReentrantLock();
    private MyLock lock = new MyLock();
    private int m = 0;

    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        Demo2 demo = new Demo2();
        new Thread(()->{
            demo.a();
        }).start();
    }
}
