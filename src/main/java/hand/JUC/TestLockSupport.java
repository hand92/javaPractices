package hand.JUC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {
    //容器，提供add size方法
    //线程1加到10，线程2，如果加到5，输出一下提示就结束
    private List<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);

    }

    public int size() {
        return list.size();

    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        TestLockSupport testLockSupport = new TestLockSupport();

        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                testLockSupport.add(new Object());
                System.out.println("add " + i);
                if (testLockSupport.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "t1");

        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("t2 end");
            LockSupport.unpark(t1);
        }, "t1");

        t2.start();
        t1.start();
    }
}
