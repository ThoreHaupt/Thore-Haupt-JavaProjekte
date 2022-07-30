package testStuff.interfacetoabstract;

public interface interfaceB {
    static void m1() {
        // so something
    }

    default void m2() {
        System.out.println("jajaja");
    }
}
