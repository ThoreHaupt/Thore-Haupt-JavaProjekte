package AIFB;

public class ThreadTest100 {
    public static void main(String[] args) {
        ThreadTest100 obj = new ThreadTest100();
        Runnable r1 = () -> {
            obj.m1();
        };
        Runnable r2 = () -> {
            obj.m2();
        };
        new Thread(r1).start();
        new Thread(r2).start();
    }

    synchronized void m1() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("m1");
    }

    synchronized void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.err.println("m2");
    }
}
