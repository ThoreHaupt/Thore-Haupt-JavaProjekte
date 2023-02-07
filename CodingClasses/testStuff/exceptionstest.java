package CodingClasses.testStuff;

public class exceptionstest extends RuntimeException {
    public exceptionstest(String msg) {
        super(msg);
    }

    public static void main(String[] args) {
        try {
            throw new exceptionstest("DUDE");

        } catch (RuntimeException e) {
            System.out.println("test");
            throw new RuntimeException("thats illigal");
            // System.out.println("jah"); //unreachable code
        }

    }
}
