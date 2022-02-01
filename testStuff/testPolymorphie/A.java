package testStuff.testPolymorphie;

public class A {
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
