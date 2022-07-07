package ProkSy.RP.RP_006.RP_A001;

public class ExtendsThread extends Thread {

    int iterationen = (int) (Math.random() * 10) + 1;

    public void run() {
        for (int i = 0; i < iterationen; i++) {
            System.out.println("ExtendedThread - Wiederholung : " + (i + 1));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Runnable runner = () -> {
            int iterationen = (int) (Math.random() * 10) + 1;
            for (int i = 0; i < iterationen; i++) {
                System.out.println("ExtendedThread - Wiederholung : " + (i + 1));
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runner).start();
        ExtendsThread et = new ExtendsThread();
        et.start();
    }
}
