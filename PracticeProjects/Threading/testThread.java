package PracticeProjects.Threading;

public class testThread extends Thread {
    
    public static void main(String[] args) {
        testThread threadTest1 = new testThread();
        testThread threadTest2 = new testThread();
        testThread threadTest3 = new testThread();

        threadTest1.start();
        threadTest2.start();
        threadTest3.start();
    }
    
    
    @Override
    public void run() {
        
        super.run();

        System.out.println("test: " + Thread.currentThread().getName());
    }
}
