package CodingClasses.testStuff;

public class StopwatchThread extends Thread {
    boolean running = true;

    public void run() {
        while (running) {
            DatumZeug.threadloop();
        }
    }

    public StopwatchThread() {

    }

    public void stopWatch() {
        running = false;
    }

}
