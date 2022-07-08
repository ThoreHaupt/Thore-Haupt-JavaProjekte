package testStuff;

import java.util.concurrent.Semaphore;

public class threadstuff {
    public static final Semaphore available = new Semaphore(0, true);

    public static void main(String[] args) {
        Runnable rA = () -> {
            try {
                available.acquire();
                System.out.println("A - before");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("A - After");
        };
        Runnable rB = () -> {
            try {
                available.acquire();
                System.out.println("B - before");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("B - After");
        };

        Thread threadA = new Thread(rA);
        Thread threadB = new Thread(rB);
        threadA.start();
        threadB.start();

        System.out.println("start:");
        available.release();
    }
}
