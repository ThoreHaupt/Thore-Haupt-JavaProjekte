package CodingClasses.testStuff;

public class Countdown {
    public static void main(String[] args) {
        rekursion(3);
    }

    public static void rekursion(int i) {
        if ((i = (i <= 5) ? i : 5) < 4 && i > 0) {
            System.out.println("Gleich ist es so weit");
            rekursion(i - 1);
        } else if (i == 0 || i == 4) {
            System.out.println("nächster Schritt");
            rekursion(i - 1);
        } else if (i < 0) {
            System.out.println("Tada");
        } else {
            System.out.println(i);
            rekursion(i - 1);
        }
    }
}
