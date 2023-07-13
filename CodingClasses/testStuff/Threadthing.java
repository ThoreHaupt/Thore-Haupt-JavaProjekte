package CodingClasses.testStuff;

public class Threadthing {

    public static void main(String[] args) {
        System.out.println("Start");
        Threadthing f = new Threadthing();
        Runnable r = () -> {
            for (int i = 0; i < 10; i++) {
                f.meineMethode(i);
                /* try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } */
            }
        };
        long t1 = System.currentTimeMillis();
        Thread thr1 = new Thread(r, "A");
        thr1.start();
        Thread thr2 = new Thread(r, "B");
        thr2.start();
        try {
            thr1.join();
            thr2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - t1);
    }

    synchronized void meineMethode(int i) { //
        System.out.println(i + Thread.currentThread().getName());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
