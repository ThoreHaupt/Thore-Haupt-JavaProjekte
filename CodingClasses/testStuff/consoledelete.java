package CodingClasses.testStuff;

public class consoledelete {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("hello");
        Thread.sleep(1000); // Just to give the user a chance to see "hello".
        System.out.print("\b\b\b\b\b");
        System.out.print("world");
    }
}
