package CodingClasses.testStuff;

public class KonstruktorTets extends SuperTest {
    static int i = 5;
    static {
        System.out.println("statischer Konstruktor der Klasse");
    }

    public static void main(String[] args) {
        System.out.println("main");
    }
}
