package testStuff;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DatumZeug {

    private static Thread t;
    static SimpleDateFormat tf = new SimpleDateFormat("mm:ss:SSS");
    public static Date starttime;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("press Enter to Start Stopwatch");
        s.nextLine();

        starttime = new Date();
        System.out.println("Startet Stoppwatch at : " + starttime + "\n");
        System.out.println("press Enter to stop Stopwatch");

        StopwatchThread t = startThread();

        s.nextLine();
        Date stoptime = new Date();

        t.stopWatch();
        // System.out.print("\b\b\b\b\b\b\b\b\b");
        s.close();
        System.out.println("The time was " + tf.format(stoptime.getTime() - starttime.getTime()));
    }

    public static StopwatchThread startThread() {
        StopwatchThread thread = new StopwatchThread();
        thread.start();
        return thread;
    }

    public static void threadloop() {
        System.out.print("\b\b\b\b\b\b\b\b\b");
        long time = new Date().getTime() - starttime.getTime();
        System.out.print(tf.format(time));
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
