package testStuff;

public class exceptionstest extends RuntimeException {
    public exceptionstest(String msg) {
        super(msg);
    }

    public static void main(String[] args) {
        try {
            throw new exceptionstest("DUDE");
        } catch (RuntimeException e) {
            System.out.println("test");
            throw new exceptionstest("error in catch");
        } finally {
            System.out.println("hahaha");
        }

    }
}
