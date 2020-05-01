package hand.JUC.A1B2C3;

import java.util.concurrent.locks.LockSupport;

public class Test_A1B2C3_LockSupport {
    private static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                LockSupport.park();
                System.out.print((char)(65 + i));
                LockSupport.unpark(t2);


            }
        });

        t2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                LockSupport.unpark(t1);
                LockSupport.park();
                System.out.print(i);
            }
        });
        t2.start();
        t1.start();

    }
}
