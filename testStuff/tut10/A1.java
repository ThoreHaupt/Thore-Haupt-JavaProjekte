package testStuff.tut10;

public abstract class A1 {
    public static void main(String[] args) {
        Integer i = Integer.valueOf(127);
        Integer b = Integer.valueOf(127);
        if (i == b) {
            System.out.println("ha");
        }
    }

    public abstract void yield();

    public abstract String a();
}
