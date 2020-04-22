package hand.AQS;


public class Demo1 {
    //线程安全，不会出现重复数据
    private MyLock lock = new MyLock();
    private int m = 0;

    public int next() {
        lock.lock();
        try {
            return m++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Demo1 demo = new Demo1();
        Thread[] th = new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i] = new Thread(() -> {
                System.out.println(demo.next());
            });
            th[i].start();
        }
    }
}
