package testStuff.testPolymorphie;

public class A {
    public int a = 4;

    static {
        System.out.println("Static A");
    }

    public A() {
        System.out.println("Konstruktor A");
    }

    public A(int f) {

    }

    void foo(A a) {
        System.out.println("Afoo(a)");
        return;
    }

    void foo(B b) {
        System.out.println("Afoo(B)");
        return;
    }

    void foo(long i) {
        System.out.println("A_l");
    }
}
